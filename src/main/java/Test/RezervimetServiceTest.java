package Test;

import models.dto.Rezervimet.CreateRezervimetDto;
import models.dto.Rezervimet.Rezervimet;
import models.dto.Rezervimet.UpdateRezervimetDto;
import services.RezervimetService;

import java.util.List;

public class RezervimetServiceTest {
    public static void main(String[] args) {
        RezervimetService rezervimetService = new RezervimetService();

        try {
            Rezervimet rezervimi = rezervimetService.getById(1);
            System.out.println(rezervimi.getDataRezervimit());

            /*List<Rezervimet> list = rezervimetService.getAll();
            for (Rezervimet r : list) {
                System.out.println("ID: " + r.getRezervimiId());
                System.out.println("Data: " + r.getDataRezervimit());
                System.out.println("Klienti ID: " + r.getKlientiId());
                System.out.println("Punetori ID: " + r.getPunetoriId());
                System.out.println("----------------------");
            }*/

            //CreateRezervimetDto createDto = new CreateRezervimetDto("2025-04-22", 2, 5);
            //rezervimetService.create(createDto);

            //UpdateRezervimetDto updateDto = new UpdateRezervimetDto();
            //updateDto.setRezervimiId(3);
            //updateDto.setDataRezervimit("2025-05-01");
            //rezervimetService.update(updateDto);

            //rezervimetService.delete(3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
