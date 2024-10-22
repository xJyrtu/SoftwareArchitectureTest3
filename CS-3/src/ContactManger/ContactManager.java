package ContactManger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class  ContactManager {
    private static final String FILE_PATH = "F:\\Projects\\JAVATest3\\CS-3\\src\\data\\data.txt";

    public void addContact(Contact contact) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(contact.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Contact> getContacts() {
        List<Contact> contacts = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    contacts.add(new Contact(parts[0], parts[1], parts[2]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    public void updateContact(String name, Contact newContact) {
        List<Contact> contacts = getContacts();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Contact contact : contacts) {
                if (contact.getName().equals(name)) {
                    writer.write(newContact.toString());
                } else {
                    writer.write(contact.toString());
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteContact(String name) {
        List<Contact> contacts = getContacts();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Contact contact : contacts) {
                if (!contact.getName().equals(name)) {
                    writer.write(contact.toString());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
