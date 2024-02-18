package View;

import Controller.AccueilController;
import Controller.LaunchingController;
import Controller.SigninController;
import Controller.LoginController;
import DAO.UserDAOImpl;
import Database.DbManager;
import Model.User;

import javax.swing.*;

public class MainWindow extends JFrame {
    private LaunchingPanel launchingPanel;
    private LoginPanel loginPanel;
    private SigninPanel signinPanel;
    private AccueilPanel accueilPanel;

    private User connectedUser;

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
        new AccueilController(this, accueilPanel, userDAO);

        add(launchingPanel);

    }

    public void setConnectedUser(User user){
        this.connectedUser = user;
    }
    public User getConnectedUser(){
        return this.connectedUser;
    }

    public void deconnexion(){
        setConnectedUser(null);
        showLaunchingPanel();
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
    public boolean isAdmin(){
        User connectedUser = getConnectedUser();
        return connectedUser.getRole().equals("Admin");
    }

}
