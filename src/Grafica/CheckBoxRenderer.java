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
        System.out.println("RENDERER: Object:"+value+",Selected:"+isSelected+",row:"+row+",column:"+column);
        return this;
    }
}
