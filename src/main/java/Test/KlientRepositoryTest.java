package Test;

import Database.DBConnector;
import models.Klientet;
import models.dto.Klientet.CreateKlientetDto;
import models.dto.Klientet.UpdateKlientetEmailDto;
import repository.KlientetRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class KlientRepositoryTest {
    public static void main(String[] args) {
        Connection connection = DBConnector.getConnection();
        try {
            Statement stm=connection.createStatement();
            String query="SELECT * FROM KLIENTET ORDER BY KID DESC LIMIT 1";
            ResultSet result=stm.executeQuery(query);
            if(result.next()){
                Klientet klient=Klientet.getInstance(result);
                System.out.println("Id: "+klient.getKid());
                System.out.println("Email: "+klient.getEmail());
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        KlientetRepository klientetRepository=new KlientetRepository();
        Klientet klient=klientetRepository.getById(14);
        if(klient!=null){
            System.out.println("id "+klient.getKid());
        }
        klientetRepository.delete(5);
//        klientetRepository.delete(4);
//        CreateKlientetDto klientDto=new CreateKlientetDto("Studenti 2", "Haliti","student2@gmail.com","+38349834896","Prishtine");
//        klientetRepository.create(klientDto);
//        UpdateKlientetEmailDto updateEmail=new UpdateKlientetEmailDto(2,"update@gmail.com");
//        klientetRepository.updateEmail(updateEmail);

    }
}
