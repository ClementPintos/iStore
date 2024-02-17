package View;

import Controller.LaunchingController;
import Controller.SigninController;
import Controller.LoginController;
import DAO.UserDAOImpl;
import Database.DbManager;
import javax.swing.*;

public class MainWindow extends JFrame {
    private LaunchingPanel launchingPanel;
    private LoginPanel loginPanel;
    private SigninPanel signinPanel;
    private AccueilPanel accueilPanel;

    public MainWindow(){

        setTitle("iStore");
        setSize(1200,800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        DbManager dbManager = new DbManager();
        UserDAOImpl userDAO = new UserDAOImpl(dbManager);

        launchingPanel = new LaunchingPanel();
        loginPanel = new LoginPanel();
        signinPanel = new SigninPanel();
        accueilPanel = new AccueilPanel();

        new LaunchingController(this, launchingPanel);
        new SigninController(this, signinPanel, userDAO);
        new LoginController(this, loginPanel, userDAO);

        add(launchingPanel);
    }

    public void showLoginPanel(){
        switchPanel(loginPanel);
    }
    public void showLaunchingPanel(){
        switchPanel(launchingPanel);
    }
    public void showSigninPanel(){
        switchPanel(signinPanel);
    }
    public void showAccueilPanel(){
        switchPanel(accueilPanel);
    }

    public void switchPanel(JPanel panel){
        getContentPane().removeAll();
        add(panel);
        revalidate();
        repaint();
    }


}
