package Test;

import Database.DBConnector;
import models.dto.Sherbimet.Sherbimet;
import models.dto.Sherbimet.UpdateSherbimetDto;
import models.dto.Vleresimet.CreateVleresimetDto;
import models.dto.Vleresimet.UpdateVleresimetDto;
import models.dto.Vleresimet.Vleresimet;
import repository.SherbimetRepository;
import repository.VleresimetRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class
VleresimetRepositoryTest {
    public static void main(String[] args) {
        Connection connection = DBConnector.getConnection();
        try {
            Statement stm=connection.createStatement();
            String query="SELECT * FROM VLERESIMET ORDER BY ID DESC LIMIT 1";
            ResultSet result=stm.executeQuery(query);
            if(result.next()){
                Vleresimet vleresimet=Vleresimet.getInstance(result);
                System.out.println("Id: "+vleresimet.getVleresimiId());
                System.out.println("Vleresimi: "+vleresimet.getVleresimi());
                System.out.println("Komenti: "+vleresimet.getKomenti());
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        VleresimetRepository vleresimetRepository=new VleresimetRepository();
        Vleresimet vleresimet=vleresimetRepository.getById(2);
        if(vleresimet!=null){
            System.out.println("id "+vleresimet.getVleresimiId());
        }
        UpdateVleresimetDto update=new UpdateVleresimetDto();
        update.setVleresimiId(1);
//        update.setKomenti("Makina me pelqeu shume");
        update.setVleresimi(3);
        vleresimetRepository.update(update);

//         CreateVleresimetDto vleresimetDto=new CreateVleresimetDto(2,3,4,"Veture shume e bukure");
//        vleresimetRepository.create(vleresimetDto);
    }
}
