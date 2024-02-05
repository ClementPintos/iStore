package Model;

public class User {

    private static int nextId = 0;
    private int id;
    private String email;
    private String pseudo;
    private String password;
    private String role;
    private boolean whitelisted = false;

    public User(String email, String pseudo, String password){
        this.id = nextId++;
        this.email = email;
        this.pseudo = pseudo;
        this.password = password;
        this.role = (nextId == 1) ? "Admin" : "User";
    }

    public void direBonjour(){
        System.out.println("Bonjour !");
    }

}
