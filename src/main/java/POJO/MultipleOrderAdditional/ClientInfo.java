package POJO.MultipleOrderAdditional;

public class ClientInfo {
    private String name;
    private String email;
    private String phoneNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        // разделитель в виде пробела не подойдёт, поскольку в названии товара может быть пробел
        return name + ';' + email + ';' + phoneNumber;
    }
}
