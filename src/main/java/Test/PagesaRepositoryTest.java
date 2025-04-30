package Test;

import Database.DBConnector;
import models.dto.Pagesat.Pagesa;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class PagesaRepositoryTest {
    public static Connection connection = DBConnector.getConnection();
    public static void main(String[] args) {
        try{
            String query = "Select * from pagesat";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                Pagesa pagesa = Pagesa.getInstance(resultSet);
                System.out.println("Pagesa ID: " + pagesa.getPagesaId());
                System.out.println("Metoda pagese: " + pagesa.getMetodaPageses());
                System.out.println("Shuma e pageses: " + pagesa.getShuma());
                System.out.println("Data pageses: " + pagesa.getDataPageses());
            }



        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
