import DAO.UserDAOImpl;
import Database.DbManager;
import Model.User;
import View.MainWindow;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainWindow();
            }
        });

    }
    
}
