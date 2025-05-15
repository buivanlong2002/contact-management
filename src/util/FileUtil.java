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
            System.out.println("❌ Không thể đọc file: " + e.getMessage());
        }
        return list;
    }

    public static void writeToCSV(List<Contact> list, String path) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for (Contact c : list) {
                writer.write(c.toCSV());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("❌ Lỗi ghi file: " + e.getMessage());
        }
    }
}
