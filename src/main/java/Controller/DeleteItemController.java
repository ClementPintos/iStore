package Controller;

import DAO.ItemDAO;
import DAO.StoreDAO;
import View.DeleteItem.DeleteItemMainPanel;

public class DeleteItemController {
    private final ItemDAO itemDAO;
    private final StoreDAO storeDAO;
    private final DeleteItemMainPanel parentPanel;

    public DeleteItemController(DeleteItemMainPanel parentPanel, ItemDAO itemDAO, StoreDAO storeDAO) {
        this.itemDAO = itemDAO;
        this.storeDAO = storeDAO;
        this.parentPanel = parentPanel;
    }

    public void refreshPanel() {
        parentPanel.refreshPanel();
    }
}
