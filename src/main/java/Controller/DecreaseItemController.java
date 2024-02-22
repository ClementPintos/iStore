package Controller;

import DAO.ItemDAO;
import DAO.StoreDAO;
import View.DecreaseItem.DecreaseItemMainPanel;

public class DecreaseItemController {
    private final ItemDAO itemDAO;
    private final StoreDAO storeDAO;
    private final DecreaseItemMainPanel parentPanel;

    public DecreaseItemController(DecreaseItemMainPanel parentPanel, ItemDAO itemDAO, StoreDAO storeDAO) {
        this.itemDAO = itemDAO;
        this.storeDAO = storeDAO;
        this.parentPanel = parentPanel;
    }

    public void refreshPanel() {
        parentPanel.refreshPanel();
    }
}
