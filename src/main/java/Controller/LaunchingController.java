package Controller;

import View.LaunchingPanel;
import View.MainWindow;

public class LaunchingController {

    private MainWindow mainWindow;
    private LaunchingPanel launchingPanel;

    public LaunchingController(MainWindow mainWindow, LaunchingPanel launchingPanel){
        this.mainWindow = mainWindow;
        this.launchingPanel = launchingPanel;

        launchingPanel.setLoginRedButtonAction(e -> openLoginPanel());
        launchingPanel.setSigninRedButtonAction(e -> openSigninPanel());

    }

    private void openLoginPanel(){
        mainWindow.showLoginPanel();
    }
    private void openSigninPanel(){
        mainWindow.showSigninPanel();
    }
}
