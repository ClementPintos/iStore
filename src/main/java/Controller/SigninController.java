package Controller;

import DAO.UserDAO;
import Model.User;
import Utils.EmailValidator;
import View.MainWindow;
import View.SigninPanel;

import javax.swing.*;
import java.sql.SQLException;

import static Utils.PasswordUtils.hashPassword;

public class SigninController {

    private final SigninPanel signinPanel;
    private final UserDAO userDAO;

    public SigninController(MainWindow mainWindow, SigninPanel signinPanel, UserDAO userDAO){
        this.signinPanel = signinPanel;
        this.userDAO = userDAO;

        signinPanel.setReturnButtonAction(e -> mainWindow.showLaunchingPanel());
        signinPanel.setSigninButtonAction(e -> {
            if(EmailValidator.emailValide(signinPanel.getEmailField())){
                try {
                    if(createUser()){
                        JOptionPane.showMessageDialog(signinPanel, "Utilisateur ajouté avec succès.");
                        signinPanel.emptyEmailField();
                        signinPanel.emptyPasswordField();}
                    else {
                        JOptionPane.showMessageDialog(signinPanel,"Erreur lors de la création du compte. " , "Erreur ", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(signinPanel, "Une erreur est survenue lors de la création du compte.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "Veuillez rentrer un email dans le champs requis.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private boolean createUser() throws SQLException {
        String email = signinPanel.getEmailField();
        String password = hashPassword(signinPanel.getPasswordField());
        int idUser = userDAO.getLastUserId() + 1;
        int idStore = 1;

        if(userDAO.getUserCount() == 0){
            User newUser = new User(1, email, email, password, "Admin", true, 1);
            return userDAO.addUser(newUser);
        }
        else {
            User newUser = new User(idUser, email, email, password, "User", false, 1);
            return userDAO.addUser(newUser);
        }
    }
}
