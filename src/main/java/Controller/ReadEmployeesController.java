package Controller;

import DAO.StoreDAO;
import DAO.UserDAO;
import View.ReadEmployees.ReadEmployeesMainPanel;

public class ReadEmployeesController {
    private UserDAO userDAO;
    private StoreDAO storeDAO;
    private ReadEmployeesMainPanel parentPanel;

    public ReadEmployeesController(ReadEmployeesMainPanel parentPanel, UserDAO userDAO, StoreDAO storeDAO) {
        this.userDAO = userDAO;
        this.storeDAO = storeDAO;
        this.parentPanel = parentPanel;
    }

    public void refreshPanel() {
        parentPanel.refreshPanel();
    }
}
