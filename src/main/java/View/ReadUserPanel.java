package View;

import DAO.UserDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ReadUserPanel extends JDialog{

private JTable userTable;

public ReadUserPanel(JFrame parent, UserDAO userDAO) {
    super(parent, "Read User", true);

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
