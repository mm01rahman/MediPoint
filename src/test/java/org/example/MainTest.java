package org.example;

import org.junit.jupiter.api.*;
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

    // --- Ambulance ---
    @Test
    void ambulance_loads() {
        Ambulance t = new Ambulance();
        assertNotNull(t);
    }
}
