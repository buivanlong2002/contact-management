package service;

import model.Contact;

import java.util.List;
import java.util.Optional;

public interface IContactService {
    List<Contact> getAll();
    void add(Contact c);
    Optional<Contact> findByPhone(String phone);
    void deleteByPhone(String phone);
    List<Contact> search(String keyword);
    void load(String path);
    void save(String path);
}
