package View.UpdateUser;

import DAO.StoreDAO;
import DAO.UserDAO;
import Model.Store;
import Model.User;
import View.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UpdateUserMainPanel extends JPanel {
    private UserDAO userDAO;
    private StoreDAO storeDAO;
    private List<User> users;
    private MainWindow mainWindow;

    public UpdateUserMainPanel(UserDAO userDAO, StoreDAO storeDAO, MainWindow mainWindow) {
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
        JPanel header = new JPanel(new GridLayout(1, 5));
        header.add(new JLabel("eMail"));
        header.add(new JLabel("Password"));
        header.add(new JLabel("Pseudo"));
        header.add(new JLabel(""));
        add(header);

        for (User user : users) {
            add(new UpdateUserSousPanel(user, userDAO, storeDAO, this));
        }
        revalidate();
        repaint();
    }

}

