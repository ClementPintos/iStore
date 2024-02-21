package Controller;

import DAO.ItemDAO;
import DAO.StoreDAO;
import View.AddItem.AddItemMainPanel;

import javax.swing.*;

public class AddItemController {
    private ItemDAO itemDAO;
    private StoreDAO storeDAO;
    private AddItemMainPanel parentPanel;

    public AddItemController(AddItemMainPanel parentPanel, ItemDAO itemDAO, StoreDAO storeDAO) {
        this.itemDAO = itemDAO;
        this.storeDAO = storeDAO;
        this.parentPanel = parentPanel;
    }

    public void refreshPanel() {
        parentPanel.refreshPanel();
    }
}
