package service;

import dao.DataAccess;

import java.io.IOException;
import java.util.List;

public class ContactService {
    private DataAccess dataAccess;

    public ContactService() {
        dataAccess = new DataAccess();
    }

    // 添加联系人
    public void addContact(String name, String address, String phone) throws IOException {
        dataAccess.writeContact(name, address, phone);
    }

    // 删除联系人
    public void deleteContact(String name) throws IOException {
        dataAccess.deleteContact(name);
    }

    // 修改联系人
    public void updateContact(String name, String newAddress, String newPhone) throws IOException {
        dataAccess.updateContact(name, newAddress, newPhone);
    }

    // 查询所有联系人
    public List<String> getAllContacts() throws IOException {
        return dataAccess.readAllContacts();
    }
}
