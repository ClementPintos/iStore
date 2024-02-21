package Controller;

import DAO.ItemDAO;
import DAO.StoreDAO;
import DAO.UserDAO;
import View.ReadEmployees.ReadEmployeesMainPanel;
import View.ReadItems.ReadItemsMainPanel;

public class ReadItemsController {

    private ItemDAO itemDAO;
    private StoreDAO storeDAO;
    private ReadItemsMainPanel parentPanel;

    public ReadItemsController(ReadItemsMainPanel parentPanel, ItemDAO itemDAO, StoreDAO storeDAO) {
        this.itemDAO = itemDAO;
        this.storeDAO = storeDAO;
        this.parentPanel = parentPanel;
    }

    public void refreshPanel() {
        parentPanel.refreshPanel();
    }

}
