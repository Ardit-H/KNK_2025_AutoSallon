package Test;

import Database.DBConnector;
import models.dto.Ofertat.Oferta;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class OfertaRepositoryTest {
    public static Connection connection = DBConnector.getConnection();
    public static void main(String[] args) {

        try{
            String query = "SELECT * FROM ofertat WHERE  zbritja >= 200";
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                Oferta oferta = Oferta.getInstance(rs);
                System.out.println(oferta.getOfertaId());
                System.out.println(oferta.getZbritja());
                System.out.println(oferta.getCmimiFinal());
                System.out.println(oferta.getDataFillimit());
                System.out.println(oferta.getDataMbarimit());

            }


        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
