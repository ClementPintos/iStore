package View.DeleteStore;

import DAO.StoreDAO;
import DAO.UserDAO;
import Model.Store;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class DeleteStoreSousPanel extends JPanel {
    private JLabel nomLabel;

    public DeleteStoreSousPanel(Store store, StoreDAO storeDAO, UserDAO userDAO, DeleteStoreMainPanel parentPanel) {
        setLayout(new GridLayout(1, 2));

        String nom = store.getStoreName();
        nomLabel = new JLabel(store.getStoreName());

        JButton deleteButton = new JButton("Supprimer");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int result = JOptionPane.showConfirmDialog(parentPanel, "Etes-vous sûr de vouloir effectuer cette action ?");
                    if (result == JOptionPane.YES_OPTION){
                        userDAO.mettreAuChomage(store.getStoreId());
                        storeDAO.deleteStore(nom);
                    }

                    parentPanel.refreshPanel();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Echec lors de la suppression de ce store", "Erreur", JOptionPane.ERROR_MESSAGE);
                };
            }
        });

        add(nomLabel);
        add(deleteButton);
    }
}
