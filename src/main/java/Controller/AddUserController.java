package Controller;

import DAO.UserDAO;
import Model.User;
import Utils.EmailValidator;
import View.AddUserPanel;
import View.MainWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import static Utils.PasswordUtils.hashPassword;

public class AddUserController {

    private UserDAO userDAO;
    private AddUserPanel addUserPanel;

    public AddUserController(JFrame parent, UserDAO userDAO){
        this.userDAO = userDAO;
        addUserPanel = new AddUserPanel(parent, userDAO);

        if(((MainWindow)parent).isAdmin()){
            addUserPanel.getRoleComboBox().setEnabled(true);
        }

        addUserPanel.getAddButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(EmailValidator.emailValide(addUserPanel.getEmailField().getText())){
                    int dialogResult = JOptionPane.showConfirmDialog(null, "Etes-vous sûr de vouloir ajouter cet utilisateur ?", "Confirmation", JOptionPane.YES_NO_OPTION);
                    if(dialogResult == JOptionPane.YES_OPTION){
                        ajouter();
                    }
                }
                else {
                    JOptionPane.showMessageDialog(parent,"Veuillez rentrer un email valide" , "Erreur ", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void ajouter(){
        String email = addUserPanel.getEmailField().getText();
        String pseudo = addUserPanel.getPseudoField().getText();
        String password = hashPassword(addUserPanel.getPasswordField().getText());
        String role =(String) addUserPanel.getRoleComboBox().getSelectedItem();
        boolean whitelisted = ((MainWindow)addUserPanel.getParent()).isAdmin();
        try {
            User user = new User(email, pseudo, password, role, whitelisted);
            userDAO.addUser(user);
            JOptionPane.showMessageDialog(addUserPanel, "Utilisateur ajouté avec succès");
            addUserPanel.getEmailField().setText("");
            addUserPanel.getPseudoField().setText("");
            addUserPanel.getPasswordField().setText("");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void showPanel() {
        addUserPanel.setVisible(true);
    }
}