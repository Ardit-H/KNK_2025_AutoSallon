package Test;

import Database.DBConnector;
import models.dto.Klientet.Klientet;
import models.dto.Klientet.UpdateKlientiDto;
import models.dto.Sherbimet.CreateSherbimetDto;
import models.dto.Sherbimet.Sherbimet;
import models.dto.Sherbimet.UpdateSherbimetDto;
import repository.KlientetRepository;
import repository.SherbimetRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SherbimetRepositoryTest {
    public static void main(String[] args) {
        Connection connection = DBConnector.getConnection();
        try {
            Statement stm=connection.createStatement();
            String query="SELECT * FROM SHERBIMET ORDER BY ID DESC LIMIT 1";
            ResultSet result=stm.executeQuery(query);
            if(result.next()){
                Sherbimet sherbimet=Sherbimet.getInstance(result);
                System.out.println("Id: "+sherbimet.getId());
                System.out.println("Pershkrimi: "+sherbimet.getPershkrimi());
                System.out.println("Cmimi: "+sherbimet.getÇmimi());
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        SherbimetRepository sherbimetRepository=new SherbimetRepository();
        Sherbimet sherbimet=sherbimetRepository.getById(14);
        if(sherbimet!=null){
            System.out.println("id "+sherbimet.getId());
        }
        UpdateSherbimetDto update=new UpdateSherbimetDto();
        update.setId(2);
//        update.setÇmimi(200.00);
        update.setEmri("Rregullim dhe nderrim frenash");
        update.setPershkrimi("Nderrimi i disqeve dhe jastekave te frenave");
        sherbimetRepository.update(update);

       // CreateSherbimetDto sherbimetDto=new CreateSherbimetDto("Balancim dhe rregullim i gomave", "Sherbimi i rregullimit dhe balancimit te rrotave",150);
        //sherbimetRepository.create(sherbimetDto);
    }
}
