package View;

import DAO.StoreDAO;
import DAO.UserDAO;
import Model.Store;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class AddUserPanel extends JPanel {
    private UserDAO userDAO;
    private StoreDAO storeDAO;
    private JTextField emailField;
    private JTextField pseudoField;
    private JTextField passwordField;
    private JComboBox<String> roleSelect;
    private JComboBox<String> storeSelect;
    private JButton addButton;

    public AddUserPanel(UserDAO userDAO, StoreDAO storeDAO) {
        this.userDAO = userDAO;
        this.storeDAO = storeDAO;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        emailField = new JTextField(20);
        pseudoField = new JTextField(20);
        passwordField = new JTextField(20);
        roleSelect = new JComboBox<>(new String[] {"User", "Admin"});
        storeSelect = new JComboBox<>();
        addButton = new JButton("Ajouter");

        add(new JLabel("eMail :"));
        add(emailField);
        add(new JLabel("Pseudo :"));
        add(pseudoField);
        add(new JLabel("Password :"));
        add(passwordField);
        add(new JLabel("Role :"));
        add(roleSelect);
        add(new JLabel("Store :"));
        add(storeSelect);
        add(addButton);
    }

    public void refreshPanel(boolean isAdmin) throws SQLException {
        roleSelect.setSelectedIndex(0);
        if (!isAdmin) {
            roleSelect.setEnabled(false);
        }

        storeSelect.removeAllItems();
        List<Store> stores = storeDAO.getStores();
        for (Store store : stores) {
            storeSelect.addItem(store.getStoreName());
        }
        if (!isAdmin) {
            storeSelect.setSelectedItem("chomage");
            storeSelect.setEnabled(false);
        }
        emailField.setText("");
        passwordField.setText("");
        pseudoField.setText("");
        revalidate();
        repaint();
    }

    public JTextField getEmailField() {
        return emailField;
    }
    public JTextField getPseudoField() {
        return pseudoField;
    }
    public JTextField getPasswordField() {
        return passwordField;
    }
    public JComboBox<String> getRoleSelect() {
        return roleSelect;
    }
    public JComboBox<String> getStoreSelect() {
        return storeSelect;
    }
    public JButton getAddButton() {
        return addButton;
    }

}
