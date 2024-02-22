package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AccueilPanel extends JPanel {

    private final JButton addUserButton;
    private final JButton deleteUserButton;
    private final JButton readUserButton;
    private final JButton updateUserButton;
    private final JButton whitelistUserButton;
    private final JButton addStoreButton;
    private final JButton removeStoreButton;
    private final JButton employeesStoreButton;
    private final JButton addItemButton;
    private final JButton removeItemButton;
    private final JButton browseItemButton;
    private final JButton increaseItemButton;
    private final JButton decreaseItemButton;
    private final JButton deconnectionButton;

    public AccueilPanel(){

        addUserButton = new JButton("Ajouter un User");
        deleteUserButton = new JButton("Retirer un User");
        readUserButton = new JButton("Voir la liste des Users");
        updateUserButton = new JButton("Update un User");
        whitelistUserButton = new JButton("Whitelister un User");
        addStoreButton = new JButton("Ajouter un Store");
        removeStoreButton = new JButton("Retirer un Store");
        employeesStoreButton = new JButton("Visualiser la liste des employés");
        addItemButton = new JButton("Ajouter un nouvel Item");
        removeItemButton = new JButton("Supprimer un Item");
        browseItemButton = new JButton("Visualiser la liste des Items");
        increaseItemButton = new JButton("Augmenter la quantité d'un Item");
        decreaseItemButton = new JButton("Diminuer la quantité d'un Item");
        deconnectionButton = new JButton("Se déconnecter");

        setupUI();
    }

    private void setupUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        ImageIcon userIcon = new ImageIcon("src\\main\\resources\\user_icon.png");
        Image userImage = userIcon.getImage();
        Image newUserImage = userImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        userIcon = new ImageIcon(newUserImage);

        ImageIcon storeIcon = new ImageIcon("src\\main\\resources\\user_icon.png");
        Image storeImage = storeIcon.getImage();
        Image newStoreImage = storeImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        storeIcon = new ImageIcon(newStoreImage);

        ImageIcon itemIcon = new ImageIcon("src\\main\\resources\\user_icon.png");
        Image itemImage = itemIcon.getImage();
        Image newItemImage = itemImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        itemIcon = new ImageIcon(newItemImage);

        // Colonne User
        setupColumn("User", 0, userIcon, addUserButton, deleteUserButton, readUserButton, updateUserButton, whitelistUserButton);

        // Colonne Store
        setupColumn("Store", 1, storeIcon, addStoreButton, removeStoreButton, employeesStoreButton);

        // Colonne Item
        setupColumn("Item", 2, itemIcon, addItemButton, removeItemButton, browseItemButton, increaseItemButton, decreaseItemButton);

        // Boutton Deconnection
        constraints.gridx = 0;
        constraints.gridy = -10;
        constraints.gridwidth = 1;
        constraints.weighty = 0;
        constraints.anchor = GridBagConstraints.PAGE_END;
        add(deconnectionButton, constraints);
    }

    private void setupColumn(String title, int x, ImageIcon icon, JButton... buttons) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = x;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel(title, icon, JLabel.CENTER);
        titleLabel.setFont(new Font("Georgia", Font.BOLD, 18));
        add(titleLabel, constraints);

        for (int i = 0; i < buttons.length; i++) {
            constraints.gridy = i + 1;
            add(buttons[i], constraints);
        }
    }

    public void setAddUserButtonAction(ActionListener action){
        addUserButton.addActionListener(action);
    }
    public void setDeleteUserButtonAction(ActionListener action){
        deleteUserButton.addActionListener(action);
    }
    public void setUpdateUserButtonAction(ActionListener action){
        updateUserButton.addActionListener(action);
    }
    public void setReadUserButtonAction(ActionListener action){
        readUserButton.addActionListener(action);
    }
    public void setWhitelistUserButtonAction(ActionListener action){
        whitelistUserButton.addActionListener(action);
    }
    public void setAddStoreButtonAction(ActionListener action){
        addStoreButton.addActionListener(action);
    }
    public void setDeleteStoreButtonAction(ActionListener action){
        removeStoreButton.addActionListener(action);
    }
    public void setReadEmployeesButtonAction(ActionListener action){
        employeesStoreButton.addActionListener(action);
    }
    public void setDeconnectionButtonAction(ActionListener action){
        deconnectionButton.addActionListener(action);
    }
    public void setAddItemButtonAction(ActionListener action){
        addItemButton.addActionListener(action);
    }
    public void setDeleteItemButtonAction(ActionListener action){
        removeItemButton.addActionListener(action);
    }
    public void setReadItemsButtonAction(ActionListener action){
        browseItemButton.addActionListener(action);
    }
    public void setDecreaseItemButtonAction(ActionListener action){
        decreaseItemButton.addActionListener(action);
    }
    public void setIncreaseItemButtonAction(ActionListener action){
        increaseItemButton.addActionListener(action);
    }
}
