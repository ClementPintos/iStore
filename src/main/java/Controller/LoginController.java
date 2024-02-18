package Controller;

import DAO.UserDAO;
import DAO.UserDAOImpl;
import Model.User;
import View.MainWindow;
import View.LoginPanel;

import static Utils.PasswordUtils.hashPassword;

import javax.swing.*;
import java.sql.SQLException;



public class LoginController {

    private MainWindow mainWindow;
    private LoginPanel loginPanel;
    private UserDAO userDAO;

    public LoginController(MainWindow mainWindow, LoginPanel loginPanel, UserDAOImpl userDAO){
        this.mainWindow = mainWindow;
        this.loginPanel = loginPanel;
        this.userDAO = userDAO;

        loginPanel.setLoginRetourButtonAction(e -> openLaunchingPanel());
        loginPanel.setLoginButtonAction(e -> {
            try {
                login();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private void openLaunchingPanel(){
        mainWindow.showLaunchingPanel();
    }

    private void login() throws SQLException {

        String login = loginPanel.getLoginField();
        String password = hashPassword(loginPanel.getPasswordField());
        User user = userDAO.checkLogin(login, password);
        if(user != null){
            loginPanel.emptyPasswordField();
            mainWindow.setConnectedUser(user);
            mainWindow.showAccueilPanel();
        }
    }

}
