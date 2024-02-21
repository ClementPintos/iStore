package View.UpdateUser;

import DAO.StoreDAO;
import DAO.UserDAO;
import Model.Store;
import Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import static Utils.PasswordUtils.hashPassword;

public class UpdateUserSousPanel extends JPanel {
    private int userId;
    private JTextField emailField;
    private JTextField pseudoField;
    private JTextField passwordField;
    private JComboBox<String> roleComboBox;
    private JComboBox<String> storeComboBox;

    public UpdateUserSousPanel(User user, UserDAO userDAO, StoreDAO storeDAO, UpdateUserMainPanel parentPanel) {
        setLayout(new GridLayout(1, 7));

        userId = user.getId();
        emailField = new JTextField(user.getEmail());
        passwordField = new JTextField();
        pseudoField = new JTextField(user.getPseudo());

        String[] roles = {"User", "Admin"};
        roleComboBox = new JComboBox<>(roles);
        roleComboBox.setSelectedItem(user.getRole());

        List<Store> stores = null;
        try {
            stores = storeDAO.getStores();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erreur lors de le la mise à jour de l'utilisateur", "Erreur", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
        String[] storeNames = new String[stores.size()];
        for (int i = 0; i < stores.size(); i++) {
            storeNames[i] = stores.get(i).getStoreName();
        }
        storeComboBox = new JComboBox<>(storeNames);
        storeComboBox.setSelectedItem(storeDAO.getStoreName(user.getStore()));


        JButton updateButton = new JButton("Mettre à jour");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dialogResult = JOptionPane.showConfirmDialog(null, "Etes-vous sûr de vouloir réaliser cette action ?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if(dialogResult == JOptionPane.YES_OPTION){
                    user.setEmail(emailField.getText());
                    if(!passwordField.getText().isEmpty()){
                        user.setPassword(hashPassword(passwordField.getText()));
                    }
                    user.setPseudo(pseudoField.getText());
                    user.setStore(storeDAO.getStoreId((String) storeComboBox.getSelectedItem()));
                    user.setRole((String) roleComboBox.getSelectedItem());

                    try {
                        userDAO.updateUser(userId, user);
                        parentPanel.refreshPanel();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Erreur lors de le la mise à jour de l'utilisateur", "Erreur", JOptionPane.ERROR_MESSAGE);
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        add(emailField);
        add(passwordField);
        add(pseudoField);
        add(roleComboBox);
        add(storeComboBox);
        add(updateButton);
    }
}
