package client;


import service.ContactServer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContactClient {
    private ContactServer server = new ContactServer();
    private JFrame frame;
    private JTextArea textArea;

    public ContactClient() {
        frame = new JFrame("个人通讯录系统");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        JLabel nameLabel = new JLabel("姓名：");
        JTextField nameField = new JTextField();
        JLabel addrLabel = new JLabel("住址：");
        JTextField addrField = new JTextField();
        JLabel phoneLabel = new JLabel("电话：");
        JTextField phoneField = new JTextField();

        JButton addButton = new JButton("添加");
        JButton queryButton = new JButton("查询");
        JButton deleteButton = new JButton("删除");
        JButton updateButton = new JButton("修改");

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(addrLabel);
        panel.add(addrField);
        panel.add(phoneLabel);
        panel.add(phoneField);
        panel.add(addButton);
        panel.add(queryButton);
        panel.add(deleteButton);
        panel.add(updateButton);

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        frame.getContentPane().add(panel, BorderLayout.NORTH);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String addr = addrField.getText();
                String phone = phoneField.getText();
                if (!name.isEmpty() && !addr.isEmpty() && !phone.isEmpty()) {
                    server.addContact(new Contact(name, addr, phone));
                    textArea.append("已添加联系人：" + name + "\n");
                } else {
                    textArea.append("请填写完整的信息！\n");
                }
            }
        });

        queryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                Contact contact = server.findContact(name);
                if (contact != null) {
                    textArea.append("查询结果：" + contact + "\n");
                } else {
                    textArea.append("未找到联系人：" + name + "\n");
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                server.deleteContact(name);
                textArea.append("已删除联系人：" + name + "\n");
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String addr = addrField.getText();
                String phone = phoneField.getText();
                if (!name.isEmpty() && !addr.isEmpty() && !phone.isEmpty()) {
                    server.updateContact(name, new Contact(name, addr, phone));
                    textArea.append("已修改联系人：" + name + "\n");
                } else {
                    textArea.append("请填写完整的信息！\n");
                }
            }
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new ContactClient();
    }
}
