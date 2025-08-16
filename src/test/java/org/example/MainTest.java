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


}
