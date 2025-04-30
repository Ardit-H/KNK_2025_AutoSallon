package Test;

import models.dto.Pagesat.CreatePagesaDto;
import models.dto.Pagesat.Pagesa;
import models.dto.Pagesat.UpdatePagesaDto;
import services.PagesaService;

public class PagesaServiceTest {
    public static void main(String[] args) {
        PagesaService pagesaService = new PagesaService();
        try{
            CreatePagesaDto create = new CreatePagesaDto(28, "CASH", 250000, "2025/05/28");
//            pagesaService.create(create);
//            Pagesa pg = pagesaService.getById(2);
//            System.out.println("Id e pagese: " + pg.getPagesaId());
//            System.out.println("Id e porosise: " + pg.getPorosiaId());
//            System.out.println("Shuma e pagume: " + pg.getShuma());
//            System.out.println("Data e pagese: " + pg.getDataPageses());

            UpdatePagesaDto update = new UpdatePagesaDto();

            update.setPagesaId(2);
            update.setMetodaPageses("KREDI");
            update.setShuma(230000);
            update.setDataPageses("2025/07/16");

            pagesaService.update(update);

            Pagesa pg = pagesaService.getById(2);
            System.out.println("Id e pagese: " + pg.getPagesaId());
            System.out.println("Id e porosise: " + pg.getPorosiaId());
            System.out.println("Shuma e pagume: " + pg.getShuma());
            System.out.println("Data e pagese: " + pg.getDataPageses());





        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
