package View;

import Controller.AccueilController;
import Controller.LoginController;
import Controller.SigninController;
import DAO.ItemDAOImpl;
import DAO.StoreDAOImpl;
import DAO.UserDAOImpl;
import Database.DbManager;
import Model.User;

import javax.swing.*;

public class MainWindow extends JFrame {
    private final LaunchingPanel launchingPanel;
    private final LoginPanel loginPanel;
    private final SigninPanel signinPanel;
    private final AccueilPanel accueilPanel;

    private User connectedUser;

    public MainWindow(){

        setTitle("iStore");
        setSize(1200,800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        DbManager dbManager = new DbManager();
        UserDAOImpl userDAO = new UserDAOImpl(dbManager);
        StoreDAOImpl storeDAO = new StoreDAOImpl(dbManager);
        ItemDAOImpl itemDAO = new ItemDAOImpl(dbManager);

        launchingPanel = new LaunchingPanel(this);
        loginPanel = new LoginPanel();
        signinPanel = new SigninPanel();
        accueilPanel = new AccueilPanel();

        new SigninController(this, signinPanel, userDAO);
        new LoginController(this, loginPanel, userDAO);
        new AccueilController(this, accueilPanel, userDAO, storeDAO, itemDAO);

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
        revalidate();
        repaint();
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
