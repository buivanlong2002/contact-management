package util;

import model.Contact;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
        public static List<Contact> readFromCSV(String path) {
            List<Contact> list = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    Contact c = Contact.fromCSV(line);
                    if (c != null) list.add(c);
                }
            } catch (IOException e) {
                System.out.println(" Không thể đọc file: " + e.getMessage());
            }
            return list;
        }

    public static void writeToCSV(List<Contact> contacts, String path) {
        try {
            File file = new File(path);

            // Tạo thư mục cha nếu chưa tồn tại
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs(); // tạo thư mục cha
            }

            try (PrintWriter pw = new PrintWriter(file)) {
                for (Contact c : contacts) {
                    pw.println(c.toCSV());
                }
            }

            System.out.println("✅ Ghi file thành công!");
        } catch (IOException e) {
            System.out.println("❌ Lỗi ghi file: " + e.getMessage());
        }
    }
}
