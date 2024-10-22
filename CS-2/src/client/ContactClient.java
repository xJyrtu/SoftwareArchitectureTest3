package client;


import java.io.*;
import java.net.*;

public class ContactClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 12345);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
             PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in, "UTF-8"))) {

            String command;
            while (true) {
                System.out.println("请输入命令（ADD, SHOW, DELETE, UPDATE, EXIT）：");
                command = userInput.readLine().toUpperCase();  // 转换为大写
                if (command.equals("EXIT")) {
                    break;
                }
                if (command.equals("ADD")) {
                    System.out.println("请输入姓名：");
                    String name = userInput.readLine();
                    System.out.println("请输入电话：");
                    String phone = userInput.readLine();
                    System.out.println("请输入地址：");
                    String addr = userInput.readLine();
                    out.println("ADD;" + name + ";" + phone + ";" + addr);
                    System.out.println(in.readLine());  // 显示成功提示
                } else if (command.equals("SHOW")) {
                    out.println("SHOW");
                    String response = in.readLine();
                    System.out.println("联系人列表：");
                    System.out.println(response); // 展示联系人
                } else if (command.equals("DELETE")) {
                    System.out.println("请输入要删除的姓名：");
                    String name = userInput.readLine();
                    out.println("DELETE;" + name);
                    System.out.println(in.readLine());  // 显示成功提示
                } else if (command.equals("UPDATE")) {
                    System.out.println("请输入要更新的旧姓名：");
                    String oldName = userInput.readLine();
                    // 显示当前联系人信息
                    System.out.println("当前联系人信息：");
                    out.println("SHOW");
                    System.out.println(in.readLine());

                    System.out.println("请输入新姓名：");
                    String newName = userInput.readLine();
                    System.out.println("请输入新电话：");
                    String newPhone = userInput.readLine();
                    System.out.println("请输入新地址：");
                    String newAddr = userInput.readLine();
                    out.println("UPDATE;" + oldName + ";" + newName + ";" + newPhone + ";" + newAddr);
                    System.out.println(in.readLine());  // 显示成功提示
                } else {
                    System.out.println("无效的命令。");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
