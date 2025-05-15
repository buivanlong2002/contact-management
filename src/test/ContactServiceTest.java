package test;


import model.Contact;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import service.ContactService;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class ContactServiceTest {

    private ContactService contactService;

    @BeforeEach
    void setUp() {
        contactService = new ContactService();
    }

    @Test
    public void testAddAndGetAll() {
        Contact c1 = new Contact("Nguyen Van A", "0123456789");
        contactService.add(c1);
        List<Contact> all = contactService.getAll();
        assertEquals(1, all.size());
        assertEquals("Nguyen Van A", all.get(0).getName());
    }

    @Test
    public void testFindByPhoneExists() {
        Contact c1 = new Contact("Nguyen Van A", "0123456789");
        contactService.add(c1);
        Optional<Contact> found = contactService.findByPhone("0123456789");
        assertTrue(found.isPresent());
        assertEquals("Nguyen Van A", found.get().getName());
    }

    @Test
    public void testFindByPhoneNotExists() {
        Optional<Contact> found = contactService.findByPhone("0000000000");
        assertFalse(found.isPresent());
    }

    @Test
    public void testDeleteByPhone() {
        Contact c1 = new Contact("Nguyen Van A", "0123456789");
        contactService.add(c1);
        contactService.deleteByPhone("0123456789");
        assertTrue(contactService.getAll().isEmpty());
    }

    @Test
    public void testSearch() {
        Contact c1 = new Contact("Nguyen Van A", "0123456789");
        Contact c2 = new Contact("Le Thi B", "0987654321");
        contactService.add(c1);
        contactService.add(c2);

        List<Contact> result1 = contactService.search("Nguyen");
        assertEquals(1, result1.size());
        assertEquals("Nguyen Van A", result1.get(0).getName());

        List<Contact> result2 = contactService.search("0987");
        assertEquals(1, result2.size());
        assertEquals("Le Thi B", result2.get(0).getName());

        List<Contact> result3 = contactService.search("xyz");
        assertTrue(result3.isEmpty());
    }

    @Test
    public void testLoadAndSave() {
        // Chuẩn bị dữ liệu
        Contact c1 = new Contact("Nguyen Van A", "0123456789");
        Contact c2 = new Contact("Le Thi B", "0987654321");
        contactService.add(c1);
        contactService.add(c2);

        String path = "test_contacts.csv";
        contactService.save(path);

        // Tạo instance mới để load lại
        ContactService newService = new ContactService();
        newService.load(path);

        List<Contact> loaded = newService.getAll();
        assertEquals(2, loaded.size());
        assertTrue(loaded.stream().anyMatch(c -> c.getName().equals("Nguyen Van A")));
        assertTrue(loaded.stream().anyMatch(c -> c.getPhone().equals("0987654321")));


        java.io.File f = new java.io.File(path);
        if(f.exists()) f.delete();
    }
}
