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
    private final String filePath = "contacts.csv";



    public void displayAll() {
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

    public void add() {
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
        System.out.println(" Thêm thành công.");
    }

    public void update() {
        System.out.print("Nhập số điện thoại cần cập nhật: ");
        String phone = sc.nextLine();
        Optional<Contact> cOpt = service.findByPhone(phone);
        if (cOpt.isEmpty()) {
            System.out.println(" Không tìm thấy.");
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

        System.out.println(" Cập nhật thành công.");
    }

    public void delete() {
        System.out.print("Nhập số điện thoại cần xoá: ");
        String phone = sc.nextLine();
        Optional<Contact> cOpt = service.findByPhone(phone);
        if (cOpt.isEmpty()) {
            System.out.println(" Không tìm thấy.");
            return;
        }
        System.out.print("Xác nhận xoá? (Y/N): ");
        if (sc.nextLine().equalsIgnoreCase("Y")) {
            service.deleteByPhone(phone);
            System.out.println(" Đã xoá.");
        }
    }

    public void search() {
        System.out.print("Từ khoá (tên/sđt): ");
        String k = sc.nextLine();
        List<Contact> rs = service.search(k);
        if (rs.isEmpty()) System.out.println(" Không tìm thấy.");
        else rs.forEach(System.out::println);
    }

    public void load() {
        System.out.print("Xác nhận đọc file (sẽ mất dữ liệu hiện tại)? (Y/N): ");
        if (sc.nextLine().equalsIgnoreCase("Y")) {
            service.load(filePath);
            System.out.println(" Đã đọc.");
        }
    }

    public void save() {
        service.save(filePath);
        System.out.println(" Đã ghi.");
    }

    public String getValidated(Supplier<String> input, Predicate<String> validate, String errMsg) {
        String value;
        while (true) {
            value = input.get();
            if (validate.test(value)) break;
            System.out.println(" " + errMsg);
        }
        return value;
    }

    @FunctionalInterface
    interface Supplier<T> { T get(); }

    @FunctionalInterface
    interface Predicate<T> { boolean test(T t); }
}
