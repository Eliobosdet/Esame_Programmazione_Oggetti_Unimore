package Grafica;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;

public class CheckBoxEditor extends DefaultCellEditor{
    private JCheckBox checkBox;
    private boolean isCellEditable = true;
    public CheckBoxEditor() {
        super(new JCheckBox());
        checkBox = (JCheckBox) getComponent();
        checkBox.setHorizontalAlignment(SwingConstants.CENTER);

    }


    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        JCheckBox checkBox = (JCheckBox) super.getTableCellEditorComponent(table,value,isSelected,row,column);
        System.out.println("EDITOR: Metodo runnato, editable: "+isSelected);
        checkBox.setEnabled(isCellEditable);

        return checkBox;
    }

    public void setCellEditable(boolean isCellEditable) {
        this.isCellEditable = isCellEditable;
    }
}
