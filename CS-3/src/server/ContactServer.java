package server;

import ContactManger.Contact;
import ContactManger.ContactManager;

import java.io.*;
import java.net.*;

public class ContactServer {

    private static final int PORT = 12346;
    private static ContactManager contactManager = new ContactManager();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("服务器已启动，等待连接...");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("客户端连接: " + clientSocket.getInetAddress());
                handleClient(clientSocket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket clientSocket) {
        new Thread(() -> {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                String command;
                while ((command = in.readLine()) != null) {
                    if (command.equals("ADD")) {
                        String name = in.readLine();
                        String address = in.readLine();
                        String phone = in.readLine();
                        contactManager.addContact(new Contact(name, address, phone));
                        out.println("联系人添加成功");
                    } else if (command.equals("GET")) {
                        for (Contact contact : contactManager.getContacts()) {
                            out.println(contact);
                        }
                        out.println("END");
                    } else if (command.equals("UPDATE")) {
                        for (Contact contact : contactManager.getContacts()) {
                            out.println(contact);
                        }
                        String oldName = in.readLine();
                        String newName = in.readLine();
                        String newAddress = in.readLine();
                        String newPhone = in.readLine();
                        contactManager.updateContact(oldName, new Contact(newName, newAddress, newPhone));
                        out.println("联系人更新成功");
                    } else if (command.equals("DELETE")) {
                        String name = in.readLine();
                        contactManager.deleteContact(name);
                        out.println("联系人删除成功");
                    } else {
                        out.println("无效命令");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
