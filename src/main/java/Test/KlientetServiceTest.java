package Test;

import models.dto.Klientet.CreateKlientetDto;
import models.dto.Klientet.Klientet;
import models.dto.Klientet.UpdateKlientiDto;
import repository.KlientetRepository;
import services.KlientetService;

import java.util.ArrayList;
import java.util.List;

public class KlientetServiceTest {
    public static void main(String[] args){
        KlientetService klientetService=new KlientetService();
        try{
           Klientet klientet= klientetService.getById(6);
          System.out.println( klientet.getEmri());
//         List<Klientet> klients=klientetService.getAll();
//         for(int i=0;i< klients.size();i++){
//             Klientet klient = klients.get(i);
//             System.out.println("ID: " + klient.getKid());
//             System.out.println("Emri: " + klient.getEmri());
//             System.out.println("Mbiemri: " + klient.getMbiemri());
//             System.out.println("Email: " + klient.getEmail());
//             System.out.println("Nr Telefonit: " + klient.getNrtelefonit());
//             System.out.println("Adresa: " + klient.getAdresa());
//             System.out.println("Data Regjistrimit: " + klient.getData_regjistrimit());
//             System.out.println("-----------------------------");
//         }
//            CreateKlientetDto create=new CreateKlientetDto("Fisnik","Beqiri","fis@gmail.com","+49824948","Peje-Rruga Shekspiri");
//          klientetService.create(create);
//            UpdateKlientiDto update=new UpdateKlientiDto();
//            update.setId(8);
//            update.setEmail("fisnik2@gmail.com");
//            update.setAdresa("Ferizaj");
//            update.setNrtelefonit("049586724");
//          klientetService.update(update);
            klientetService.delete(8);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
