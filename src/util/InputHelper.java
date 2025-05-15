package util;

import java.util.Scanner;

public class InputHelper {
    private final Scanner scanner;

    public InputHelper(Scanner scanner) {
        this.scanner = scanner;
    }

    public String inputPhone() {
        while (true) {
            System.out.print("Số điện thoại: ");
            String phone = scanner.nextLine();
            if (Validator.isValidPhone(phone)) return phone;
            System.out.println(" Số điện thoại không hợp lệ! (9-11 chữ số)");
        }
    }

    public String inputEmail() {
        while (true) {
            System.out.print("Email: ");
            String email = scanner.nextLine();
            if (Validator.isValidEmail(email)) return email;
            System.out.println(" Email không hợp lệ!");
        }
    }

    public String inputDate() {
        while (true) {
            System.out.print("Ngày sinh (dd/MM/yyyy): ");
            String date = scanner.nextLine();
            if (Validator.isValidDate(date)) return date;
            System.out.println(" Ngày sinh không hợp lệ! Vui lòng nhập theo định dạng dd/MM/yyyy.");
        }
    }

    public String inputNonEmptyString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine();
            if (!value.trim().isEmpty()) return value;
            System.out.println("Trường này không được để trống!");
        }
    }
}

