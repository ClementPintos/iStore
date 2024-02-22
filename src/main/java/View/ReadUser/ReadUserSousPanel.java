package View.ReadUser;

import Model.User;

import javax.swing.*;
import java.awt.*;

public class ReadUserSousPanel extends JPanel{

    public ReadUserSousPanel(User user) {
        setLayout(new GridLayout(1, 3));

        String eMail = user.getEmail();
        JLabel emailLabel = new JLabel(eMail);
        String pseudo = user.getPseudo();
        JLabel pseudoLabel = new JLabel(pseudo);
        String role = user.getRole();
        JLabel roleLabel = new JLabel(role);

        add(emailLabel);
        add(pseudoLabel);
        add(roleLabel);
    }

}
