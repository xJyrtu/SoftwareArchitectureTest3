package com.x.bs.dao;

import com.x.bs.entity.Contact;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDao {
    private final String filePath = "F:\\Projects\\JAVATest3\\BS\\src\\main\\java\\data\\data.txt";

    public List<Contact> getAllContacts() throws IOException {
        List<Contact> contacts = new ArrayList<>();
        if (Files.exists(Paths.get(filePath))) {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split("，");
                    if (parts.length == 3) {
                        contacts.add(new Contact(
                                parts[0].split("：")[1],
                                parts[1].split("：")[1],
                                parts[2].split("：")[1]
                        ));
                    }
                }
            }
        }
        return contacts;
    }

    public void addContact(Contact contact) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write("name：" + contact.getName() + "，phone：" + contact.getPhone() + "，addr：" + contact.getAddr() + "\n");
        }
    }

    public boolean updateContact(Contact oldContact, Contact newContact) throws IOException {
        List<Contact> contacts = getAllContacts();
        boolean updated = false;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Contact contact : contacts) {
                if (contact.getName().equals(oldContact.getName())) {
                    writer.write("name：" + newContact.getName() + "，phone：" + newContact.getPhone() + "，addr：" + newContact.getAddr() + "\n");
                    updated = true;
                } else {
                    writer.write("name：" + contact.getName() + "，phone：" + contact.getPhone() + "，addr：" + contact.getAddr() + "\n");
                }
            }
        }
        return updated;
    }

    public boolean deleteContact(String name) throws IOException {
        List<Contact> contacts = getAllContacts();
        boolean deleted = false;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Contact contact : contacts) {
                if (!contact.getName().equals(name)) {
                    writer.write("name：" + contact.getName() + "，phone：" + contact.getPhone() + "，addr：" + contact.getAddr() + "\n");
                } else {
                    deleted = true;  // 标记为已删除
                }
            }
        }
        return deleted;
    }
}
