package client;



import service.ContactService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class ContactApp extends JFrame {
    private ContactService contactManager;

    private JTextField nameField;
    private JTextField addressField;
    private JTextField phoneField;
    private JTextArea resultArea;

    public ContactApp() {
        contactManager = new ContactService();

        // 设置界面布局
        setTitle("个人通讯录系统");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2));

        // 创建界面元素
        JLabel nameLabel = new JLabel("姓名：");
        nameField = new JTextField();
        JLabel addressLabel = new JLabel("住址：");
        addressField = new JTextField();
        JLabel phoneLabel = new JLabel("电话：");
        phoneField = new JTextField();

        JButton addButton = new JButton("添加联系人");
        JButton deleteButton = new JButton("删除联系人");
        JButton updateButton = new JButton("修改联系人");
        JButton queryButton = new JButton("查询联系人");

        resultArea = new JTextArea(10, 30);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        // 添加元素到窗口
        add(nameLabel);
        add(nameField);
        add(addressLabel);
        add(addressField);
        add(phoneLabel);
        add(phoneField);
        add(addButton);
        add(deleteButton);
        add(updateButton);
        add(queryButton);
        add(scrollPane);

        // 添加事件处理
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addContact();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteContact();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateContact();
            }
        });

        queryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                queryContacts();
            }
        });

        setVisible(true);
    }

    // 添加联系人
    private void addContact() {
        String name = nameField.getText();
        String address = addressField.getText();
        String phone = phoneField.getText();
        try {
            contactManager.addContact(name, address, phone);
            resultArea.setText("联系人添加成功！");
        } catch (IOException e) {
            resultArea.setText("添加失败：" + e.getMessage());
        }
    }

    // 删除联系人
    private void deleteContact() {
        String name = nameField.getText();
        try {
            contactManager.deleteContact(name);
            resultArea.setText("联系人删除成功！");
        } catch (IOException e) {
            resultArea.setText("删除失败：" + e.getMessage());
        }
    }

    // 修改联系人
    private void updateContact() {
        String name = nameField.getText();
        String address = addressField.getText();
        String phone = phoneField.getText();
        try {
            contactManager.updateContact(name, address, phone);
            resultArea.setText("联系人修改成功！");
        } catch (IOException e) {
            resultArea.setText("修改失败：" + e.getMessage());
        }
    }

    // 查询所有联系人
    private void queryContacts() {
        try {
            List<String> contacts = contactManager.getAllContacts();
            resultArea.setText("联系人信息：\n");
            for (String contact : contacts) {
                resultArea.append(contact + "\n");
            }
        } catch (IOException e) {
            resultArea.setText("查询失败：" + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new ContactApp();
    }
}
