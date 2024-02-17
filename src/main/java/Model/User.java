package Model;

public class User {

    private String email;
    private String pseudo;
    private String password;
    private String role;
    private boolean whitelisted;

    public User(String email, String pseudo, String password){
        this.email = email;
        this.pseudo = pseudo;
        this.password = password;
        this.role = "User";
        this.whitelisted = false;
    }

    public User(String email, String pseudo, String password, String role, boolean whitelisted) {
        this.email = email;
        this.pseudo = pseudo;
        this.password = password;
        this.role = role;
        this.whitelisted = whitelisted;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isWhitelisted() {
        return whitelisted;
    }

    public void setWhitelisted(boolean whitelisted) {
        this.whitelisted = whitelisted;
    }
}
