package Test;

import models.dto.Ofertat.CreateOfertaDto;
import models.dto.Ofertat.Oferta;
import models.dto.Ofertat.UpdateOfertaDto;
import models.dto.Porosite.UpdatePorosiaDto;
import models.dto.Veturat.UpdateVeturatDto;
import services.OfertaService;
import services.PorosiaService;
import services.VeturatService;

public class OfertaServiceTest {
    public static void main(String[] args) {
        OfertaService ofertaService = new OfertaService();
        PorosiaService porosiaService = new PorosiaService();
        try{
            CreateOfertaDto createOferta = new CreateOfertaDto(1, 200, 22000, "2025/02/12", "2025/05/10");
            ofertaService.create(createOferta);
//            Oferta ofertaEpare = ofertaService.getById(14);
//            System.out.println(ofertaEpare.getOfertaId());
//            System.out.println(ofertaEpare.getVeturaId());
//            System.out.println(ofertaEpare.getCmimiFinal());
//            System.out.println(ofertaEpare.getDataFillimit());
//            System.out.println(ofertaEpare.getDataMbarimit());




            UpdateOfertaDto update = new UpdateOfertaDto();
            update.setOfertaId(3);
            update.setZbritja(300);
            update.setCmimiFinal(26500);
            update.setDataFillimit("2025/04/01");
            update.setDataMbarimit("2025/05/01");



            Oferta ofertaUpdate = ofertaService.getById(3);
//            System.out.println(ofertaUpdate);
            System.out.println(ofertaUpdate.getOfertaId());
            System.out.println(ofertaUpdate.getVeturaId());
            System.out.println(ofertaUpdate.getCmimiFinal());
            System.out.println(ofertaUpdate.getDataFillimit());
            System.out.println(ofertaUpdate.getDataMbarimit());


        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
