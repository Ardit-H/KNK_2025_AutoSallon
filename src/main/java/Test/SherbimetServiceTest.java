package Test;

import models.dto.Sherbimet.CreateSherbimetDto;
import models.dto.Sherbimet.Sherbimet;
import models.dto.Sherbimet.UpdateSherbimetDto;
import services.SherbimetService;

public class SherbimetServiceTest {
    public static void main(String[] args){
        SherbimetService service=new SherbimetService();
        try{
//            CreateSherbimetDto sherbimi=new CreateSherbimetDto("Nderrim vaji dhe filtri","Zevendesimi i vajit dhe filtrit",50.00);
//            Sherbimet sherbimet=service.create(sherbimi);
//            UpdateSherbimetDto update=new UpdateSherbimetDto();
//            update.setId(2);
//            update.setÇmimi(25.00);
//            update.setEmri("Nderrim vaji");
//            update.setPershkrimi("Nderrim vetem i vajit");
//            service.update(update);
//            for (Sherbimet s : service.getAll()) {
//                System.out.println(s.getId() + ": " + s.getEmri() + " - " + s.getÇmimi());
//            }
            service.delete(9);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
