package Model;

public class User {

    private final int idUser;
    private int idStore;
    private String email;
    private String pseudo;
    private String password;
    private String role;
    private final boolean whitelisted;

    public User(int idUser, String email, String pseudo, String password, String role, Boolean whitelisted, int idStore) {
        this.idUser = idUser;
        this.email = email;
        this.pseudo = pseudo;
        this.password = password;
        this.role = role;
        this.whitelisted = whitelisted;
        this.idStore = idStore;
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
    public void setStore(int idStore) {
        this.idStore = idStore;
    }
    public boolean isWhitelisted() {
        return whitelisted;
    }
    public int getId() {
        return idUser;
    }
    public int getStore() {
        return idStore;
    }
}
