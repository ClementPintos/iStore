package View;

import javax.swing.*;
import java.awt.*;

public class LaunchingPanel extends JPanel {

    private static final Color BACKGROUND_COLOR_SIGNIN = Color.BLACK;
    private static final Color BACKGROUND_COLOR_LOGIN = Color.WHITE;
    private static final Font BUTTON_FONT = new Font("Georgia", Font.BOLD, 20);

    private JButton signupButton;
    private JButton loginButton;

    public LaunchingPanel(MainWindow mainWindow){
        setLayout(new GridLayout(1, 2));
        setupSigninButton();
        setupLoginButton();
        add(createButtonPanel(signupButton, BACKGROUND_COLOR_SIGNIN));
        add(createButtonPanel(loginButton, BACKGROUND_COLOR_LOGIN));
        signupButton.addActionListener(e -> mainWindow.showSigninPanel());
        loginButton.addActionListener(e -> mainWindow.showLoginPanel());
    }

    private void setupSigninButton() {
        signupButton = new JButton("Créer un compte");
        signupButton.setBackground(Color.WHITE);
        signupButton.setFont(BUTTON_FONT);
    }

    private void setupLoginButton() {
        loginButton = new JButton("Se connecter");
        loginButton.setBackground(Color.BLACK);
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(BUTTON_FONT);
    }

    private JPanel createButtonPanel(JButton button, Color backgroundColor) {
        JPanel panel = new JPanel();
        panel.setBackground(backgroundColor);
        panel.setLayout(new GridBagLayout());
        panel.add(button);
        return panel;
    }
}
