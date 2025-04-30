package Test;

import models.dto.Perdoruesit.UpdatePerdoruesitDto;
import models.dto.Perdoruesit.CreatePerdoruesitDto;
import models.dto.Perdoruesit.Perdoruesit;

import Database.DBConnector;
import repository.PerdoruesitRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PerdoruesitRepositoryTest {
    public static void main(String [] args){
        Connection connection = DBConnector.getConnection();
        try{
            Statement stm = connection.createStatement();
            String query = "SELECT * FROM PERDORUESIT ORDER BY ID DESC LIMIT 1 ";
            ResultSet result = stm.executeQuery(query);
            if(result.next()){
                Perdoruesit perdoruesit = Perdoruesit.getInstance(result);
                System.out.println("Id: " + perdoruesit.getPid());
                System.out.println("Email: " + perdoruesit.getEmail());
            }
        } catch(SQLException e){
            e.printStackTrace();
        }

        PerdoruesitRepository perdoruesitRepository = new PerdoruesitRepository();
        Perdoruesit perdoruesit = perdoruesitRepository.getById(6);
        if(perdoruesit != null){
            System.out.println("Id: " + perdoruesit.getPid());
        }
        perdoruesitRepository.delete(2);
        UpdatePerdoruesitDto update = new UpdatePerdoruesitDto();
        update.setId(3);
        update.setEmail("met.gjoka@gmail.com");
          //upadte.setRoli("klient");
         //perdoruesitRepository.delete(4);
        //CreatePerdoruesitDto perdoruesDto = new CreatePerdoruesitDto("Arta", "Berisha", "arta@gmail.com", "+38344123456", "admin");
       //perdoruesitRepository.create(perdoruesDto);
      //UpdatePerdoruesitEmailDto updateEmail = new UpdatePerdoruesitEmailDto(2, "updateemail@gmail.com");
    //perdoruesitRepository.updateEmail(updateEmail);



    }
}
