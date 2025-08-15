package org.example;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.time.Duration;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import java.awt.event.ActionEvent;

public class MainTest {

    @BeforeAll
    static void beforeAllTests() {
    }

    @AfterAll
    static void afterAllTests() { }

    @BeforeEach
    void setUp() { }

    @AfterEach
    void tearDown() { }

//    // --- ALL_Patient_Info ---
//    @Test
//    void allPatientInfo_loads() {
//        ALL_Patient_Info t = new ALL_Patient_Info();
//        assertNotNull(t.model);
//
//    }
//
    // --- Ambulance ---
    @Test
    void ambulance_loads() {
        Ambulance t = new Ambulance();
        assertNotNull(t);
    }
//
//    // --- Department ---
//    @Test
//    void department_loads() {
//        Department t = new Department();
//        assertNotNull(t);
//        assertDoesNotThrow(t::);
//    }
//
//    // --- NEW_PATIENT ---
//    @Test
//    void newPatient_basicCalls() {
//        NEW_PATIENT t = new NEW_PATIENT();
//        assertNotNull(t);
//        assertDoesNotThrow(t::clearFields);
//        assertDoesNotThrow(t::closeFields);
//        assertTimeout(Duration.ofSeconds(3), t::loadAvailableRooms);
//    }
//
//    @Test
//    void newPatient_saveItem() {
//        NEW_PATIENT t = new NEW_PATIENT();
//        assertDoesNotThrow(t::saveItem);
//    }
//
//    // --- patient_discharge ---
//    @Test
//    void discharge_basicCalls() {
//        patient_discharge t = new patient_discharge();
//        assertNotNull(t);
//        assertDoesNotThrow(t::loadPatientIDs);
//        assertDoesNotThrow(t::checkPatient);
//        assertTimeout(Duration.ofSeconds(5), t::dischargePatient);
//    }
//
//    // --- Reception ---
//    static Stream<String> receptionCommands() {
//        return Stream.of("DUMMY","OPEN_ROOMS","ADD_PATIENT","SEARCH","LOGOUT");
//    }
////
//    @ParameterizedTest
//    @MethodSource("receptionCommands")
//    void reception_actionPerformed_commands(String cmd) {
//        Reception t = new Reception();
//        ActionEvent evt = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, cmd);
//        assertDoesNotThrow(() -> t.actionPerformed(evt));
//    }
//
//    @Test
//    void reception_actionPerformed_nullSafe() {
//        Reception t = new Reception();
//        assertDoesNotThrow(() -> t.actionPerformed(null));
//    }

//    // --- Room ---
//    @Test
//    void room_loads() {
//        Room t = new Room();
//        assertNotNull(t);
//        assertDoesNotThrow(t::loadData);
//    }
//
//    // --- SearchRoom ---
//    static Stream<String> roomQueries() {
//        return Stream.of("", "101", "nonexistent", "ICU", "Available");
//    }
//
//    @ParameterizedTest
//    @MethodSource("roomQueries")
//    void searchRoom_queries(String q) {
//        SearchRoom t = new SearchRoom();
//        assertDoesNotThrow(() -> t.searchRoom(q));
//    }
//
//    @ParameterizedTest
//    @ValueSource(strings = {"", "101", "nonexistent"})
//    void searchRoom_quickTimeout(String q) {
//        SearchRoom t = new SearchRoom();
//        assertTimeout(Duration.ofSeconds(2), () -> t.searchRoom(q));
//    }
//
//    // --- update_patient_details ---
//    @Test
//    void updateDetails_utilities() {
//        update_patient_details t = new update_patient_details();
//        assertNotNull(t);
//        assertDoesNotThrow(t::populatePatientIDs);
//        assertDoesNotThrow(t::loadAvailableRooms);
//    }
//
//    @Test
//    void updateDetails_checkAndUpdate() {
//        update_patient_details t = new update_patient_details();
//        assertDoesNotThrow(t::checkPatient);
//        assertTimeout(Duration.ofSeconds(5), t::updatePatient);
//    }
}
