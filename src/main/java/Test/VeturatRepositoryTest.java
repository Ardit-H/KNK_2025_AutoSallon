package Test;

import Database.DBConnector;
import models.Veturat;
import models.dto.Veturat.CreateVeturatDto;
import models.dto.Veturat.UpdateVeturatDto;
import repository.VeturatRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VeturatRepositoryTest {
    public static void main(String[] args){
        Connection connection = DBConnector.getConnection();
        try {
            Statement stm=connection.createStatement();
            String query="SELECT * FROM VETURAT ORDER BY VETURA_ID DESC LIMIT 1";
            ResultSet result=stm.executeQuery(query);
            if(result.next()){
                Veturat vetura=Veturat.getInstance(result);
                System.out.println("Id: "+vetura.getVetura_id());
                System.out.println("Modeli: "+vetura.getModeli());
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        VeturatRepository veturatRepository = new VeturatRepository();
        Veturat vetura = veturatRepository.getById(15);
        if(vetura!=null){
            System.out.println("id "+vetura.getVetura_id());
        }
        veturatRepository.delete(3);
    }
}
