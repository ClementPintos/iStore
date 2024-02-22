package View.AddItem;

import Controller.AddItemController;
import Model.Store;

import javax.swing.*;
import java.awt.*;

public class AddItemSousPanel extends JPanel {
    private final JTextField itemNameField;
    private final JTextField itemPriceField;

    public AddItemSousPanel(Store store, AddItemController controller) {
        setLayout(new GridLayout(1, 4));

        JLabel storeNameLabel = new JLabel(store.getStoreName());
        itemNameField = new JTextField();
        itemPriceField = new JTextField();

        JButton addButton = new JButton("Ajouter");
        addButton.addActionListener(e -> {
            controller.addItem(store, itemNameField.getText(), itemPriceField.getText());
        });

        add(storeNameLabel);
        add(itemNameField);
        add(itemPriceField);
        add(addButton);
    }
}
