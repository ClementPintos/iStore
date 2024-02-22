package View.ReadUser;

import DAO.UserDAO;
import Model.User;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class ReadUserMainPanel extends JPanel{

    private final UserDAO userDAO;
    private List<User> users;

    public ReadUserMainPanel(UserDAO userDAO) {
        this.userDAO = userDAO;
        try {
            this.users = userDAO.getUsers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public void refreshPanel() {
        removeAll();
        try {
            this.users = userDAO.getUsers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        JPanel header = new JPanel(new GridLayout(1, 3));
        header.add(new JLabel("eMail"));
        header.add(new JLabel("Peudo"));
        header.add(new JLabel("Role"));
        add(header);

        for (User user : users) {
            add(new ReadUserSousPanel(user));
        }
        revalidate();
        repaint();
    }

}
