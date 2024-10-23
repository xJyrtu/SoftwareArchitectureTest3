package client;

public class Contact {
    private String name;
    private String addr;
    private String phone;

    public Contact(String name, String addr, String phone) {
        this.name = name;
        this.addr = addr;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getAddr() {
        return addr;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "name：" + name + "，addr：" + addr + "，phone：" + phone;
    }
}
