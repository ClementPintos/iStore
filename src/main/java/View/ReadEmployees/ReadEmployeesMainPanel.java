package View.ReadEmployees;

import DAO.StoreDAO;
import DAO.UserDAO;
import Model.User;
import View.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class ReadEmployeesMainPanel extends JPanel {
    private UserDAO userDAO;
    private StoreDAO storeDAO;
    private MainWindow mainWindow;

    public ReadEmployeesMainPanel(MainWindow mainWindow, UserDAO userDAO, StoreDAO storeDAO) {
        this.userDAO = userDAO;
        this.storeDAO = storeDAO;
        this.mainWindow = mainWindow;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public void refreshPanel() {
        removeAll();
        List<User> users;
        try {
            if (mainWindow.isAdmin()) {
                users = userDAO.getUsers();
            } else {
                users = userDAO.getEmployes(mainWindow.getConnectedUser().getStore());
            }
            JPanel header = new JPanel(new GridLayout(1, 4));
            header.add(new JLabel("Email"));
            header.add(new JLabel("Pseudo"));
            header.add(new JLabel("Role"));
            header.add(new JLabel("Store"));
            add(header);

            for (User user : users) {
                if(user.getStore() != 1){
                    add(new ReadEmployeesSousPanel(user, storeDAO));
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erreur lors du rafraîchissement de la page", "Erreur", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
        revalidate();
        repaint();
    }
}
