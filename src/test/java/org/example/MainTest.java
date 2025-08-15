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
}
