package Test;

import models.dto.Vleresimet.CreateVleresimetDto;
import models.dto.Vleresimet.UpdateVleresimetDto;
import models.dto.Vleresimet.Vleresimet;
import repository.KlientetRepository;
import repository.VeturatRepository;
import repository.VleresimetRepository;
import services.VleresimetService;

import java.util.List;

public class VleresimetServiceTest{
    public static void main(String[] args){
        KlientetRepository klientetRepository = new KlientetRepository();
        VeturatRepository veturatRepository = new VeturatRepository();
        VleresimetRepository vleresimetRepository = new VleresimetRepository();
        VleresimetService vleresimetService = new VleresimetService();
        try {

//            CreateVleresimetDto dto = new CreateVleresimetDto(6, 3, 7, "Makine e bukure");
//            vleresimetService.create(dto);

//            UpdateVleresimetDto updatedto=new UpdateVleresimetDto();
//            updatedto.setVleresimiId(4);
//            updatedto.setVleresimi(5);
//            vleresimetService.update(updatedto);
//              vleresimetService.getById(0);
//            vleresimetService.delete(5);
//            List<Vleresimet> klientiVleresime = vleresimetService.getVleresimetByKlientiId(2);
//            System.out.println("Vlerësimet e klientit me ID 2:");
//            for (Vleresimet vleresim : klientiVleresime) {
//                System.out.println(vleresim.getKomenti());
//            }
//
//            // Test për marrjen e vlerësimeve sipas veturës
//            List<Vleresimet> veturaVleresime = vleresimetService.getVleresimetByVeturaId(3);
//            System.out.println("Vlerësimet e veturës me ID 3:");
//            for (Vleresimet vleresim : veturaVleresime) {
//                System.out.println(vleresim.getKomenti());

//            }
//            double mesatarja = vleresimetService.getMesatarjaEVleresimevePerVeture(3);
//            System.out.println("Mesatarja e vlerësimeve për veturën me ID 3: " + mesatarja);
            // Test për shfaqjen e vlerësimeve pozitive dhe negative
//            vleresimetService.showPositiveAndNegativeVleresimet(3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
