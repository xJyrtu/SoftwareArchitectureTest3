package dao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataAccess {
    private static final String FILE_PATH = "F:\\Projects\\JAVATest3\\CS-3\\src\\data\\data.txt";

    // 读取所有联系人信息
    public List<String> readAllContacts() throws IOException {
        List<String> contacts = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                contacts.add(line);
            }
        }
        return contacts;
    }

    // 写入新的联系人信息
    public void writeContact(String name, String address, String phone) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write("name: " + name + ", addr: " + address + ", phone: " + phone);
            writer.newLine();
        }
    }

    // 删除联系人信息
    public void deleteContact(String name) throws IOException {
        List<String> contacts = readAllContacts();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (String contact : contacts) {
                if (!contact.startsWith("name: " + name + ",")) { // 判断是否是要删除的联系人
                    writer.write(contact);
                    writer.newLine();
                }
            }
        }
    }

    // 修改联系人信息
    public void updateContact(String name, String newAddress, String newPhone) throws IOException {
        List<String> contacts = readAllContacts();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (String contact : contacts) {
                if (contact.startsWith("name: " + name + ",")) {
                    writer.write("name: " + name + ", addr: " + newAddress + ", phone: " + newPhone);  // 写入更新后的联系人信息
                } else {
                    writer.write(contact);
                }
                writer.newLine();
            }
        }
    }
}

