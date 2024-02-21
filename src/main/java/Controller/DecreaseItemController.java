package Controller;

import DAO.ItemDAO;
import DAO.StoreDAO;
import View.DecreaseItem.DecreaseItemMainPanel;

public class DecreaseItemController {
    private ItemDAO itemDAO;
    private StoreDAO storeDAO;
    private DecreaseItemMainPanel parentPanel;

    public DecreaseItemController(DecreaseItemMainPanel parentPanel, ItemDAO itemDAO, StoreDAO storeDAO) {
        this.itemDAO = itemDAO;
        this.storeDAO = storeDAO;
        this.parentPanel = parentPanel;
    }

    public void refreshPanel() {
        parentPanel.refreshPanel();
    }
}
