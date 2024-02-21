package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
public class SigninPanel extends JPanel {

    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton signinButton;
    private JButton returnButton;

    public SigninPanel(){

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        Dimension fieldSize = new Dimension(200, 20);

        JLabel titleLabel = new JLabel("Créer un compte");
        titleLabel.setFont(new Font("Georgia", Font.BOLD, 18));
        add(titleLabel);

        add(Box.createRigidArea(new Dimension(0,100)));

        emailField = new JTextField();
        emailField.setPreferredSize(fieldSize);

        passwordField = new JPasswordField();
        passwordField.setPreferredSize(fieldSize);

        signinButton = new JButton("Valider");
        returnButton = new JButton("Retour");

        JLabel emailLabel = new JLabel("eMail");
        emailLabel.setFont(new Font("Georgia", Font.PLAIN, 14));
        JPanel emailPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        emailPanel.add(emailLabel);
        emailPanel.add(emailField);
        add(emailPanel);

        JLabel passwordLabel = new JLabel("Mot de passe");
        passwordLabel.setFont(new Font("Georgia", Font.PLAIN, 14));
        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);
        add(passwordPanel);

        add(signinButton);

        add(Box.createRigidArea(new Dimension(0,10)));

        add(returnButton);

    }
    public void setSigninRetourButtonAction(ActionListener action){
        returnButton.addActionListener(action);
    }
    public void setSigninButtonAction(ActionListener action){
        try{
                signinButton.addActionListener(action);
        }catch (Error e){
            System.out.println("Erreur");
        }
    }

    public String getEmailField() {
        return emailField.getText();
    }
    public void emptyEmailField() {
        emailField.setText("");
    }
    public void emptyPasswordField() {
        passwordField.setText("");
    }

    public String getPasswordField() {
        return passwordField.getText();
    }

}
