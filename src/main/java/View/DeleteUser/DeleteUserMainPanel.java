package View.DeleteUser;

import DAO.StoreDAO;
import DAO.UserDAO;
import Model.User;
import View.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeleteUserMainPanel extends JPanel {
    private UserDAO userDAO;
    private StoreDAO storeDAO;
    private List<User> users;
    private MainWindow mainWindow;

    public DeleteUserMainPanel(UserDAO userDAO, StoreDAO storeDAO, MainWindow mainWindow) {
        this.userDAO = userDAO;
        this.storeDAO = storeDAO;
        this.mainWindow = mainWindow;
        try {
            this.users = userDAO.getUsers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public void refreshPanel() {
        removeAll();
        if (mainWindow.isAdmin()){
            try {
                this.users = userDAO.getUsers();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            try {
                this.users = new ArrayList<>();
                this.users.add(userDAO.getUser(mainWindow.getConnectedUser().getEmail()));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        JPanel header = new JPanel(new GridLayout(1, 4));
        header.add(new JLabel("eMail"));
        header.add(new JLabel("Role"));
        header.add(new JLabel("Store"));
        header.add(new JLabel(""));
        add(header);

        for (User user : users) {
            add(new DeleteUserSousPanel(user, userDAO, storeDAO, this));
        }
        revalidate();
        repaint();
    }

    public boolean isOut(){
        try {
            // On verifie si l'utilisateur connecté s'est supprimé
            String email = String.valueOf(userDAO.getUser(mainWindow.getConnectedUser().getEmail()));
            if(email == null){
                mainWindow.deconnexion();
                return true;
            };
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

