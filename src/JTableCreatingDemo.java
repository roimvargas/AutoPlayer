import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class JTableCreatingDemo {
  
  JFrame frame = new JFrame();
  
  String col[] = new String[]{"CLASS","TERM","PAYABLE","YEAR"};
  DefaultTableModel model = new  DefaultTableModel(col, 100);
  JTable table1 = new JTable(model);
  JScrollPane pane = new JScrollPane(table1);
  
  
  public void showWindow(){
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    Object dataArr[][] = { { "Row1-Column1", "Row1-Column2", "Row1-Column3", "Row2-Column3" },
        { "Row2-Column1", "Row2-Column2", "Row2-Column3", "Row2-Column3" },
        { "Row2-Column1", "Row2-Column2", "Row2-Column3", "Row2-Column3" },
        { "Row2-Column1", "Row2-Column2", "Row2-Column3", "Row2-Column3" },
        { "Row2-Column1", "Row2-Column2", "Row2-Column3", "Row2-Column3" },
        { "Row2-Column1", "Row2-Column2", "Row2-Column3", "Row2-Column3" },
        { "Row2-Column1", "Row2-Column2", "Row2-Column3", "Row2-Column3" },
        { "Row2-Column1", "Row2-Column2", "Row2-Column3", "Row2-Column3" },
        { "Row2-Column1", "Row2-Column2", "Row2-Column3", "Row2-Column3" },
        { "Row2-Column1", "Row2-Column2", "Row2-Column3", "Row2-Column3" },
        { "Row2-Column1", "Row2-Column2", "Row2-Column3", "Row2-Column3" } };
    Object columnNames[] = { "Column One", "Column Two", "Column Three" };
    
    model.setRowCount(0);
    
    for(Object[] row: dataArr){
      Vector<String> rowData = new Vector<String>();
      rowData.add((String)row[0]);
      rowData.add((String)row[1]);
      rowData.add((String)row[2]);
      rowData.add((String)row[3]);
      // row++;
      model.addRow(rowData);
    }
    
    

  
    //JTable table = new JTable(rowData, columnNames);
    //JScrollPane scrollPane = new JScrollPane(table);
    
    
    
    
    frame.add(pane, BorderLayout.CENTER);
    frame.setSize(300, 150);
    frame.setVisible(true);
  }
  
  
  public static void main(String args[]) {

    JTableCreatingDemo demo = new JTableCreatingDemo();
    demo.showWindow();
  }
}