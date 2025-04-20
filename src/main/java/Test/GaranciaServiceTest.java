package Test;

import models.dto.Garancia.CreateGaranciaDto;
import models.dto.Garancia.UpdateGaranciaDto;
import models.dto.Garancia.Garancia;
import services.GaranciaService;

import java.util.List;

public class GaranciaServiceTest {
    public static void main(String[] args) {
        GaranciaService garanciaService = new GaranciaService();

        try {
            Garancia garancia = garanciaService.getById(2);
            System.out.println("Data Fillimit: " + garancia.getDataFillimit());
            System.out.println("Data Mbarimit: " + garancia.getDataMbarimit());
            System.out.println("-----------------------");


//            List<Garancia> lista = garanciaService.getAll();
//            for (Garancia g : lista) {
//                System.out.println("ID: " + g.getId());
//                System.out.println("Data Fillimit: " + g.getDataFillimit());
//                System.out.println("Data Mbarimit: " + g.getDataMbarimit());
//                System.out.println("-----------------------");
//            }


//            CreateGaranciaDto createDto = new CreateGaranciaDto();
//            createDto.setDataFillimit("2025-04-01");
//            createDto.setDataMbarimit("2026-04-01");
//            garanciaService.create(createDto);

//            UpdateGaranciaDto updateDto = new UpdateGaranciaDto();
//            updateDto.setId(2);
//            updateDto.setDataFillimit("2025-05-01");
//            updateDto.setDataMbarimit("2026-05-01");
//            garanciaService.update(updateDto);

            garanciaService.delete(5);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
