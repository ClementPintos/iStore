package View.ReadEmployees;

import DAO.StoreDAO;
import Model.User;

import javax.swing.*;
import java.awt.*;

public class ReadEmployeesSousPanel extends JPanel {

    public ReadEmployeesSousPanel(User user, StoreDAO storeDAO) {

        setLayout(new GridLayout(1, 4));

        JLabel emailLabel = new JLabel(user.getEmail());
        JLabel pseudoLabel = new JLabel(user.getPseudo());
        JLabel roleLabel = new JLabel(user.getRole());
        JLabel storeLabel = new JLabel(storeDAO.getStoreName(user.getStore()));

        add(emailLabel);
        add(pseudoLabel);
        add(roleLabel);
        add(storeLabel);
    }
}
