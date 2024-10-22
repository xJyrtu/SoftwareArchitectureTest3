package server;


import java.io.*;
import java.net.*;

public class ContactServer {
    private static final String DATA_FILE_PATH = "F:\\Projects\\JAVATest3\\CS-2\\src\\data\\data.txt";

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("服务器正在运行...");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ClientHandler implements Runnable {
        private Socket socket;

        ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
                 PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true)) {
                String input;
                while ((input = in.readLine()) != null) {
                    String[] parts = input.split(";");
                    switch (parts[0]) {
                        case "ADD":
                            addContact(parts[1], parts[2], parts[3]);
                            out.println("联系人已成功添加。");
                            break;
                        case "SHOW":
                            out.println(showContacts());
                            break;
                        case "DELETE":
                            deleteContact(parts[1]);
                            out.println("联系人已成功删除。");
                            break;
                        case "UPDATE":
                            out.println(showContacts()); // 展示当前联系人信息
                            updateContact(parts[1], parts[2], parts[3], parts[4]);
                            out.println("联系人已成功更新。");
                            break;
                        default:
                            out.println("无效的命令。");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void addContact(String name, String phone, String addr) {
            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(DATA_FILE_PATH, true), "UTF-8"))) {
                writer.write("name: " + name + "，phone: " + phone + "，addr: " + addr + ": \n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private String showContacts() {
            StringBuilder contacts = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(DATA_FILE_PATH), "UTF-8"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    contacts.append(line).append("\n"); // 每个联系人后换行
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return contacts.toString();
        }

        private void deleteContact(String name) {
            File inputFile = new File(DATA_FILE_PATH);
            File tempFile = new File("F:\\Projects\\JAVATest3\\CS-2\\src\\data\\data.txt");

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile), "UTF-8"));
                 BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tempFile), "UTF-8"))) {
                String line;
                boolean found = false;
                while ((line = reader.readLine()) != null) {
                    if (!line.contains("name：" + name + "，")) {
                        writer.write(line + "\n");
                    } else {
                        found = true;  // 标记已找到
                    }
                }
                if (found) {
                    System.out.println("联系人已成功删除。");
                } else {
                    System.out.println("未找到联系人。");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            // 删除旧文件，重命名临时文件
            inputFile.delete();
            tempFile.renameTo(inputFile);
        }

        private void updateContact(String oldName, String newName, String newPhone, String newAddr) {
            File inputFile = new File(DATA_FILE_PATH);
            File tempFile = new File("F:\\Projects\\JAVATest3\\CS-2\\src\\data\\data.txt");

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile), "UTF-8"));
                 BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tempFile), "UTF-8"))) {
                String line;
                boolean found = false;
                while ((line = reader.readLine()) != null) {
                    if (line.contains("name：" + oldName + "，")) {
                        line = line.replace("name：" + oldName + "，", "name：" + newName + "，")
                                .replace(line.split("，")[1], "phone：" + newPhone)
                                .replace(line.split("，")[2].split("；")[0], "addr：" + newAddr);
                        found = true;  // 标记已找到
                    }
                    writer.write(line + "\n");
                }
                if (!found) {
                    System.out.println("未找到联系人，更新失败。");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            // 删除旧文件，重命名临时文件
            inputFile.delete();
            tempFile.renameTo(inputFile);
        }
    }
}
