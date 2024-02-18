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

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        Dimension fieldSize = new Dimension(200, 20);

        JLabel titleLabel = new JLabel("Se connecter");
        titleLabel.setFont(new Font("Georgia", Font.BOLD, 18));
        add(titleLabel);

        add(Box.createRigidArea(new Dimension(0,100)));

        loginField = new JTextField();
        loginField.setPreferredSize(fieldSize);

        passwordField = new JPasswordField();
        passwordField.setPreferredSize(fieldSize);

        loginButton = new JButton("Connexion");
        returnButton = new JButton("Retour");

        JLabel loginLabel = new JLabel("Login");
        loginLabel.setFont(new Font("Georgia", Font.PLAIN, 14));
        JPanel loginPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        loginPanel.add(loginLabel);
        loginPanel.add(loginField);
        add(loginPanel);

        JLabel passwordLabel = new JLabel("Mot de passe");
        passwordLabel.setFont(new Font("Georgia", Font.PLAIN, 14));
        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);
        add(passwordPanel);

        add(loginButton);

        add(Box.createRigidArea(new Dimension(0,10)));

        add(returnButton);
    }

    public String getLoginField() {
        return loginField.getText();
    }

    public String getPasswordField() {
        return passwordField.getText();
    }
    public void emptyPasswordField() {
        passwordField.setText("");
    }

    public void setLoginRetourButtonAction(ActionListener action){
        returnButton.addActionListener(action);
    }

    public void setLoginButtonAction(ActionListener action){
        try{
            loginButton.addActionListener(action);
        }catch (Error e){
            System.out.println("Erreur");
        }
    }
}
