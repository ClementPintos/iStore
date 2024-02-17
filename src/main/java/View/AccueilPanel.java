package View;

import javax.swing.*;
import java.awt.*;

public class AccueilPanel extends JPanel {

    public AccueilPanel(){

        setLayout(new GridLayout(1, 3));

        JLabel addUserLabel = new JLabel("Utilisateur");
        ImageIcon userIcon = new ImageIcon("resources/user_icon.png");

        JButton addUserButton = new JButton("Ajouter un User");
        JButton removeUserButton = new JButton("Retirer un User");

        JPanel userColumn = new JPanel(new BorderLayout());
        userColumn.add()

    }

}
