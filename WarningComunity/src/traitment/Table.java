package traitment;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

public class Table extends JTable {
	
	public Table(TableModel tm)
	{
		super(tm);
		
		
		setFillsViewportHeight(true);
        setAutoCreateRowSorter(true);
        
        //Combo box pour la colonne NOM EVENT
        TableColumn nameEventColumn = getColumnModel().getColumn(2);
        JComboBox comboBox = new JComboBox();
        String[] temp = tm.getDataEvent();
        for(int i = 0; i < temp.length; i++)
        {
        	comboBox.addItem(temp[i]);
        }
        nameEventColumn.setCellEditor(new DefaultCellEditor(comboBox));
        
        
        //Alignement milieu sur toutes les colonnes
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        for(int i =0; i<5;i++)
        	getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
        	
	}

}
