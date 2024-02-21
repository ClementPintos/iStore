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
            try {
                if(EmailValidator.emailValide(signinPanel.getEmailField())){
                    if (signin()){
                        signinPanel.showValidationMessage();
                    }
                    else {
                        JOptionPane.showMessageDialog(signinPanel,"Erreur lors de la création du compte. " , "Erreur ", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "Veuillez rentrer un email dans le champs requis.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(signinPanel,"Erreur lors de la création du compte : " + ex.getMessage(), "Erreur ", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void openLaunchingPanel(){
        mainWindow.showLaunchingPanel();
    }
    private boolean signin() throws SQLException {
        String email = signinPanel.getEmailField();
        String password = hashPassword(signinPanel.getPasswordField());
        int id_user = userDAO.getLastUserId() + 1;

        if(userDAO.getUserCount() == 0){
            User newUser = new User(1, email, email, password, "Admin", true, 1);
            return userDAO.addUser(newUser);
        }
        else {
            User newUser = new User(userDAO.getLastUserId(), email, email, password, "User", false, id_user);
            return userDAO.addUser(newUser);
        }
    }
}
