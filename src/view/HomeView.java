package view;

import controller.ContactController;

import java.util.Scanner;

public class HomeView {
    Scanner sc = new Scanner(System.in);
    ContactController contactController = new ContactController();
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
            case "1" -> contactController.displayAll();
            case "2" -> contactController.add();
            case "3" -> contactController.update();
            case "4" -> contactController.delete();
            case "5" -> contactController.search();
            case "6" -> contactController.load();
            case "7" -> contactController.save();
            case "8" -> {
                System.out.println("Tạm biệt!");
                System.exit(0);
            }
            default -> System.out.println(" Lựa chọn không hợp lệ!");
        }
    }
}
