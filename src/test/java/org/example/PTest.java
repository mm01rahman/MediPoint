package org.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.time.Duration;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTimeout;

public class PTest {

    @AfterAll
    static void afterAllTests() {
        System.exit(1);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Available", "Occupied"})
    public void test(String str) {
        SearchRoom sr = new SearchRoom();
        sr.choice.select(str);
        sr.searchRoom();
    }

    @ParameterizedTest
    @CsvSource({"mm01rahman, 12345678"})
    public void testLogin1(String id, String pass) {
        Login n = new Login();
        n.textField.setText(id);
        n.jPasswordField.setText(pass);
        assertDoesNotThrow(() -> n.b1.doClick());
    }


    @ParameterizedTest
    @CsvFileSource(resources = "/res.csv", numLinesToSkip = 1)
    public void test(String document, String number, String name, String gender, String disease, String room, String bill, String contact, String time) {
        NEW_PATIENT n = new NEW_PATIENT();

        n.comboBox.setSelectedItem(document);
        n.textFieldNumber.setText(number);
        n.textName.setText(name);
        n.r1.setSelected("Male".equals(gender));
        n.r2.setSelected("Female".equals(gender));
        n.textFieldDisease.setText(disease);
        n.roomChoice.select(room);
        n.textFieldBill.setText(bill);
        n.contactField.setText(contact);

        n.b1.doClick();
    }


    static Stream<Arguments> loginDetails() {
        return Stream.of(
                Arguments.of("mm01rahman", "1234567"),
                Arguments.of("MUSA", "12345678"),
                Arguments.of("mm01rahman", "12345678")
        );
    }

    @ParameterizedTest
    @MethodSource("loginDetails")
    public void testLogin2(String id, String pass) {
        Login n = new Login();
        n.textField.setText(id);
        n.jPasswordField.setText(pass);
        assertTimeout(Duration.ofMinutes(1), () -> n.b1.doClick());

    }

}
