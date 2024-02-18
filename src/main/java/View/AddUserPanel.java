package View;

import DAO.UserDAO;

import javax.swing.*;
import java.awt.*;

public class AddUserPanel extends JDialog {

    private JTextField emailField;
    private JTextField pseudoField;
    private JPasswordField passwordField;
    private JComboBox<String> roleComboBox;
    private JButton addButton;

    public AddUserPanel(JFrame parent, UserDAO userDAO){
        super(parent, "Ajouter un utilisateur", true);

        add(Box.createRigidArea(new Dimension(0,32)));
        add(new JLabel("Veuillez rentrer les champs requis pour créer le nouvel utilisateur"));

        setLayout(new GridLayout(0, 2));

        add(new JLabel("eMail"));
        emailField = new JTextField();
        add(emailField);
        add(new JLabel("Pseudo"));
        pseudoField = new JTextField();
        add(pseudoField);
        add(new JLabel("Mot de passe"));
        passwordField = new JPasswordField();
        add(passwordField);
        add(new JLabel("Rôle"));
        String[] roles = {"User", "Admin"};
        roleComboBox = new JComboBox<>(roles);
        roleComboBox.setEnabled(false);
        add(roleComboBox);

        addButton = new JButton("Ajouter");
        add(addButton);

        setSize(1000, 400);
        setLocationRelativeTo(parent);
    }

    // Getters pour les champs et le bouton
    public JTextField getEmailField() {
        return emailField;
    }

    public JTextField getPseudoField() {
        return pseudoField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JComboBox<String> getRoleComboBox() {
        return roleComboBox;
    }

    public JButton getAddButton() {
        return addButton;
    }
}