package controller;

import model.Contact;
import service.ContactService;
import util.InputHelper;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ContactController {
    private final Scanner sc = new Scanner(System.in);
    private final ContactService service = new ContactService();
    private final InputHelper inputHelper = new InputHelper(sc);
    private final String filePath = "data/contacts.csv";

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
        String phone = inputHelper.inputPhone();
        String group = inputHelper.inputNonEmptyString("Nhóm: ");
        String name = inputHelper.inputNonEmptyString("Họ tên: ");
        System.out.print("Giới tính: ");
        String gender = sc.nextLine().trim();
        System.out.print("Địa chỉ: ");
        String address = sc.nextLine().trim();
        String dob = inputHelper.inputDate();
        String email = inputHelper.inputEmail();

        Contact c = new Contact(phone, group, name, gender, address, dob, email);
        service.add(c);
        System.out.println(" Thêm thành công.");
    }

    public void update() {
        System.out.print("Nhập số điện thoại cần cập nhật: ");
        String phone = sc.nextLine().trim();
        Optional<Contact> cOpt = service.findByPhone(phone);
        if (cOpt.isEmpty()) {
            System.out.println(" Không tìm thấy.");
            return;
        }
        Contact c = cOpt.get();

        String group = inputHelper.inputNonEmptyString("Nhóm mới: ");
        String name = inputHelper.inputNonEmptyString("Tên mới: ");
        System.out.print("Giới tính: ");
        String gender = sc.nextLine().trim();
        System.out.print("Địa chỉ: ");
        String address = sc.nextLine().trim();
        String dob = inputHelper.inputDate();
        String email = inputHelper.inputEmail();

        c.setGroup(group);
        c.setName(name);
        c.setGender(gender);
        c.setAddress(address);
        c.setDob(dob);
        c.setEmail(email);

        System.out.println(" Cập nhật thành công.");
    }

    public void delete() {
        System.out.print("Nhập số điện thoại cần xoá: ");
        String phone = sc.nextLine().trim();
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
        String keyword = sc.nextLine().trim();
        List<Contact> rs = service.search(keyword);
        if (rs.isEmpty()) System.out.println(" Không tìm thấy.");
        else rs.forEach(System.out::println);
    }

    public void load() {
        System.out.print("Xác nhận đọc file (sẽ mất dữ liệu hiện tại)? (Y/N): ");
        if (sc.nextLine().equalsIgnoreCase("Y")) {
            service.load(filePath);
            System.out.println(" Đã đọc dữ liệu từ file.");
        }
    }

    public void save() {
        service.save(filePath);
        System.out.println(" Đã ghi dữ liệu vào file.");
    }
}
