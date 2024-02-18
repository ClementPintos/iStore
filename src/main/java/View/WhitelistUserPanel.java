package View;

import DAO.UserDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class WhitelistUserPanel extends JDialog {
    private JTable userTable;
    private UserDAO userDAO;

    public WhitelistUserPanel(JFrame parent, UserDAO userDAO) {
        super(parent, "Whitelist User", true);
        this.userDAO = userDAO;

        setModal(true);

        userTable = new JTable();
        userTable.setPreferredScrollableViewportSize(new Dimension(500, 70));
        userTable.setFillsViewportHeight(true);

        setSize(1000, 400);
        setLocationRelativeTo(parent);
        add(new JScrollPane(userTable));
    }


    public void setTableModel(DefaultTableModel model) {
        userTable.setModel(model);
    }
    public JTable getUserTable(){
        return userTable;
    }
}
