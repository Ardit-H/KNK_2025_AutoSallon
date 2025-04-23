package Test;

import models.dto.Punetoret.CreatePunetoretDto;
import models.dto.Punetoret.Punetoret;
import models.dto.Punetoret.UpdatePunetoretDto;
import services.PunetoretService;

import java.util.List;

public class PunetoretServiceTest {
    public static void main(String[] args) {
        PunetoretService punetoretService = new PunetoretService();

        try {
          //vetem prove
            Punetoret punetori = punetoretService.getById(2);
            System.out.println(punetori.getEmri());

            List<Punetoret> list = punetoretService.getAll();
            for (Punetoret p : list) {
                System.out.println("ID: " + p.getPunetor_id());
                System.out.println("Emri: " + p.getEmri());
                System.out.println("Mbiemri: " + p.getMbiemri());
                System.out.println("Email: " + p.getEmail());
                System.out.println("Paga: " + p.getPaga());
                System.out.println("------------------------");
            }

            //CreatePunetoretDto create = new CreatePunetoretDto("Arta", "Mehmeti", "arta@gmail.com", 450.0);
            //punetoretService.create(create);

            //UpdatePunetoretDto update = new UpdatePunetoretDto();
            //update.setId(2);
            //update.setEmail("arta_updated@gmail.com");
            //punetoretService.update(update);

            punetoretService.delete(6);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

