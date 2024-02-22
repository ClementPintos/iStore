package Controller;

import DAO.UserDAO;
import DAO.UserDAOImpl;
import Model.User;
import View.LoginPanel;
import View.MainWindow;

import java.sql.SQLException;

import static Utils.PasswordUtils.hashPassword;



public class LoginController {

    private final MainWindow mainWindow;
    private final LoginPanel loginPanel;
    private final UserDAO userDAO;

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
