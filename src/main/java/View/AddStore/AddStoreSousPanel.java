package View.AddStore;

import Model.User;

import javax.swing.*;

public class AddStoreSousPanel extends JPanel {
    private final User user;
    private final JCheckBox checkBox;

    public AddStoreSousPanel(User user) {
        this.user = user;
        checkBox = new JCheckBox(user.getEmail());

        add(checkBox);
    }

    public User getUser() {
        return user;
    }

    public boolean isSelected() {
        return checkBox.isSelected();
    }
}
