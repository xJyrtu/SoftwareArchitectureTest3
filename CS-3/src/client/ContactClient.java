package client;

import java.io.*;
import java.net.*;

public class ContactClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int PORT = 12346;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            String command;

            while (true) {
                System.out.println("请输入操作: ADD, GET, UPDATE, DELETE 或者 EXIT");
                command = userInput.readLine();

                if (command.equalsIgnoreCase("EXIT")) {
                    break;
                }

                switch (command.toUpperCase()) {
                    case "ADD":
                        System.out.print("姓名: ");
                        String name = userInput.readLine();
                        System.out.print("住址: ");
                        String address = userInput.readLine();
                        System.out.print("电话: ");
                        String phone = userInput.readLine();
                        out.println("ADD");
                        out.println(name);
                        out.println(address);
                        out.println(phone);
                        System.out.println(in.readLine()); // Server response
                        break;

                    case "GET":
                        out.println("GET");
                        String response;
                        while (!(response = in.readLine()).equals("END")) {
                            System.out.println(response);
                        }
                        break;

                    case "UPDATE":
                        out.println("GET");
                        while (!(response = in.readLine()).equals("END")) {
                            System.out.println(response);
                        }
                        System.out.print("旧姓名: ");
                        String oldName = userInput.readLine();
                        System.out.print("新姓名: ");
                        String newName = userInput.readLine();
                        System.out.print("新住址: ");
                        String newAddress = userInput.readLine();
                        System.out.print("新电话: ");
                        String newPhone = userInput.readLine();
                        out.println("UPDATE");
                        out.println(oldName);
                        out.println(newName);
                        out.println(newAddress);
                        out.println(newPhone);
                        System.out.println(in.readLine()); // Server response
                        break;

                    case "DELETE":
                        System.out.print("姓名: ");
                        String delName = userInput.readLine();
                        out.println("DELETE");
                        out.println(delName);
                        System.out.println(in.readLine()); // Server response
                        break;

                    default:
                        System.out.println("无效操作");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
