package View;

import DAO.StoreDAO;
import Model.Store;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class AddUserPanel extends JPanel {
    private final StoreDAO storeDAO;
    private final JTextField emailField;
    private final JTextField pseudoField;
    private final JTextField passwordField;
    private final JComboBox<String> roleSelect;
    private final JComboBox<String> storeSelect;
    private final JButton addButton;

    public AddUserPanel(StoreDAO storeDAO) {
        this.storeDAO = storeDAO;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        emailField = new JTextField(20);
        pseudoField = new JTextField(20);
        passwordField = new JTextField(20);
        roleSelect = new JComboBox<>(new String[] {"User", "Admin"});
        storeSelect = new JComboBox<>();
        addButton = new JButton("Ajouter");

        addComponents();
    }

    private void addComponents() {
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

    private void resetFields() {
        emailField.setText("");
        passwordField.setText("");
        pseudoField.setText("");
    }

    private void updateRoleSelect(boolean isAdmin) {
        roleSelect.setSelectedIndex(0);
        if (!isAdmin) {
            roleSelect.setEnabled(false);
        }
    }

    private void updateStoreSelect(boolean isAdmin) throws SQLException {
        storeSelect.removeAllItems();
        List<Store> stores = storeDAO.getStores();
        for (Store store : stores) {
            storeSelect.addItem(store.getStoreName());
        }
        if (!isAdmin) {
            storeSelect.setSelectedItem("chomage");
            storeSelect.setEnabled(false);
        }
    }

    public void refreshPanel(boolean isAdmin) throws SQLException {
        resetFields();
        updateRoleSelect(isAdmin);
        updateStoreSelect(isAdmin);
        revalidate();
        repaint();
    }

    public String getEmail() {
        return emailField.getText();
    }

    public String getPseudo() {
        return pseudoField.getText();
    }

    public String getPassword() {
        return passwordField.getText();
    }

    public String getRole() {
        return (String) roleSelect.getSelectedItem();
    }

    public String getStore() {
        return (String) storeSelect.getSelectedItem();
    }

    public JButton getAddButton() {
        return addButton;
    }
}
