package Controller;

import DAO.ItemDAO;
import DAO.StoreDAO;
import View.DeleteItem.DeleteItemMainPanel;

import javax.swing.*;

public class DeleteItemController {
    private ItemDAO itemDAO;
    private StoreDAO storeDAO;
    private DeleteItemMainPanel parentPanel;

    public DeleteItemController(DeleteItemMainPanel parentPanel, ItemDAO itemDAO, StoreDAO storeDAO) {
        this.itemDAO = itemDAO;
        this.storeDAO = storeDAO;
        this.parentPanel = parentPanel;
    }

    public void refreshPanel() {
        parentPanel.refreshPanel();
    }
}
