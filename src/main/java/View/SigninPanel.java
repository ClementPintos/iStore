package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class SigninPanel extends JPanel {

    private static final Dimension FIELD_SIZE = new Dimension(200, 30);
    private static final Font LABEL_FONT = new Font("Georgia", Font.PLAIN, 14);
    private static final Font TITLE_FONT = new Font("Georgia", Font.BOLD, 18);
    private static final Color BUTTON_COLOR = new Color(70, 130, 180);
    private static final Color PANEL_COLOR = new Color(240, 248, 255);

    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton signinButton;
    private JButton returnButton;

    public SigninPanel(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(PANEL_COLOR);
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setupUI();
    }

    private void setupUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        JLabel titleLabel = new JLabel("Créer un compte");
        titleLabel.setFont(new Font("Georgia", Font.BOLD, 18));
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(10, 10, 10, 10);
        add(titleLabel, constraints);

        JLabel emailLabel = new JLabel("eMail");
        emailLabel.setFont(new Font("Georgia", Font.PLAIN, 14));
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        add(emailLabel, constraints);

        emailField = new JTextField(20);
        constraints.gridx = 1;
        add(emailField, constraints);

        JLabel passwordLabel = new JLabel("Mot de passe");
        passwordLabel.setFont(new Font("Georgia", Font.PLAIN, 14));
        constraints.gridx = 0;
        constraints.gridy = 2;
        add(passwordLabel, constraints);

        passwordField = new JPasswordField(20);
        constraints.gridx = 1;
        add(passwordField, constraints);

        signinButton = new JButton("Valider");
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        add(signinButton, constraints);

        returnButton = new JButton("Retour");
        constraints.gridy = 4;
        add(returnButton, constraints);
    }

    public void setSigninButtonAction(ActionListener action){
        signinButton.addActionListener(action);
    }

    public void setReturnButtonAction(ActionListener action){
        returnButton.addActionListener(action);
    }

    public String getEmailField() {
        return emailField.getText();
    }

    public void emptyEmailField() {
        emailField.setText("");
    }

    public String getPasswordField() {
        return passwordField.getText();
    }

    public void emptyPasswordField() {
        passwordField.setText("");
    }
}
