package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {

    private JTextField loginField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton returnButton;

    public LoginPanel(){
        setupUI();
    }

    private void setupUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        JLabel titleLabel = new JLabel("Se connecter");
        titleLabel.setFont(new Font("Georgia", Font.BOLD, 18));
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(10, 10, 10, 10);
        add(titleLabel, constraints);

        JLabel loginLabel = new JLabel("Login");
        loginLabel.setFont(new Font("Georgia", Font.PLAIN, 14));
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        add(loginLabel, constraints);

        loginField = new JTextField(20);
        constraints.gridx = 1;
        add(loginField, constraints);

        JLabel passwordLabel = new JLabel("Mot de passe");
        passwordLabel.setFont(new Font("Georgia", Font.PLAIN, 14));
        constraints.gridx = 0;
        constraints.gridy = 2;
        add(passwordLabel, constraints);

        passwordField = new JPasswordField(20);
        constraints.gridx = 1;
        add(passwordField, constraints);

        loginButton = new JButton("Connexion");
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        add(loginButton, constraints);

        returnButton = new JButton("Retour");
        constraints.gridy = 4;
        add(returnButton, constraints);
    }
    public String getLoginField() {
        return loginField.getText();
    }

    public String getPasswordField() {
        return new String(passwordField.getPassword());
    }

    public void emptyPasswordField() {
        passwordField.setText("");
    }

    public void setLoginRetourButtonAction(ActionListener action){
        returnButton.addActionListener(action);
    }

    public void setLoginButtonAction(ActionListener action){
        loginButton.addActionListener(action);
    }
}
