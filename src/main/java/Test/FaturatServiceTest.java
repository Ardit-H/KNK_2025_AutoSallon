package Test;

import models.dto.Faturat.CreateFaturatDto;
import models.dto.Faturat.Faturat;
import models.dto.Faturat.UpdateFaturatDto;
import services.FaturatService;

public class FaturatServiceTest {
    public static void main(String[] args) {
        FaturatService faturatService = new FaturatService();

        try {
            // Testo getById
            Faturat faturat = faturatService.getById(3);
            System.out.println("Lloji i Pagesës: " + faturat.getLlojiPageses());

            // Testo getAll
//            List<Fatura> lista = faturatService.getAll();
//            for (Fatura f : lista) {
//                System.out.println("ID: " + f.getId());
//                System.out.println("Shuma Totale: " + f.getShumaTotale());
//                System.out.println("Lloji Pagesës: " + f.getLlojiPageses());
//                System.out.println("Data e Faturës: " + f.getDataFatures());
//                System.out.println("ID e Shitjes: " + f.getShitjeId());
//                System.out.println("------------------------------");
//            }

            // Testo create
//            CreateFaturatDto createDto = new CreateFaturatDto();
//            createDto.setShitjeId(2);
//           // createDto.setShumaTotale(new BigDecimal("150.75"));
//            createDto.setLlojiPageses("Kesh");
//            createDto.setDataFatures(new String("31.05.2025"));
//            Faturat eRe = faturatService.create(createDto);
//            System.out.println("Faturat u shtua me ID: " + eRe.getId());

            // Testo update
//            UpdateFaturatDto updateDto = new UpdateFaturatDto();
//            updateDto.setId(3);
//            updateDto.setShumaTotale(new Double("199.99"));
//            updateDto.setLlojiPageses("Transfer Bankar");
//            Faturat updated = faturatService.update(updateDto);
//            System.out.println("Faturat u përditësua: " + updated.getShumaTotale());

            // Testo delete
            faturatService.delete(5);
            System.out.println("Faturat u fshi me sukses!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
