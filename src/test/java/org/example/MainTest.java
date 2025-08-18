package org.example;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.awt.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @BeforeAll
    static void beforeAllTests() {
    }

    @AfterAll
    static void afterAllTests() {
        System.exit(1);
    }

    @BeforeEach
    void setUp() {
    }


    @AfterEach
    void tearDown() {
    }

    // --- Reception ---
    @Test
    void reception_tests() {
        Reception r = new Reception();
        assertNotNull(r);

        //Size of Jframe == Panel
        assertEquals(r.getSize(), r.getContentPane().getComponent(0).getSize());
        assertNotEquals(Color.BLACK, r.getContentPane().getBackground());

        assertTrue(r.isShowing());
        r.dispose();
        assertFalse(r.isShowing());

    }

    @Test
    void NewPateint_tests() {
        NEW_PATIENT np = new NEW_PATIENT();

        assertNotNull(np);
        assertTrue(np.isShowing());
        assertTrue(np.isVisible());

        assertEquals(new Color(90, 156, 163), np.getContentPane().getComponent(0).getBackground());
        assertEquals(new Dimension(850, 550), np.getContentPane().getSize());

        np.textFieldBill.setText("Only int should be input");
        assertFalse(np.IsDouble(np.textFieldBill.getText()));

        np.clearFields();
        assertNull(np.getGender());

        assertDoesNotThrow(() -> np.b2.doClick());

        np.b3.doClick();
        assertFalse(np.isShowing());
    }

    @Test
    public void testSearchRoomUIComponents() {
        SearchRoom searchRoom = new SearchRoom();

        assertNotNull(searchRoom.choice, "Choice dropdown should be created");
        assertNotNull(searchRoom.table, "Table should be created");
        assertNotNull(searchRoom.model, "Table model should be created");

        searchRoom.dispose();
    }

    @Test
    public void testSearchRoomSearchWithAvailable() {
        SearchRoom searchRoom = new SearchRoom();
        searchRoom.choice.select("Available");

        // Needs DBConnection + rooms table!
        Assertions.assertDoesNotThrow(() -> searchRoom.searchRoom(),
                "searchRoom() should not throw an exception when querying Available");

        searchRoom.dispose();
    }

    @Test
    void testSearchRoomNoResults() {
        SearchRoom searchRoom = new SearchRoom();
        searchRoom.choice.select("Occupied");

        // Needs DBConnection + rooms table!
        searchRoom.searchRoom();

        assertNotEquals(0, searchRoom.model.getRowCount(),
                "Should return rows if there are Occupied rooms in DB");
        searchRoom.dispose();
    }

    @Test
    void testRoomInitialization() {
        // Create Room frame
        Room room = new Room();

        // Basic checks
        assertNotNull(room.table, "Table should be created");
        assertNotNull(room.model, "Table model should be created");
        assertEquals(4, room.model.getColumnCount(), "Table should have 4 columns");

        room.dispose(); // Close frame
    }

    //check Table Column
    @Test
    void testTableColumns() {
        ALL_Patient_Info frame = new ALL_Patient_Info();
        String[] expectedColumns = {
                "ID", "Document", "Number", "Name", "Gender",
                "Disease", "Room", "Entry Time", "Bill", "Contact"
        };

        for (int i = 0; i < expectedColumns.length; i++) {
            assertEquals(expectedColumns[i], frame.model.getColumnName(i), "Column name should match");
        }

        frame.dispose();
    }

    @Test
    void testAmbulanceTableColumns() {
        Ambulance frame = new Ambulance();

        // Expected column names
        String[] expectedColumns = {"Vehicle No", "Driver Name", "Contact", "Status"};

        // Check each column
        for (int i = 0; i < expectedColumns.length; i++) {
            assertEquals(expectedColumns[i], frame.model.getColumnName(i), "Column name should match");
        }
        frame.dispose();
    }

    @Test
    void testLoginInitialization() {
        Login login = new Login();

        // Check frame is not null
        assertNotNull(login);

        // Check components
        assertNotNull(login.textField, "Username field should exist");
        assertNotNull(login.jPasswordField, "Password field should exist");
        assertNotNull(login.b1, "Login button should exist");
        assertNotNull(login.b2, "Cancel button should exist");

        // Check frame visibility
        assertTrue(login.isShowing(), "Login frame should be visible initially");

        login.dispose();
        assertFalse(login.isShowing(), "Login frame should be hidden after dispose");
    }

//    @Test
//    void testCancelButtonExits() {
//        Login login = new Login();
//
//        // Cancel button calls System.exit(0)
//        // We cannot test System.exit directly; we can test actionPerformed method with a mock
//        assertDoesNotThrow(() -> login.actionPerformed(null), "Cancel button action should not throw exception");
//
//        login.dispose();
//    }


    //Value Source Test For paitant discharge
//    @ParameterizedTest
//    @ValueSource(strings = {"101", "202", "303"})
//    public void testCheckPatientWithDifferentIDs(String patientId) {
//        patient_discharge frame = new patient_discharge();
//
//        frame.choice.add(patientId);
//        frame.choice.select(patientId);
//
//        assertDoesNotThrow(() -> frame.checkPatient(),
//                "checkPatient() should not throw for ID: " + patientId);
//
//        frame.dispose();
//    }

//    @ParameterizedTest
//    @CsvSource({
//            "101, RoomA, 500",
//            "202, RoomB, 0",
//            "303, RoomC, -10"
//    })
//    public void testPatientDataMapping(String id, String room, int bill) {
//        patient_discharge frame = new patient_discharge();
//
//        // Simulate DB result
//        frame.RNo.setText(room);
//        frame.Bill = bill;
//
//        assertEquals(room, frame.RNo.getText(), "Room number should match");
//        assertTrue(bill >= -10, "Bill should be valid test input");
//
//        frame.dispose();
//    }

//    @ParameterizedTest
//    @CsvFileSource(resources = "/res.csv", numLinesToSkip = 1)
//    void testDischargeWithCsvFile(String id, String room, int bill) {
//        patient_discharge frame = new patient_discharge();
//
//        frame.choice.add(id);
//        frame.choice.select(id);
//        frame.RNo.setText(room);
//        frame.Bill = bill;
//
//        if (bill <= 0) {
//            assertDoesNotThrow(frame::dischargePatient,
//                    "Patient should discharge if bill is cleared or negative");
//        } else {
//            frame.dischargePatient();
//            assertEquals(bill, frame.Bill, "Unpaid bill should remain");
//        }
//
//        frame.dispose();
//    }

//    static Stream<Arguments> providePatients() {
//        return Stream.of(
//                Arguments.of("101", "RoomX", 0),
//                Arguments.of("202", "RoomY", 200),
//                Arguments.of("303", "RoomZ", -50)
//        );
//    }
//
//    @ParameterizedTest
//    @MethodSource("providePatients")
//    void testDischargeWithMethodSource(String id, String room, int bill) {
//        patient_discharge frame = new patient_discharge();
//
//        frame.choice.add(id);
//        frame.choice.select(id);
//        frame.RNo.setText(room);
//        frame.Bill = bill;
//
//        if (bill <= 0) {
//            frame.dischargePatient();
//            assertNull(frame.Bill, "Bill should reset after discharge");
//        } else {
//            frame.dischargePatient();
//            assertNotNull(frame.Bill, "Unpaid bill should block discharge");
//        }
//
//        frame.dispose();
//    }
}
