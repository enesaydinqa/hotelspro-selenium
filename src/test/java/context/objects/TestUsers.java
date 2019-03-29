package context.objects;

public class TestUsers
{
    private String employeeNo;
    private String username;
    private String password;
    private String name;
    private String title;
    private String email;
    private boolean isAvailable;

    public TestUsers(String employeeNo, String username, String password, String name, String title, String email) {
        this.employeeNo = employeeNo;
        this.username = username;
        this.password = password;
        this.name = name;
        this.title = title;
        this.email = email;
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
