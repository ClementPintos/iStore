package Controller;

import View.ReadItems.ReadItemsMainPanel;

public class ReadItemsController {

    private final ReadItemsMainPanel parentPanel;

    public ReadItemsController(ReadItemsMainPanel parentPanel) {
        this.parentPanel = parentPanel;
    }

    public void refreshPanel() {
        parentPanel.refreshPanel();
    }

}
