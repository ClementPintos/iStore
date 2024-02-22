package Controller;

import DAO.StoreDAO;
import DAO.UserDAO;
import Model.User;
import View.AddUserPanel;
import View.MainWindow;

import javax.swing.*;
import java.sql.SQLException;

import static Utils.PasswordUtils.hashPassword;

public class AddUserController {

    public AddUserController(UserDAO userDAO, StoreDAO storeDAO, AddUserPanel panel, MainWindow mainWindow) {

        panel.getAddButton().addActionListener(e -> {
            try {
                int userId = userDAO.getLastUserId() + 1;
                String email = panel.getEmail();
                String pseudo = panel.getPseudo();
                String password = hashPassword(panel.getPassword());
                String role = panel.getRole();
                if(mainWindow.isAdmin()){
                    System.out.println("ok");
                    System.out.println("pas ok");
                }
                boolean whitelisted = mainWindow.isAdmin();
                int storeId = storeDAO.getStoreId(panel.getStore());

                User user = new User(userId, email, pseudo, password, role, whitelisted, storeId);

                if (userDAO.addUser(user)) {
                    JOptionPane.showMessageDialog(panel, "Utilisateur ajouté avec succès.");
                }
                panel.refreshPanel(whitelisted);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(panel, "Une erreur est survenue lors de l'ajout de l'utilisateur.");
                ex.printStackTrace();
            }
        });
    }
}
