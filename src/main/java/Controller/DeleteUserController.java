package Controller;

import DAO.UserDAO;
import Model.User;
import Utils.ButtonEditor;
import Utils.ButtonRenderer;
import View.DeleteUserPanel;
import View.MainWindow;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.List;

public class DeleteUserController {
    private UserDAO userDAO;
    private DeleteUserPanel deleteUserPanel;

    public DeleteUserController(JFrame parent, UserDAO userDAO) throws SQLException {

        this.userDAO = userDAO;

        deleteUserPanel = new DeleteUserPanel(parent, userDAO);

        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("Email");
        model.addColumn("Delete");

        if(((MainWindow)parent).isAdmin()){
            List<User> users = userDAO.getUsers();
            for (User user : users) {
                if (!user.getEmail().equals(((MainWindow)parent).getConnectedUser().getEmail())) {
                    model.addRow(new Object[]{user.getEmail(), "Delete"});
                }
            }
        }
        if(((MainWindow)parent).getConnectedUser() == null){
            deleteUserPanel.setVisible(false);
        }
        else {
            model.addRow(new Object[]{((MainWindow)parent).getConnectedUser().getEmail(), "Delete"});
        }
        deleteUserPanel.setUserTable(model);
        deleteUserPanel.getUserTable().getColumn("Delete").setCellRenderer(new ButtonRenderer());
        deleteUserPanel.getUserTable().getColumn("Delete").setCellEditor(new ButtonEditor(new JCheckBox(), deleteUserPanel.getUserTable(), this::deleteUser, this::refreshPanel));
    }

    public void deleteUser(String email) {
        try {
            userDAO.deleteUser(email);
            if(email.equals(((MainWindow)deleteUserPanel.getParent()).getConnectedUser().getEmail())){
                ((MainWindow)deleteUserPanel.getParent()).deconnexion();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Echec lors de la suppression de cet utilisateur", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void refreshPanel() {
        try {
            List<User> users = userDAO.getUsers();
            DefaultTableModel model = (DefaultTableModel) deleteUserPanel.getUserTable().getModel();
            model.setRowCount(0);
            for (User user : users) {
                model.addRow(new Object[]{user.getEmail(), "Delete"});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Echec lors de la mise à jour de la liste", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void showPanel(){
        deleteUserPanel.setVisible(true);
    }

}