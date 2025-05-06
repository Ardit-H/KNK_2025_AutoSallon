package services;

import models.dto.Ofertat.CreateOfertaDto;
import models.dto.Ofertat.Oferta;
import models.dto.Ofertat.UpdateOfertaDto;
import models.dto.Porosite.Porosia;
import repository.OfertaRepository;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class OfertaService {
    private OfertaRepository ofertaRepository;
    private PorosiaService porosiaService;

    public OfertaService() {this.ofertaRepository = new OfertaRepository();
        this.porosiaService = new PorosiaService();
    }
    public List<Oferta> getAll(){ return this.ofertaRepository.getAll();}

    public Oferta getById(int id){
        if(id < 0){
            throw new IllegalArgumentException("Id nuk duhet te jete negative!");
        }
        Oferta oferta = this.ofertaRepository.getById(id);
        if(oferta == null){
            throw new IllegalArgumentException("Oferta me ID:" + id + " nuk ekziston!");
        }
        return oferta;
    }

    public Oferta create(CreateOfertaDto ofertaCreateDto){
        validateCreateOferta(ofertaCreateDto);
        return this.ofertaRepository.create(ofertaCreateDto);
    }

    public void validateCreateOferta(CreateOfertaDto ofertaCreateDto){
        if(ofertaCreateDto.getVeturaId() < 0){
            throw new IllegalArgumentException("ID e vetures nuk duhet te jete negative!");
        }
        if(porosiaService.eshteShiturVetura(ofertaCreateDto.getVeturaId())){
            throw new IllegalArgumentException("Nuk mund te krijosh oferte, vetura me kete ID:" +
                    ofertaCreateDto.getVeturaId() + " eshte shitur!!");
        }
        if(ofertaCreateDto.getZbritja() < 0){
            throw new IllegalArgumentException("Zbritja nuk te jete negative!");
        }
        if(ofertaCreateDto.getCmimiFinal() < 0){
            throw new IllegalArgumentException("CmimiFinal nuk te jete negative!");
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDate dataFillimit = LocalDate.parse(ofertaCreateDto.getDataFillimit(), formatter);
            LocalDate dataMbarimit = LocalDate.parse(ofertaCreateDto.getDataMbarimit(), formatter);



            if (dataMbarimit.isBefore(dataFillimit)) {
                throw new IllegalArgumentException("Data e mbarimit nuk mund te jete para dates se fillimit.");
            }

        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Format i pavlefshem i dates! Perdor formatin yyyy/MM/dd.");
        }

    }

    public Oferta update(UpdateOfertaDto updateOfertaDto) throws Exception{
        if(updateOfertaDto.getOfertaId() < 0){
            throw new IllegalArgumentException("ID nuk duhet te jete negative!");
        }
        Oferta oferta = this.ofertaRepository.getById(updateOfertaDto.getOfertaId());
        if(oferta == null){
            throw new IllegalArgumentException("Oferta me ID:" + updateOfertaDto.getOfertaId() + " nuk ekziston!");
        }

        boolean hasChanges = false;

        if(updateOfertaDto.getZbritja() != 0){
            if(updateOfertaDto.getZbritja() < 0) {
                throw new IllegalArgumentException("ID nuk duhet te jete negative!");
            }
            hasChanges = true;
        }
        if(updateOfertaDto.getCmimiFinal() != 0){
            if(updateOfertaDto.getCmimiFinal() < 0) {
                throw new IllegalArgumentException("Cmimi nuk mund te jete negativ!");
            }
            hasChanges = true;
        }
        if(updateOfertaDto.getDataFillimit() != null){
            try {
                DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                LocalDate.parse(updateOfertaDto.getDataFillimit().trim(), formater);
                hasChanges = true;
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("Data e fillimit te ofertes nuk eshte ne formatin e sakte yyyy-MM-dd.");
            }
        }
        if(updateOfertaDto.getDataMbarimit() != null){
            try {
                DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                LocalDate.parse(updateOfertaDto.getDataMbarimit().trim(), formater);
                hasChanges = true;
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("Data e mbarimit te ofertes nuk eshte ne formatin e sakte yyyy-MM-dd.");
            }
        }

        if(!hasChanges) {
            throw new IllegalArgumentException("Duhet të përditësohet të paktën një fushë.");
        }

        Oferta update = this.ofertaRepository.update(updateOfertaDto);

        if(update == null){
            throw new Exception("Update ka deshtuar per ofert!");
        }
        return update;
    }

    public boolean delete(int id) throws Exception{
        if(id < 0){
            throw new IllegalArgumentException("ID nuk duhet te jete numer negativ!");
        }
        Oferta oferta = this.ofertaRepository.getById(id);

        if(oferta == null){
            throw new Exception("Oferta me ID:" + id + " nuk ekziston!");
        }
        return this.ofertaRepository.delete(id);
    }


}
