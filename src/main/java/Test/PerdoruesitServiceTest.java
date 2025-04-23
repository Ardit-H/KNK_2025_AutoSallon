package Test;

import models.dto.Perdoruesit.Perdoruesit;
import models.dto.Perdoruesit.CreatePerdoruesitDto;
import models.dto.Perdoruesit.UpdatePerdoruesitDto;
import services.PerdoruesitService;

import java.util.List;

public class PerdoruesitServiceTest {
    public static void main(String [] args){
        PerdoruesitService perdoruesitService = new PerdoruesitService();

        try{
            Perdoruesit perdoruesit = perdoruesitService.getById(3);
            System.out.println(perdoruesit.getEmri());

           /* List<Perdoruesit> perdoruesList = perdoruesitService.getAll();
            for (Perdoruesit p : perdoruesList){
                System.out.println("ID: " + p.getPid());
                System.out.println("Emri: " + p.getEmri());
                System.out.println("Email: " + p.getEmail());
                System.out.println("Fjalekalimi: " + p.getFjalekalimi());
                System.out.println("Roli: " + p.getRoli());
                System.out.println("------------------------");
            }*/

            //CreatePerdoruesitDto create = new CreatePerdoruesitDto("Arta", "arta@gmail.com", "xxxxxx", "admin");
            //perdoruesitService.create(create);

           // UpdatePerdoruesitDto update = new UpdatePerdoruesitDto();
            //update.setId(9);
            //update.setEmail("arta_updated@gmail.com");
            //update.setFjalekalimi("newpassword");
            //update.setRoli("klient");
            //perdoruesitService.update(update);

           // perdoruesitService.delete(7);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
