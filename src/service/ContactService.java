package service;

import model.Contact;
import util.FileUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ContactService implements IContactService {
    private final List<Contact> contacts = new ArrayList<>();

    @Override
    public List<Contact> getAll() {
        return contacts;
    }

    @Override
    public void add(Contact c) {
        contacts.add(c);
    }

    @Override
    public Optional<Contact> findByPhone(String phone) {
        return contacts.stream()
                .filter(c -> c.getPhone().equals(phone))
                .findFirst();
    }

    @Override
    public void deleteByPhone(String phone) {
        contacts.removeIf(c -> c.getPhone().equals(phone));
    }

    @Override
    public List<Contact> search(String keyword) {
        return contacts.stream()
                .filter(c -> c.getPhone().contains(keyword) || c.getName().toLowerCase().contains(keyword.toLowerCase()))
                .toList();
    }

    @Override
    public void load(String path) {
        contacts.clear();
        contacts.addAll(FileUtil.readFromCSV(path));
    }

    @Override
    public void save(String path) {
        FileUtil.writeToCSV(contacts, path);
    }


}
