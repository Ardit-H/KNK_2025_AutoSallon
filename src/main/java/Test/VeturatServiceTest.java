package Test;

import models.dto.Veturat.CreateVeturatDto;
import models.dto.Veturat.UpdateVeturatDto;
import models.dto.Veturat.Veturat;
import services.VeturatService;

import java.util.List;

public class VeturatServiceTest {
    public static void main(String[] args) {
        VeturatService veturatService = new VeturatService();

        try {
            Veturat vetura = veturatService.getById(3);
            System.out.println("Modeli i veturës: " + vetura.getModeli());
            System.out.println("Çmimi: " + vetura.getCmimi());
            System.out.println("Kilometrazha: " + vetura.getKilometrazha());
            System.out.println("---------------");

//            List<Veturat> listaVeturave = veturatService.getAll();
//            for (Veturat v : listaVeturave) {
//                System.out.println("ID: " + v.getId());
//                System.out.println("Modeli: " + v.getModeli());
//                System.out.println("Prodhuesi: " + v.getProdhuesi());
//                System.out.println("Çmimi: " + v.getCmimi());
//                System.out.println("Kilometrazha: " + v.getKilometrazha());
//                System.out.println("Viti: " + v.getVitiProdhimit());
//                System.out.println("------------------------");
//            }

//            CreateVeturatDto createDto = new CreateVeturatDto();
//            createDto.setModeli("Golf 7");
//            createDto.setProdhuesi("Volkswagen");
//            createDto.setCmimi(13500.00);
//            createDto.setKilometrazha(85000);
//            createDto.setViti_prodhimit(2016);
//            veturatService.create(createDto);

//            UpdateVeturatDto updateDto = new UpdateVeturatDto();
//            updateDto.setId(3);
//            updateDto.setCmimi(12500.00);
//            updateDto.setKilometrazha(90000);
//            veturatService.update(updateDto);

            veturatService.delete(6);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
