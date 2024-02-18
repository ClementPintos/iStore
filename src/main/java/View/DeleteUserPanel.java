package View;

import DAO.UserDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class DeleteUserPanel extends JDialog {
    private JTable userTable;

    public DeleteUserPanel(JFrame parent, UserDAO userDAO) {
        super(parent, "Delete User", true);

        setModal(true);
        setSize(1000, 400);
        setLocationRelativeTo(parent);
    }

    public void setUserTable(DefaultTableModel model) {
        userTable = new JTable(model);
        add(new JScrollPane(userTable));
        this.validate();
    }

    public JTable getUserTable() {
        return userTable;
    }


}