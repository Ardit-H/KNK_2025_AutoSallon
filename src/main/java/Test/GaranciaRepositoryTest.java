package Test;

import Database.DBConnector;
import models.dto.Garancia.Garancia;
import repository.GaranciaRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GaranciaRepositoryTest {
    public static void main(String[] args){
        Connection connection = DBConnector.getConnection();
        try {
            Statement stm=connection.createStatement();
            String query="SELECT * FROM GARANCIA ORDER BY GID DESC LIMIT 1";
            ResultSet result=stm.executeQuery(query);
            if(result.next()){
                Garancia garancia=Garancia.getInstance(result);
                System.out.println("Id: "+garancia.getGid());
                System.out.println("Lloji i garancise: "+garancia.getLlojiGarancise());
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        GaranciaRepository garanciaRepository = new GaranciaRepository();
        Garancia garancia = garanciaRepository.getById(3);
        if(garancia!=null){
            System.out.println("id "+garancia.getGid());
        }
//        garanciaRepository.delete(3);
    }
}
