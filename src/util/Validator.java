package util;

public class Validator {
    public static boolean isValidPhone(String phone) {
        return phone.matches("\\d{9,11}");
    }

    public static boolean isValidEmail(String email) {
        return email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }
}
