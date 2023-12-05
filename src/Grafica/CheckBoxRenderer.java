package Grafica;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.TableCellRenderer;

public class CheckBoxRenderer extends JCheckBox implements TableCellRenderer {
    public CheckBoxRenderer() {
        setHorizontalAlignment(JCheckBox.CENTER);
    }

    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setSelected(Boolean.TRUE.equals(value));
        Object valueInColumn3 = table.getValueAt(row, 3);
        if (valueInColumn3 != null && !valueInColumn3.toString().equals("30")) {
            setEnabled(false);
        }
        return this;
    }
}
