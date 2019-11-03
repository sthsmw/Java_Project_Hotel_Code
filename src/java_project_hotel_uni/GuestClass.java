
package java_project_hotel_uni;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;



// създавам и нова таблица във phpMyAdmin 'guestTable'
public class GuestClass {
    my_SQL_Connect_Class mycon1 = new my_SQL_Connect_Class();
    
    
    //метод за 'добавяне' на гост
    public boolean AddingGuests(String FN, String LN, String GSM, String Email)
    {
        // PreparedStatement PpdSt_1 = null;
        ResultSet RstSt_1 = null;
        String qry = "INSERT INTO `guestTable`(`first_name`, `last_name`, `gsm`, `email`) VALUES (?,?,?,?)";
        
        try {
            PreparedStatement PpdSt_1 = mycon1.devConnect().prepareStatement(qry);
            
            PpdSt_1.setString(1, FN);
            PpdSt_1.setString(2, LN);
            PpdSt_1.setString(3, GSM);
            PpdSt_1.setString(4, Email);
            
            return (PpdSt_1.executeUpdate() > 0);
            
        } catch (SQLException ex) {
            Logger.getLogger(GuestClass.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }                
    }
    
    
    //метод за 'корекция' на гост
    public boolean editingSelectedGuest(int id, String FN, String LN, String GSM, String Email)
    {
        // PreparedStatement PpdSt_1 = null;
        ResultSet RstSt_1 = null;
        String qry_editingSelectedGuest = "UPDATE `guesttable` SET `first_name`=?,`last_name`=?,`gsm`=?,`email`=? WHERE `gID`=?";
        
        try {
            PreparedStatement PpdSt_1 = mycon1.devConnect().prepareStatement(qry_editingSelectedGuest);
            
            PpdSt_1.setString(1, FN);
            PpdSt_1.setString(2, LN);
            PpdSt_1.setString(3, GSM);
            PpdSt_1.setString(4, Email);
            PpdSt_1.setInt(5, id);
            
            return (PpdSt_1.executeUpdate() > 0);
            
        } catch (SQLException ex) {
            Logger.getLogger(GuestClass.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }  
    }
    
    //метод за 'премахване' на гост
    public boolean delGuest(int idOfGuest)
    {
        // PreparedStatement PpdSt_1 = null;
        ResultSet RstSt_1 = null;
        String qryDELETE = "DELETE FROM `guesttable` WHERE `gID`=?";
        
        try {
            PreparedStatement PpdSt_1 = mycon1.devConnect().prepareStatement(qryDELETE);
                        
            PpdSt_1.setInt(1, idOfGuest);
            
            return (PpdSt_1.executeUpdate() > 0);
            
        } catch (SQLException ex) {
            Logger.getLogger(GuestClass.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }  
    }
    
    
    //метод за 'връщане' на лист с гостите в таблицата
    public void addingItemsIntoTable(JTable myGuestTable)
    {                        
        String slctQry_1 = "SELECT * FROM `guestTable`";
        try {
            PreparedStatement PrepaSt_1 = mycon1.devConnect().prepareStatement(slctQry_1);
            ResultSet ResSet_1 = PrepaSt_1.executeQuery();
            DefaultTableModel DftTM1 = (DefaultTableModel)myGuestTable.getModel();
            Object[] line;
            while(ResSet_1.next() )
            {
                line = new Object[5]; // gID, first_name, last_name, gsm, email 
                line[0] = ResSet_1.getInt(1);
                line[1] = ResSet_1.getString(2);
                line[2] = ResSet_1.getString(3);
                line[3] = ResSet_1.getString(4);
                line[4] = ResSet_1.getString(5);    
                
                DftTM1.addRow(line);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GuestClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
