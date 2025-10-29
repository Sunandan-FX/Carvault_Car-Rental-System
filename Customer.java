public class Customer {
    private String name;
    private String email;
    private String nid;
    private String phoneNumber;

    public Customer(String name, String email, String nid, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.nid = nid;
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }
}