package tcccalango.view.ajuda;

import java.util.ArrayList;
import java.util.List;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

class ParametrosTableModel implements TableModel {
   private static final String[] columns = new String[]{"Tipo", "Nome", "Descrição"};
   private List<String[]> listaParametros = new ArrayList();

   public int getRowCount() {
      return this.listaParametros.size();
   }

   public int getColumnCount() {
      return 3;
   }

   public String getColumnName(int i) {
      return columns[i];
   }

   public Class<?> getColumnClass(int i) {
      return String.class;
   }

   public boolean isCellEditable(int i, int i1) {
      return true;
   }

   public Object getValueAt(int row, int col) {
      return ((String[])this.listaParametros.get(row))[col];
   }

   public void setValueAt(Object o, int row, int col) {
      ((String[])this.listaParametros.get(row))[col] = String.valueOf(o);
   }

   public void addTableModelListener(TableModelListener tl) {
   }

   public void removeTableModelListener(TableModelListener tl) {
   }

   public void addRow() {
      this.listaParametros.add(new String[3]);
   }

   public void removeRow(int row) {
      this.listaParametros.remove(row);
   }

   public boolean upRow(int row) {
      if (row > 0) {
         this.listaParametros.add(row - 1, this.listaParametros.remove(row));
         return true;
      } else {
         return false;
      }
   }

   public boolean downRow(int row) {
      if (row < this.listaParametros.size() - 1) {
         this.listaParametros.add(row + 1, this.listaParametros.remove(row));
         return true;
      } else {
         return false;
      }
   }

   public void clear() {
      this.listaParametros.clear();
   }
}
