package Controller;

import DAO.UserDAO;
import Model.User;
import Utils.ButtonEditor;
import Utils.ButtonRenderer;
import View.WhitelistUserPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.List;

public class WhitelistUserController {
    private WhitelistUserPanel whitelistUserPanel;
    private UserDAO userDAO;

    public WhitelistUserController(JFrame parent, UserDAO userDAO) throws SQLException {
        this.userDAO = userDAO;
        this.whitelistUserPanel = new WhitelistUserPanel(parent, userDAO);

        refreshPanel();
    }

    public void showPanel() {
        whitelistUserPanel.setVisible(true);
    }

    public void whitelistUser(String email) {
        try {
            userDAO.whitelist(email);
            refreshPanel();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Echec lors du whitelist de cet utilisateur", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void refreshPanel() {
        try {

            if (whitelistUserPanel.getUserTable().isEditing()) {
                whitelistUserPanel.getUserTable().getCellEditor().stopCellEditing();
            }
            List<User> users = userDAO.getNonWhitelistedUsers();
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Email");
            model.addColumn("Whitelist");
            for (User user : users) {
                model.addRow(new Object[]{user.getEmail(), "Whitelister"});
            }
            whitelistUserPanel.setTableModel(model);
            whitelistUserPanel.getUserTable().getColumn("Whitelist").setCellRenderer(new ButtonRenderer());
            whitelistUserPanel.getUserTable().getColumn("Whitelist").setCellEditor(new ButtonEditor(new JCheckBox(), whitelistUserPanel.getUserTable(), this::whitelistUser, this::refreshPanel));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Echec lors de la mise à jour de la liste", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

}
