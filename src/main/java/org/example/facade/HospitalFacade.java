package org.example.facade;

import org.example.adapter.DatabaseProvider;
import org.example.composite.PatientGroup;
import org.example.factory.HospitalEntityFactory;
import org.example.model.Patient;
import org.example.model.RoomInfo;
import org.example.observer.PatientEventPublisher;
import org.example.strategy.BasicPatientValidation;
import org.example.strategy.ValidationStrategy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HospitalFacade extends PatientEventPublisher {
    private final DatabaseProvider databaseProvider;
    private final HospitalEntityFactory factory = new HospitalEntityFactory();
    private ValidationStrategy validationStrategy = new BasicPatientValidation();
    private final PatientGroup admittedPatients = new PatientGroup("Admitted");

    public HospitalFacade(DatabaseProvider databaseProvider) {
        this.databaseProvider = databaseProvider;
    }

    public ValidationStrategy getValidationStrategy() {
        return validationStrategy;
    }

    public void setValidationStrategy(ValidationStrategy validationStrategy) {
        this.validationStrategy = validationStrategy;
    }

    public boolean authenticate(String username, String password) {
        try (Connection con = databaseProvider.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?")) {
            ps.setString(1, username);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Database error occurred", ex);
        }
    }

    public List<RoomInfo> fetchAvailableRooms() {
        List<RoomInfo> rooms = new ArrayList<>();
        try (Connection con = databaseProvider.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT room_no FROM rooms WHERE availability = 'Available'")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                rooms.add(factory.createRoom(rs.getString("room_no"), "Available"));
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Error loading rooms", e);
        }
        return rooms;
    }

    public void addPatient(Patient patient) {
        if (!validationStrategy.isValid(patient)) {
            throw new IllegalArgumentException(validationStrategy.getMessage());
        }

        try (Connection con = databaseProvider.getConnection()) {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO patients(document, number, name, gender, disease, room, bill, contact, time) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"
            );
            ps.setString(1, patient.getDocumentType());
            ps.setString(2, patient.getNumber());
            ps.setString(3, patient.getName());
            ps.setString(4, patient.getGender());
            ps.setString(5, patient.getDisease());
            ps.setString(6, patient.getRoom());
            ps.setDouble(7, patient.getBill());
            ps.setString(8, patient.getContact());
            ps.setTimestamp(9, java.sql.Timestamp.valueOf(patient.getAdmissionTime()));
            ps.executeUpdate();

            PreparedStatement roomUpdate = con.prepareStatement(
                    "UPDATE rooms SET availability = 'Occupied' WHERE room_no = ?"
            );
            roomUpdate.setString(1, patient.getRoom());
            roomUpdate.executeUpdate();

            con.commit();
            admittedPatients.addPatient(patient);
            notifyPatientAdded(patient);
        } catch (SQLException e) {
            throw new IllegalStateException("Error saving patient", e);
        }
    }

    public void reopenRoom(String roomNo) {
        try (Connection con = databaseProvider.getConnection();
             PreparedStatement ps = con.prepareStatement("UPDATE rooms SET availability = 'Available' WHERE room_no = ?")) {
            ps.setString(1, roomNo);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException("Error reopening room", e);
        }
    }

    public PatientGroup getAdmittedPatients() {
        return admittedPatients;
    }

    public HospitalEntityFactory getFactory() {
        return factory;
    }
}
