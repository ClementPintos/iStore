package Controller;

import DAO.ItemDAO;
import DAO.StoreDAO;
import View.IncreaseItem.IncreaseItemMainPanel;

public class IncreaseItemController {
    private ItemDAO itemDAO;
    private StoreDAO storeDAO;
    private IncreaseItemMainPanel parentPanel;

    public IncreaseItemController(IncreaseItemMainPanel parentPanel, ItemDAO itemDAO, StoreDAO storeDAO) {
        this.itemDAO = itemDAO;
        this.storeDAO = storeDAO;
        this.parentPanel = parentPanel;
    }

    public void refreshPanel() {
        parentPanel.refreshPanel();
    }
}
