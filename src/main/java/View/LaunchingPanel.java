package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LaunchingPanel extends JPanel {

    // Red pour redirection
    private JButton signinRedButton;
    private JButton loginRedButton;

    public LaunchingPanel(){

        setLayout(new GridLayout(1, 2));

        JPanel signinRedPanel = new JPanel();
        signinRedPanel.setBackground(Color.BLACK);
        signinRedPanel.setLayout(new GridBagLayout());
        signinRedButton = new JButton("Créer un compte");
        signinRedButton.setBackground(Color.WHITE);
        signinRedButton.setFont(new Font("Georgia", Font.BOLD, 20));

        signinRedPanel.add(signinRedButton);

        JPanel loginRedPanel = new JPanel();
        loginRedPanel.setBackground(Color.WHITE);
        loginRedPanel.setLayout(new GridBagLayout());
        loginRedButton = new JButton("Se connecter");
        loginRedButton.setBackground(Color.BLACK);
        loginRedButton.setForeground(Color.WHITE);
        loginRedButton.setFont(new Font("Georgia", Font.BOLD, 20));


        loginRedPanel.add(loginRedButton);

        add(signinRedPanel);
        add(loginRedPanel);

    }

    public void setSigninRedButtonAction(ActionListener action){
        signinRedButton.addActionListener(action);
    }
    public void setLoginRedButtonAction(ActionListener action){
        loginRedButton.addActionListener(action);
    }

}
