package services;

import models.dto.Porosite.CreatePorosiaDto;
import models.dto.Porosite.Porosia;
import models.dto.Porosite.UpdatePorosiaDto;
import repository.PorositeRepository;

import java.util.List;

public class PorosiaService {
    private PorositeRepository porositeRepository;
    public PorosiaService() {
        this.porositeRepository = new PorositeRepository();
    }
    public List<Porosia> getAll() { return this.porositeRepository.getAll();}

    public Porosia getById(int id) throws Exception {
        if(id < 0){
            throw new Exception("ID e porosise duhet te jete positive!");
        }
        Porosia porosia = this.porositeRepository.getById(id);

        if(porosia == null){
            throw new Exception("Porosia me ID:" + id + " nuk ekziston!");
        }
        return porosia;
    }

    public Porosia create(CreatePorosiaDto createPorosiaDto){
        validateCreateDto(createPorosiaDto);
        return this.porositeRepository.create(createPorosiaDto);
    }

    public void validateCreateDto(CreatePorosiaDto createPorosiaDto) {
        if(createPorosiaDto.getKid() < 0){
            throw new IllegalArgumentException("ID e klientit nuk duhet te jete numer negativ!");
        }
        if(createPorosiaDto.getVeturaId() < 0){
            throw new IllegalArgumentException("ID e vetures nuk duhet te jete numer negativ!");
        }
        if(eshteShiturVetura(createPorosiaDto.getVeturaId())){
            throw new IllegalArgumentException("Vetura ka porosine e kompletuar dhe eshte shitur!");
        }
        if(createPorosiaDto.getCmimiOfruar() < 0){
            throw new IllegalArgumentException("Cmimi i ofruar nuk duhet te jete numer negativ!");
        }
        if(!isValidStatusiPorosise(createPorosiaDto.getStatusiPorosise())){
            throw new IllegalArgumentException("Statusi i porosise duhet te jete njera prej opsioneve:\n 1. Ne pritje\n " +
                    " 2. Ne proces \n 3. E kompletuar \n4. E refuzuar");
        }
    }

    public boolean eshteShiturVetura(int veturaId) {
        List<Porosia> gjithaPorosite = this.porositeRepository.getAll();

        for (Porosia p : gjithaPorosite) {
            if (p.getVeturaId() == veturaId && p.getStatusiPorosise().equals("E kompletuar")) {
                return true;
            }
        }
        return false;
    }
    public boolean isValidStatusiPorosise(String statusi){
        switch(statusi){
            case "Ne pritje":
                return true;
            case "Ne proces":
                return true;
            case "E kompletura":
                return true;
            case "E refuzuar":
                return true;
                default:
                    return false;
        }
    }
        public Porosia update(UpdatePorosiaDto updatePorosiaDto) throws Exception{
        if(updatePorosiaDto.getPorosiaId() < 0){
            throw  new IllegalArgumentException("ID e porosise nuk duhet te jete numer negativ!");
        }
        Porosia porosia = this.porositeRepository.getById(updatePorosiaDto.getPorosiaId());

        if(porosia == null){
            throw new IllegalArgumentException("Porosia me ID:" + updatePorosiaDto.getPorosiaId() + " nuk ekziston!");
        }

        boolean changes = false;

        if(updatePorosiaDto.getCmimiOfruar() != 0){
            if(updatePorosiaDto.getCmimiOfruar() < 0){
                throw new IllegalArgumentException("Cmimi ofruar nuk mund te jete numer negativ!");
            }
            changes = true;
        }

        if(updatePorosiaDto.getStatusiPorosise() != null){
            if(!isValidStatusiPorosise(updatePorosiaDto.getStatusiPorosise())){
                throw new IllegalArgumentException("Statusi i porosise duhet te jete njera prej opsioneve:\n 1. Ne pritje\n " +
                        " 2. Ne proces \n 3. E kompletuar \n4. E refuzuar");
            }
            changes = true;
        }

            if(!changes) {
                throw new IllegalArgumentException("Duhet të përditësohet të paktën një fushë.");
            }

            Porosia update = this.porositeRepository.update(updatePorosiaDto);

            if(update == null){
                throw new Exception("Update ka deshtuar per porosi!");
            }
            return update;
    }

    public boolean delete(int id) throws Exception{
        if(id < 0){
            throw new IllegalArgumentException("ID nuk duhet te jete numer negativ!");
        }
        Porosia porosia = this.porositeRepository.getById(id);

        if(porosia == null){
            throw new Exception("Porosia me ID:" + id + " nuk ekziston!");
        }
        return this.porositeRepository.delete(id);
    }

}
