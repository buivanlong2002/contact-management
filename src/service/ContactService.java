package service;

import model.Contact;
import util.FileUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ContactService {
    private final List<Contact> contacts = new ArrayList<>();

    public List<Contact> getAll() {
        return contacts;
    }

    public void add(Contact c) {
        contacts.add(c);
    }

    public Optional<Contact> findByPhone(String phone) {
        return contacts.stream()
                .filter(c -> c.getPhone().equals(phone))
                .findFirst();
    }

    public boolean deleteByPhone(String phone) {
        return contacts.removeIf(c -> c.getPhone().equals(phone));
    }

    public List<Contact> search(String keyword) {
        return contacts.stream()
                .filter(c -> c.getPhone().contains(keyword) || c.getName().toLowerCase().contains(keyword.toLowerCase()))
                .toList();
    }

    public void load(String path) {
        contacts.clear();
        contacts.addAll(FileUtil.readFromCSV(path));
    }

    public void save(String path) {
        FileUtil.writeToCSV(contacts, path);
    }
}
