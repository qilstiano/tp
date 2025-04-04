package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class EmailTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Email(null));
    }

    @Test
    public void isValidEmail() {
        // null email
        assertThrows(NullPointerException.class, () -> Email.isValidEmail(null));

        // missing parts
        assertThrows(IllegalArgumentException.class, () -> Email.isValidEmail("@example.com")); // missing local part
        assertThrows(IllegalArgumentException.class, () -> Email.isValidEmail("peterjackexample.com"));
        assertThrows(IllegalArgumentException.class, () -> Email.isValidEmail("peterjack@")); // missing domain name

        // invalid domain name
        assertThrows(IllegalArgumentException.class, () -> Email.isValidEmail("peterjack@-"));
        // underscore in domain name
        assertThrows(IllegalArgumentException.class, () -> Email.isValidEmail("peterjack@exam_ple.com"));
        // spaces in local part
        assertThrows(IllegalArgumentException.class, () -> Email.isValidEmail("peter jack@example.com"));
        // spaces in domain name
        assertThrows(IllegalArgumentException.class, () -> Email.isValidEmail("peterjack@exam ple.com"));
        // leading space
        assertThrows(IllegalArgumentException.class, () -> Email.isValidEmail(" peterjack@example.com"));
        // trailing space
        assertThrows(IllegalArgumentException.class, () -> Email.isValidEmail("peterjack@example.com "));
        // double '@' symbol
        assertThrows(IllegalArgumentException.class, () -> Email.isValidEmail("peterjack@@example.com"));
        // '@' symbol in local part
        assertThrows(IllegalArgumentException.class, () -> Email.isValidEmail("peter@jack@example.com"));
        // local part starts with a hyphen
        assertThrows(IllegalArgumentException.class, () -> Email.isValidEmail("-peterjack@example.com"));
        // local part ends with a hyphen
        assertThrows(IllegalArgumentException.class, () -> Email.isValidEmail("peterjack-@example.com"));
        // local part has two consecutive periods
        assertThrows(IllegalArgumentException.class, () -> Email.isValidEmail("peter..jack@example.com"));
        // '@' symbol in domain name
        assertThrows(IllegalArgumentException.class, () -> Email.isValidEmail("peterjack@example@com"));
        // domain name starts with a period
        assertThrows(IllegalArgumentException.class, () -> Email.isValidEmail("peterjack@.example.com"));
        // domain name ends with a period
        assertThrows(IllegalArgumentException.class, () -> Email.isValidEmail("peterjack@example.com."));
        // domain name starts with a hyphen
        assertThrows(IllegalArgumentException.class, () -> Email.isValidEmail("peterjack@-example.com"));
        // domain name ends with a hyphen
        assertThrows(IllegalArgumentException.class, () -> Email.isValidEmail("peterjack@example.com-"));
        // top level domain has less than two chars
        assertThrows(IllegalArgumentException.class, () -> Email.isValidEmail("peterjack@example.c"));
        assertThrows(IllegalArgumentException.class, () -> Email.isValidEmail("a..a@gmail.com"));
        assertThrows(IllegalArgumentException.class, () -> Email.isValidEmail("peterjack@example.1c"));
        assertThrows(IllegalArgumentException.class, () -> Email.isValidEmail("peterjack@example@com"));
        assertThrows(IllegalArgumentException.class, () -> Email.isValidEmail("peterjack@example.com."));


        // valid email
        assertTrue(Email.isValidEmail("PeterJack_1190@example.com")); // underscore in local part
        assertTrue(Email.isValidEmail("PeterJack.1190@example.com")); // period in local part
        assertTrue(Email.isValidEmail("PeterJack+1190@example.com")); // '+' symbol in local part
        assertTrue(Email.isValidEmail("PeterJack-1190@example.com")); // hyphen in local part
        assertTrue(Email.isValidEmail("a1+be.d@example1.com")); // mixture of alphanumeric and special characters
        assertTrue(Email.isValidEmail("peter_jack@very-very-very-long-example.com")); // long domain name
        assertTrue(Email.isValidEmail("if.you.dream.it_you.can.do.it@example.com")); // long local part
        assertTrue(Email.isValidEmail("e1234567@u.nus.edu")); // more than one period in domain
        assertTrue(Email.isValidEmail("a__a@gmail.com"));
        assertTrue(Email.isValidEmail("if.you.dream.it_you.can.do.it@example.com"));
        assertTrue(Email.isValidEmail("peter_jack@very-very-very-long-example.com"));
    }

    @Test
    public void equals() {
        Email email = new Email("valid@email.com");

        // same values -> returns true
        assertTrue(email.equals(new Email("valid@email.com")));

        // same object -> returns true
        assertTrue(email.equals(email));

        // null -> returns false
        assertFalse(email.equals(null));

        // different types -> returns false
        assertFalse(email.equals(5.0f));

        // different values -> returns false
        assertFalse(email.equals(new Email("other.valid@email.com")));
    }
}
