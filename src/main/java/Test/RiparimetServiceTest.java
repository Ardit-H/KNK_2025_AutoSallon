package Test;

import models.dto.Riparimet.CreateRiparimetDto;
import models.dto.Riparimet.Riparimet;
import models.dto.Riparimet.UpdateRiparimetDto;
import services.RiparimetService;

import java.util.List;

public class RiparimetServiceTest {
    public static void main(String[] args) {
        RiparimetService riparimetService = new RiparimetService();

        try {
            // Testimi i getById
            Riparimet riparimi = riparimetService.getById(2);
            System.out.println("Statusi: " + riparimi.getStatusi());
            System.out.println("Kostoja: " + riparimi.getKostoRiparimit());

            // Testimi i getAll
//            List<Riparimet> riparime = riparimetService.getAll();
//            for (Riparimet r : riparime) {
//                System.out.println("ID: " + r.getId());
//                System.out.println("Vetura ID: " + r.getVeturaId());
//                System.out.println("Sherbimi ID: " + r.getSherbimiId());
//                System.out.println("Statusi: " + r.getStatusi());
//                System.out.println("Kosto Riparimi: " + r.getKostoRiparimit());
//                System.out.println("Data Riparimit: " + r.getDataRiparimit());
//                System.out.println("---------------------------");
//            }

            // Testimi i create
//            CreateRiparimetDto dto = new CreateRiparimetDto();
//            dto.setVeturaId(1);
//            dto.setSherbimiId(1);
//            dto.setStatusi("Ne proces");
//            dto.setKostoRiparimit(50.0);
//            dto.setDataRiparimit("2024-05-10");
//            riparimetService.create(dto);

            // Testimi i update
//            UpdateRiparimetDto update = new UpdateRiparimetDto();
//            update.setId(2);
//            update.setStatusi("Perfundohet");
//            update.setKostoRiparimit(70.0);
//            riparimetService.update(update);

            // Testimi i delete
//            riparimetService.delete(3);

            // Testimi i findByAllFields
//            Riparimet found = riparimetService.findByAllFields(1, 1, "Perfundohet", 70.0, "2024-05-10");
//            if (found != null) {
//                System.out.println("Riparimi u gjet: ID = " + found.getId());
//            } else {
//                System.out.println("Riparimi nuk u gjet.");
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
