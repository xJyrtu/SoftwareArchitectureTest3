package com.x.bs.service;





import com.x.bs.dao.ContactDao;
import com.x.bs.entity.Contact;

import java.io.IOException;
import java.util.List;

public class ContactService {
    private final ContactDao  contactDao= new ContactDao();

    public List<Contact> getAllContacts() throws IOException {
        return contactDao.getAllContacts();
    }

    public Boolean addContact(Contact contact) throws IOException {
       contactDao.addContact(contact);
       return true;
    }

    public Boolean updateContact(Contact oldContact, Contact newContact) throws IOException {
      Boolean b= contactDao.updateContact(oldContact, newContact);
       return b;
    }

    public Boolean deleteContact(String name) throws IOException {
      Boolean b= contactDao.deleteContact(name);
       return b;
    }
}
