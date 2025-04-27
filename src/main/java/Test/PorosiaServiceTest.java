package Test;

import models.dto.Porosite.CreatePorosiaDto;
import models.dto.Porosite.Porosia;
import services.PorosiaService;

import java.util.List;

public class PorosiaServiceTest {
    public static void main(String[] args) {
        PorosiaService porosiaService = new PorosiaService();
        try{
            CreatePorosiaDto createPorosia1 = new CreatePorosiaDto(1,3,21321, "E refuzuar");
//            porosiaService.create(createPorosia1);
//            Porosia porosia1 = porosiaService.getById(22);
//            System.out.println(porosia1.getPorosiaId());
            List<Porosia> lista = porosiaService.getAll();

            for(Porosia p : lista){
                System.out.println("Prosia id: " + p.getPorosiaId());
                System.out.println("Vetura id: " + p.getVeturaId());
                System.out.println("Klient id: " + p.getKid());
                System.out.println("Cmimi ofruar: " + p.getCmimiOfruar());
                System.out.println("Statusi: " + p.getStatusiPorosise());
            }

//            UpdatePorosiaDto  update = new UpdatePorosiaDto();
//            update.setPorosiaId(25);
//            update.setCmimiOfruar(120000);
//            update.setStatusiPorosise("Ne pritje");
//            porosiaService.update(update);
//
//            Porosia porosia17 = porosiaService.getById(25);
//            System.out.println("Prosia id: " + porosia17.getPorosiaId());
//            System.out.println("Cmimi id: " + porosia17.getCmimiOfruar());
//            System.out.println("Statusi: " + porosia17.getStatusiPorosise());




        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
