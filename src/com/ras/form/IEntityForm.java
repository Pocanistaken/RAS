
package com.ras.form;

import com.ras.entity.Menu;
import com.ras.exception.SelectedValueEmpty;
import com.ras.tabbed.WindowsTabbed;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import raven.toast.Notifications;


public interface IEntityForm {
    
    void insertData(String type);
    //void resetData();
    //ArrayList<Boolean> getSelectedValues();
    //boolean isSelectedValueContainsTrue();
    //String getSelectedName(int row, int column);
    void deleteSelectedValues();
    
    
    default boolean isSelectedValueContainsTrue(ArrayList<Boolean> list) {
        int counter = 0;
        for(Boolean b : list) {
            if (b) {
                counter++;
            }   
        }
        if (counter == 0) {
            return false;
        }
        return true;
    }
    
    
    default ArrayList<Boolean> getSelectedBooleanValues(DefaultTableModel model) {
        ArrayList<Boolean> list = new ArrayList<>();

        int rows = model.getRowCount(); 
        for (int i = 0; i < rows; i++) { 
            if (model.getValueAt(i, 0) instanceof Boolean) {
                list.add((Boolean) model.getValueAt(i, 0));
            }
        }
       
        return list;
    }
    
    
    default void resetData(DefaultTableModel model) {
        for(int i = model.getRowCount() - 1; i >=0; i--) {
           model.removeRow(i); 
        }
    }

    default Object getSelectedValue(DefaultTableModel model, int row, int column) {
        return model.getValueAt(row, column);
    }
  
    
    default void cmdDeleteAction(DefaultTableModel model, Class thisClass) {
        if (isSelectedValueContainsTrue(getSelectedBooleanValues(model))) {
            deleteSelectedValues();
        } 
        else {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.BOTTOM_LEFT, "Exception - SelectedValueEmpty");
            try {
                throw new SelectedValueEmpty("Selected value can't be empty for delete progress.");
            } catch (SelectedValueEmpty ex) {
                Logger.getLogger(thisClass.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    default int getSelectedValueAmount(DefaultTableModel model) {
        ArrayList<Boolean> selectedList = getSelectedBooleanValues(model);
        if (isSelectedValueContainsTrue(selectedList)) {
            int i = 0;
            for(Boolean b : selectedList) {
                if (b) {
                    i++;
                }   
            }
            return i;
        }
        return 0;
    }
    
    
}
