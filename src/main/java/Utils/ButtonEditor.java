package Utils;

import View.WhitelistUserPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.function.Consumer;

public class ButtonEditor extends DefaultCellEditor {
    protected JButton button;
    private String label;
    private boolean isPushed;
    private JTable userTable;
    private Consumer<String> buttonClickedAction;
    private Runnable afterClickAction;

    public ButtonEditor(JCheckBox checkBox, JTable userTable, Consumer<String> buttonClickedAction, Runnable afterClickAction) {
        super(checkBox);
        this.userTable = userTable;
        this.buttonClickedAction = buttonClickedAction;
        this.afterClickAction = afterClickAction;

        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
            }
        });
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (isSelected) {
            button.setForeground(table.getSelectionForeground());
            button.setBackground(table.getSelectionBackground());
        } else {
            button.setForeground(table.getForeground());
            button.setBackground(table.getBackground());
        }
        label = (value == null) ? "" : value.toString();
        button.setText(label);
        isPushed = true;
        return button;
    }

    public Object getCellEditorValue() {
        if (isPushed) {
            int row = userTable.getSelectedRow();
            String email = (String) userTable.getValueAt(row, 0);
            int dialogResult = JOptionPane.showConfirmDialog(null, "Etes-vous sûr de vouloir réaliser cette action ?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if(dialogResult == JOptionPane.YES_OPTION){
                SwingUtilities.invokeLater(() -> {
                    buttonClickedAction.accept(email);
                    afterClickAction.run();
                });
            }
        }
        isPushed = false;
        return label;
    }

    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }

    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }
}