package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AccueilPanel extends JPanel {

    private JButton addUserButton;
    private JButton deleteUserButton;
    private JButton readUserButton;
    private JButton updateUserButton;
    private JButton whitelistUserButton;
    private JButton addStoreButton;
    private JButton removeStoreButton;
    private JButton employeesStoreButton;
    private JLabel itemIconLabel;
    private JButton addItemButton;
    private JButton removeItemButton;
    private JButton browseItemButton;
    private JButton increaseItemButton;
    private JButton decreaseItemButton;
    private JButton deconnectionButton;

    public AccueilPanel(){

        setLayout(new GridLayout(1, 4));


        // Icon
        ImageIcon userIcon = new ImageIcon("src\\main\\resources\\user_icon.png");
        Image userImage = userIcon.getImage();
        Image newUserImage = userImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        userIcon = new ImageIcon(newUserImage);
        JLabel userIconLabel = new JLabel(userIcon);
        // Bouttons
        addUserButton = new JButton("Ajouter un User");
        deleteUserButton = new JButton("Retirer un User");
        readUserButton = new JButton("Rechercher un User");
        updateUserButton = new JButton("Update un User");
        whitelistUserButton = new JButton("Whitelister un User");
        // Centrer les bouttons
        userIconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        addUserButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        deleteUserButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        readUserButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        updateUserButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        whitelistUserButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        // Colonne User
        JPanel userColumn = new JPanel();
        userColumn.setLayout(new BoxLayout(userColumn, BoxLayout.Y_AXIS));
        userColumn.add(Box.createRigidArea(new Dimension(0,32)));
        userColumn.add(userIconLabel);
        userColumn.add(Box.createRigidArea(new Dimension(0,32)));
        userColumn.add(addUserButton);
        userColumn.add(Box.createRigidArea(new Dimension(0,16)));
        userColumn.add(deleteUserButton);
        userColumn.add(Box.createRigidArea(new Dimension(0,16)));
        userColumn.add(readUserButton);
        userColumn.add(Box.createRigidArea(new Dimension(0,16)));
        userColumn.add(updateUserButton);
        userColumn.add(Box.createRigidArea(new Dimension(0,16)));
        userColumn.add(whitelistUserButton);

        add(userColumn);

        ImageIcon storeIcon = new ImageIcon("src\\main\\resources\\store_icon.png");
        Image storeImage = storeIcon.getImage();
        Image newStoreImage = storeImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        storeIcon = new ImageIcon(newStoreImage);
        JLabel storeIconLabel = new JLabel(storeIcon);

        addStoreButton = new JButton("Ajouter un Store");
        removeStoreButton = new JButton("Retirer un Store");
        employeesStoreButton = new JButton("Visualiser la liste des employés");

        storeIconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        addStoreButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        removeStoreButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        employeesStoreButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel storeColumn = new JPanel();
        storeColumn.setLayout(new BoxLayout(storeColumn, BoxLayout.Y_AXIS));

        storeColumn.add(Box.createRigidArea(new Dimension(0,32)));
        storeColumn.add(storeIconLabel);
        storeColumn.add(Box.createRigidArea(new Dimension(0,32)));
        storeColumn.add(addStoreButton);
        storeColumn.add(Box.createRigidArea(new Dimension(0,16)));
        storeColumn.add(removeStoreButton);
        storeColumn.add(Box.createRigidArea(new Dimension(0,16)));
        storeColumn.add(employeesStoreButton);

        add(storeColumn);

        ImageIcon itemIcon = new ImageIcon("src\\main\\resources\\item_icon.png");
        Image itemImage = itemIcon.getImage();
        Image newItemImage = itemImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        itemIcon = new ImageIcon(newItemImage);
        itemIconLabel = new JLabel(itemIcon);

        addItemButton = new JButton("Ajouter un nouvel Item");
        removeItemButton = new JButton("Supprimer un Item");
        browseItemButton = new JButton("Visualiser les Items");
        increaseItemButton = new JButton("Augmenter la quantité d'un Item");
        decreaseItemButton = new JButton("Diminuer la quantité d'un Item");
        decreaseItemButton = new JButton("Diminuer la quantité d'un Item");

        itemIconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        addItemButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        removeItemButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        browseItemButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        increaseItemButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        decreaseItemButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel itemColumn = new JPanel();
        itemColumn.setLayout(new BoxLayout(itemColumn, BoxLayout.Y_AXIS));

        itemColumn.add(Box.createRigidArea(new Dimension(0,32)));
        itemColumn.add(itemIconLabel);
        itemColumn.add(Box.createRigidArea(new Dimension(0,32)));
        itemColumn.add(addItemButton);
        itemColumn.add(Box.createRigidArea(new Dimension(0,16)));
        itemColumn.add(removeItemButton);
        itemColumn.add(Box.createRigidArea(new Dimension(0,16)));
        itemColumn.add(browseItemButton);
        itemColumn.add(Box.createRigidArea(new Dimension(0,16)));
        itemColumn.add(increaseItemButton);
        itemColumn.add(Box.createRigidArea(new Dimension(0,16)));
        itemColumn.add(decreaseItemButton);

        add(itemColumn);

        deconnectionButton = new JButton("Se déconnecter");
        add(deconnectionButton);
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
