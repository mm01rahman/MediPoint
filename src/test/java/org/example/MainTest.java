package org.example;

import org.junit.jupiter.api.*;

import java.awt.*;

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


}
