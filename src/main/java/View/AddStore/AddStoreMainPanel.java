package View.AddStore;

import DAO.UserDAO;
import Model.User;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class AddStoreMainPanel extends JPanel {
    private UserDAO userDAO;
    private JTextField storeNameField;
    private JButton addButton;
    private List<User> users;
    private JPanel sousPanel;

    public AddStoreMainPanel(UserDAO userDAO) {
        this.userDAO = userDAO;
        setLayout(new BorderLayout());

        storeNameField = new JTextField(20);
        JLabel instructionsLabel = new JLabel("Veuillez cocher les futurs employés du store");
        addButton = new JButton("Ajouter");
        sousPanel = new JPanel();
        sousPanel.setLayout(new BoxLayout(sousPanel, BoxLayout.Y_AXIS));

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.add(new JLabel("Nom du magasin"));
        topPanel.add(storeNameField);
        topPanel.add(instructionsLabel);
        topPanel.add(addButton);

        add(topPanel, BorderLayout.PAGE_START);
        add(sousPanel, BorderLayout.CENTER);
    }

    public void refreshPanel() throws SQLException {
        sousPanel.removeAll();
        users = userDAO.getUnemployed();

        for (User user : users) {
            sousPanel.add(new AddStoreSousPanel(user));
        }
        storeNameField.setText("");
        revalidate();
        repaint();
    }

    public JTextField getStoreNameField() {
        return storeNameField;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JPanel getSousPanel() {
        return sousPanel;
    }
}
