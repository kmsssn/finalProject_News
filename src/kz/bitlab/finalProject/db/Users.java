package kz.bitlab.finalProject.db;

public class Users {
    private Long id;
    private String email;
    private String password;
    private String fullName;
    private int role;

    public Users(){}

    public Users(Long id, String email, String password, String fullName, int role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.role = role;
    }
    public Users(Long id, String email, String password, String fullName){
        this.id=id;
        this.email=email;
        this.password=password;
        this.fullName=fullName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
