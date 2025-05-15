package model;

public class Contact {
    private String phone;
    private String group;
    private String name;
    private String gender;
    private String address;
    private String dob;
    private String email;

    public Contact(String phone, String group, String name, String gender, String address, String dob, String email) {
        this.phone = phone;
        this.group = group;
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.dob = dob;
        this.email = email;
    }

    // Getters + Setters
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getGroup() { return group; }
    public void setGroup(String group) { this.group = group; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getDob() { return dob; }
    public void setDob(String dob) { this.dob = dob; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return String.format("SĐT: %s | Nhóm: %s | Tên: %s | Giới tính: %s | Địa chỉ: %s | Ngày sinh: %s | Email: %s",
                phone, group, name, gender, address, dob, email);
    }

    public String toCSV() {
        return String.join(",", phone, group, name, gender, address, dob, email);
    }

    public static Contact fromCSV(String line) {
        String[] parts = line.split(",");
        if (parts.length == 7) {
            return new Contact(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6]);
        }
        return null;
    }
}
