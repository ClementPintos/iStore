package View.DeleteUser;

import DAO.StoreDAO;
import DAO.UserDAO;
import Model.User;
import View.DeleteUser.DeleteUserMainPanel;
import View.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class DeleteUserSousPanel extends JPanel {

    public DeleteUserSousPanel(User user, UserDAO userDAO, StoreDAO storeDAO, DeleteUserMainPanel parentPanel) {
        setLayout(new GridLayout(1, 3));

        JLabel emailLabel = new JLabel(user.getEmail());
        JLabel pseudoLabel = new JLabel(user.getPseudo());
        JLabel roleLabel = new JLabel(user.getRole());
        JLabel storeLabel = new JLabel(storeDAO.getStoreName(user.getStore()));

        JButton deleteButton = new JButton("Supprimer");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int result = JOptionPane.showConfirmDialog(parentPanel, "Etes-vous sûr de vouloir effectuer cette action ?");
                    if (result == JOptionPane.YES_OPTION){
                        userDAO.deleteUser(emailLabel.getText());
                    }
                    if(parentPanel.isOut()){
                        Window window = SwingUtilities.getWindowAncestor(parentPanel);
                        if(window != null){
                            window.dispose();
                        }

                    };
                    parentPanel.refreshPanel();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Echec lors de la suppression de cet utilisateur", "Erreur", JOptionPane.ERROR_MESSAGE);
                };
            }
        });
        add(emailLabel);
        add(roleLabel);
        add(storeLabel);
        add(deleteButton);
    }
}
