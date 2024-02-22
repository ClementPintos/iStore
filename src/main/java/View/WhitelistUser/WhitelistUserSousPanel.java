package View.WhitelistUser;

import DAO.UserDAO;
import Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class WhitelistUserSousPanel extends JPanel {
    private final JLabel emailLabel;

    public WhitelistUserSousPanel(User user, UserDAO userDAO, WhitelistUserMainPanel parentPanel) {
        setLayout(new GridLayout(1, 3));

        String eMail = user.getEmail();
        emailLabel = new JLabel(eMail);

        JButton whitelistButton = new JButton("Whitelister");
        whitelistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    userDAO.whitelist(eMail);
                    parentPanel.refreshPanel();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Echec lors du whitelisting de cet utilisateur", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        JButton deleteButton = new JButton("Supprimer");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    userDAO.deleteUser(eMail);
                    parentPanel.refreshPanel();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Echec lors de la suppression de cet utilisateur", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        add(emailLabel);
        add(whitelistButton);
        add(deleteButton);
    }
}
