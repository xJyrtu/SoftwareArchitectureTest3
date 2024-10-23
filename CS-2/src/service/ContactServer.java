package service;


import client.Contact;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ContactServer {
    private static final String DATA_FILE = "F:\\Projects\\JAVATest3\\CS-2\\src\\data\\data.txt";

    public List<Contact> getContacts() {
        List<Contact> contacts = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(DATA_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("，");
                if (parts.length == 3) {
                    String name = parts[0].split("：")[1];
                    String addr = parts[1].split("：")[1];
                    String phone = parts[2].split("：")[1];
                    contacts.add(new Contact(name, addr, phone));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    public void addContact(Contact contact) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(DATA_FILE, true))) {
            bw.write(contact.toString());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteContact(String name) {
        List<Contact> contacts = getContacts();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(DATA_FILE))) {
            for (Contact contact : contacts) {
                if (!contact.getName().equals(name)) {
                    bw.write(contact.toString());
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateContact(String name, Contact newContact) {
        deleteContact(name);
        addContact(newContact);
    }

    public Contact findContact(String name) {
        for (Contact contact : getContacts()) {
            if (contact.getName().equals(name)) {
                return contact;
            }
        }
        return null;
    }
}
