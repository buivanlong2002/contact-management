package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Validator {
    public static boolean isValidPhone(String phone) {
        return phone.matches("\\d{9,11}");
    }

    public static boolean isValidEmail(String email) {
        return email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }

    public static boolean isValidDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false); // không cho phép ngày sai định dạng (như 32/01/2024)
        try {
            sdf.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
