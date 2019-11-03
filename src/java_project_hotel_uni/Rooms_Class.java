
package java_project_hotel_uni;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class Rooms_Class {
    
    my_SQL_Connect_Class mycon1 = new my_SQL_Connect_Class();
    
    // метод за извеждане на типовете стаи в таблица
    public void addingTypeOfRoomsIntoTable(JTable myGuestTable) // методът 'addingItemsIntoTable(JTable myGuestTable)' от GuestClass
    {                        
        String slctQry_1 = "SELECT * FROM `roomtype`";
        try {
            PreparedStatement PrepaSt_1 = mycon1.devConnect().prepareStatement(slctQry_1);
            ResultSet ResSet_1 = PrepaSt_1.executeQuery();
            DefaultTableModel DftTM1 = (DefaultTableModel)myGuestTable.getModel();
            Object[] line;
            while(ResSet_1.next() )
            {
                line = new Object[3]; // Rid, Rlabel, Rprice
                line[0] = ResSet_1.getInt(1);
                line[1] = ResSet_1.getString(2);
                line[2] = ResSet_1.getString(3);
                   
                
                DftTM1.addRow(line);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GuestClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    // метод за извеждане на стаите в таблица
    public void addingRoomsIntoTable(JTable myGuestTable) 
    {                        
        String slctQry_1 = "SELECT * FROM `rooms`";
        try {
            PreparedStatement PrepaSt_1 = mycon1.devConnect().prepareStatement(slctQry_1);
            ResultSet ResSet_1 = PrepaSt_1.executeQuery();
            DefaultTableModel DftTM1 = (DefaultTableModel)myGuestTable.getModel();
            Object[] line;
            while(ResSet_1.next() )
            {
                line = new Object[4]; 
                line[0] = ResSet_1.getInt(1);
                line[1] = ResSet_1.getInt(2);
                line[2] = ResSet_1.getString(3);
                line[3] = ResSet_1.getString(4);
                   
                
                DftTM1.addRow(line);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GuestClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // метод за попълване на комбобокса със id-тата на типовете стаи 
    public void addingTypeOfRoomsIntoComboBox(JComboBox myComboBox) 
    {                        
        String slctQry_1 = "SELECT * FROM `roomtype`";
        try {
            PreparedStatement PrepaSt_1 = mycon1.devConnect().prepareStatement(slctQry_1);
            ResultSet ResSet_1 = PrepaSt_1.executeQuery();
            
            while(ResSet_1.next() )
            {
                myComboBox.addItem(ResSet_1.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GuestClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    //метод за добавяне на нова стая
    public boolean AddingRooms(int number, int room_Type, String GSM)
    {
         // PreparedStatement PpdSt_1 = null;
        ResultSet RstSt_1 = null;
        String qry = "INSERT INTO `rooms`(`Rnumber`, `Rtype`, `RGSM`, `Rreserved`) VALUES (?,?,?,?)";
        
        try {
            PreparedStatement PpdSt_1 = mycon1.devConnect().prepareStatement(qry);
            
            PpdSt_1.setInt(1, number);
            PpdSt_1.setInt(2, room_Type);
            PpdSt_1.setString(3, GSM);
            
            //добавяйки нова стая Rreserved колоната ще бъде сетната на 'Not Free'              
            PpdSt_1.setString(4, "Not Free");
             
            //return true;
            return (PpdSt_1.executeUpdate()>0);            
            
        } catch (SQLException ex) {
            Logger.getLogger(GuestClass.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }                
    }
    
    
    //метод за 'корекция' на стая
    public boolean editingSelectedRoom(int number, int room_Type, String GSM, String isFree) //методът editingSelectedGuest() от GuestClass
    {
        // PreparedStatement PpdSt_1 = null;
        ResultSet RstSt_1 = null;
        String qry_editingSelectedGuest = "UPDATE `rooms` SET `Rtype`=?,`RGSM`=?,`Rreserved`=? WHERE `Rnumber`=?";
        
        try {
            PreparedStatement PpdSt_1 = mycon1.devConnect().prepareStatement(qry_editingSelectedGuest);
            
            PpdSt_1.setInt(1, room_Type);            
            PpdSt_1.setString(2, GSM);
            PpdSt_1.setString(3, isFree);
            PpdSt_1.setInt(4, number);
            
            return (PpdSt_1.executeUpdate() > 0);
            
        } catch (SQLException ex) {
            Logger.getLogger(GuestClass.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }  
    }
    
    
    
    //метод за 'премахване' на стая
    public boolean delRoom(int rNumber) //методът delGuest() от GuestClass
    {
        // PreparedStatement PpdSt_1 = null;
        ResultSet RstSt_1 = null;
        String qryDELETE = "DELETE FROM `rooms` WHERE `Rnumber`=?";
        
        try {
            PreparedStatement PpdSt_1 = mycon1.devConnect().prepareStatement(qryDELETE);
                        
            PpdSt_1.setInt(1, rNumber);
            
            return (PpdSt_1.executeUpdate() > 0);
            
        } catch (SQLException ex) {
            Logger.getLogger(GuestClass.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }  
    }
}
