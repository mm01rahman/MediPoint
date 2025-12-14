package org.example.factory;

import org.example.model.Patient;
import org.example.model.RoomInfo;

import java.time.LocalDateTime;

public class HospitalEntityFactory {
    public Patient createPatient(String documentType, String number, String name, String gender,
                                 String disease, String room, double bill, String contact) {
        return new Patient(documentType, number, name, gender, disease, room, bill, contact, LocalDateTime.now());
    }

    public RoomInfo createRoom(String roomNo, String availability) {
        return new RoomInfo(roomNo, availability);
    }
}
