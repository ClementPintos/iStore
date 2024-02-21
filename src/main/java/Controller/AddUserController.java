package Controller;

import DAO.StoreDAO;
import DAO.UserDAO;
import Model.User;
import View.MainWindow;
import View.AddUserPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import static Utils.PasswordUtils.hashPassword;

public class AddUserController {
    private UserDAO userDAO;
    private StoreDAO storeDAO;
    private AddUserPanel panel;
    private MainWindow mainWindow;

    public AddUserController(UserDAO userDAO, StoreDAO storeDAO, AddUserPanel panel, MainWindow mainWindow) {
        this.userDAO = userDAO;
        this.storeDAO = storeDAO;
        this.panel = panel;
        this.mainWindow = mainWindow;

        panel.getAddButton().addActionListener(new ActionListener() {
            int userId = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    userId = userDAO.getLastUserId()+1;
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                String email = panel.getEmailField().getText();
                String pseudo = panel.getPseudoField().getText();
                String password = hashPassword(panel.getPasswordField().getText());
                String role = (String) panel.getRoleSelect().getSelectedItem();
                boolean whitelisted = mainWindow.isAdmin();
                int storeId = storeDAO.getStoreId(String.valueOf(panel.getStoreSelect().getSelectedItem()));

                try {
                    User user = new User(userId, email, pseudo, password, role, whitelisted, storeId);
                    if(userDAO.addUser(user)){
                        JOptionPane.showMessageDialog(panel, "Utilisateur ajouté avec succès.");
                    };
                    panel.refreshPanel(whitelisted);
                } catch (SQLException ex) {
                    ex.printStackTrace();

                }
            }
        });
    }
}
