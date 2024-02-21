package Controller;

import DAO.UserDAO;
import Model.User;
import View.SigninPanel;
import View.MainWindow;
import Utils.EmailValidator;

import javax.swing.*;
import java.sql.SQLException;

import static Utils.PasswordUtils.hashPassword;

public class SigninController {

    private MainWindow mainWindow;
    private SigninPanel signinPanel;
    private UserDAO userDAO;

    public SigninController(MainWindow mainWindow, SigninPanel signinPanel, UserDAO userDAO){
        this.mainWindow = mainWindow;
        this.signinPanel = signinPanel;
        this.userDAO = userDAO;

        signinPanel.setSigninRetourButtonAction(e -> openLaunchingPanel());

        signinPanel.setSigninButtonAction(e -> {
            if(EmailValidator.emailValide(signinPanel.getEmailField())){
                try {
                    if(signin()){
                        JOptionPane.showMessageDialog(signinPanel, "Utilisateur ajouté avec succès.");
                        signinPanel.emptyEmailField();
                        signinPanel.emptyPasswordField();}
                    else {
                        JOptionPane.showMessageDialog(signinPanel,"Erreur lors de la création du compte. " , "Erreur ", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "Veuillez rentrer un email dans le champs requis.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }

        });
    }

    private void openLaunchingPanel(){
        mainWindow.showLaunchingPanel();
    }
    private boolean signin() throws SQLException {
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
