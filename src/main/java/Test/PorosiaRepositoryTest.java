package Test;

import Database.DBConnector;
import models.dto.Porosite.Porosia;

import java.sql.*;
import java.util.Scanner;

public class PorosiaRepositoryTest {
    public static Connection connection = DBConnector.getConnection();
    public static void main(String args[]){
        try{
//            String query = "SELECT * FROM porosite";
//            String query = "SELECT * FROM porosite where STATUSIPOROSISE = 'Ne pritje'";
//            Statement stm = connection.createStatement();
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM porosite where cmimiOfruar > ?");
            Scanner sc = new Scanner(System.in);
            System.out.println("Jepni vleren e cmimit:");
            double cmimi = sc.nextDouble();
            pstm.setDouble(1, cmimi);
            ResultSet rs = pstm.executeQuery();

            while(rs.next()){
                Porosia porosia = Porosia.getInstance(rs);
                System.out.println("Id e porosise" + porosia.getPorosiaId());
                System.out.println("Vetura: " + porosia.getVeturaId() );
                System.out.println("Statusi i porosise" + porosia.getStatusiPorosise());

            }

        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}
