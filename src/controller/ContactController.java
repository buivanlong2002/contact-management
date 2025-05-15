package controller;

import model.Contact;
import service.ContactService;
import util.Validator;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ContactController {
    private final Scanner sc = new Scanner(System.in);
    private final ContactService service = new ContactService();
    private final String filePath = "data/contacts.csv";

    public void run() {
        while (true) {
            showMenu();
            String choice = sc.nextLine();
            handle(choice);
        }
    }

    private void showMenu() {
        System.out.println("""
                ===== CHƯƠNG TRÌNH QUẢN LÝ DANH BẠ =====
                1. Xem danh sách
                2. Thêm mới
                3. Cập nhật
                4. Xoá
                5. Tìm kiếm
                6. Đọc từ file
                7. Ghi vào file
                8. Thoát
                """);
        System.out.print("Chọn chức năng: ");
    }

    private void handle(String c) {
        switch (c) {
            case "1" -> displayAll();
            case "2" -> add();
            case "3" -> update();
            case "4" -> delete();
            case "5" -> search();
            case "6" -> load();
            case "7" -> save();
            case "8" -> {
                System.out.println("👋 Tạm biệt!");
                System.exit(0);
            }
            default -> System.out.println("❌ Lựa chọn không hợp lệ!");
        }
    }

    private void displayAll() {
        List<Contact> list = service.getAll();
        int count = 0;
        for (Contact c : list) {
            System.out.println(c);
            count++;
            if (count % 5 == 0) {
                System.out.print("Nhấn Enter để tiếp tục...");
                sc.nextLine();
            }
        }
    }

    private void add() {
        System.out.print("SĐT: ");
        String phone = getValidated(sc::nextLine, Validator::isValidPhone, "Số điện thoại không hợp lệ!");

        System.out.print("Nhóm: ");
        String group = sc.nextLine();
        System.out.print("Họ tên: ");
        String name = sc.nextLine();
        System.out.print("Giới tính: ");
        String gender = sc.nextLine();
        System.out.print("Địa chỉ: ");
        String address = sc.nextLine();
        System.out.print("Ngày sinh: ");
        String dob = sc.nextLine();

        System.out.print("Email: ");
        String email = getValidated(sc::nextLine, Validator::isValidEmail, "Email không hợp lệ!");

        Contact c = new Contact(phone, group, name, gender, address, dob, email);
        service.add(c);
        System.out.println("✅ Thêm thành công.");
    }

    private void update() {
        System.out.print("Nhập số điện thoại cần cập nhật: ");
        String phone = sc.nextLine();
        Optional<Contact> cOpt = service.findByPhone(phone);
        if (cOpt.isEmpty()) {
            System.out.println("❌ Không tìm thấy.");
            return;
        }
        Contact c = cOpt.get();

        System.out.print("Nhóm mới: ");
        c.setGroup(sc.nextLine());
        System.out.print("Tên mới: ");
        c.setName(sc.nextLine());
        System.out.print("Giới tính: ");
        c.setGender(sc.nextLine());
        System.out.print("Địa chỉ: ");
        c.setAddress(sc.nextLine());
        System.out.print("Ngày sinh: ");
        c.setDob(sc.nextLine());

        System.out.print("Email: ");
        c.setEmail(getValidated(sc::nextLine, Validator::isValidEmail, "Email không hợp lệ!"));

        System.out.println("✅ Cập nhật thành công.");
    }

    private void delete() {
        System.out.print("Nhập số điện thoại cần xoá: ");
        String phone = sc.nextLine();
        Optional<Contact> cOpt = service.findByPhone(phone);
        if (cOpt.isEmpty()) {
            System.out.println("❌ Không tìm thấy.");
            return;
        }
        System.out.print("Xác nhận xoá? (Y/N): ");
        if (sc.nextLine().equalsIgnoreCase("Y")) {
            service.deleteByPhone(phone);
            System.out.println("✅ Đã xoá.");
        }
    }

    private void search() {
        System.out.print("Từ khoá (tên/sđt): ");
        String k = sc.nextLine();
        List<Contact> rs = service.search(k);
        if (rs.isEmpty()) System.out.println("❌ Không tìm thấy.");
        else rs.forEach(System.out::println);
    }

    private void load() {
        System.out.print("Xác nhận đọc file (sẽ mất dữ liệu hiện tại)? (Y/N): ");
        if (sc.nextLine().equalsIgnoreCase("Y")) {
            service.load(filePath);
            System.out.println("✅ Đã đọc.");
        }
    }

    private void save() {
        service.save(filePath);
        System.out.println("✅ Đã ghi.");
    }

    private String getValidated(Supplier<String> input, Predicate<String> validate, String errMsg) {
        String value;
        while (true) {
            value = input.get();
            if (validate.test(value)) break;
            System.out.println("❌ " + errMsg);
        }
        return value;
    }

    @FunctionalInterface
    interface Supplier<T> { T get(); }

    @FunctionalInterface
    interface Predicate<T> { boolean test(T t); }
}
