package services;

import models.dto.Pagesat.CreatePagesaDto;
import models.dto.Pagesat.Pagesa;
import models.dto.Pagesat.UpdatePagesaDto;
import repository.PagesaRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class PagesaService {
    private PagesaRepository pagesaRepository;

    public PagesaService() { this.pagesaRepository = new PagesaRepository(); }
    public List<Pagesa> getAll(){return this.pagesaRepository.getAll();}

    public Pagesa getById(int id){
        if(id < 0){
            throw new IllegalArgumentException("ID nuk mund te jete negative");
        }
        Pagesa pagesa = this.pagesaRepository.getById(id);

        if(pagesa == null){
            throw new IllegalArgumentException("Pagesa me kete ID:" + id + " nuk ekziston!");
        }
        return pagesa;
    }

    public Pagesa create(CreatePagesaDto createPagesaDto){
        validatePagesa(createPagesaDto);
        return this.pagesaRepository.create(createPagesaDto);
    }

    public void validatePagesa(CreatePagesaDto createPagesaDto){
        if(createPagesaDto.getPorosiaId() < 0){
            throw new IllegalArgumentException("ID nuk mund te jete negative");
        }
        if(!isValideMetodaPageses(createPagesaDto.getMetodaPageses())){
            throw new IllegalArgumentException("Metoda e pagese eshte njera prej opsioneve:\n" +
                    " 1. KARTELE\n 2. CASH\n 3. KREDI\n 4. TJETER");
        }
        if(createPagesaDto.getShuma() < 0){
            throw new IllegalArgumentException("Shuma e pagese nuk mund te jete negative");
        }
        if(createPagesaDto.getDataPageses() != null){
            try {
                LocalDate.parse(createPagesaDto.getDataPageses().trim(), DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("Data e pagese nuk eshte ne formatin e sakte yyyy-MM-dd.");
            }
        }
    }

    public boolean isValideMetodaPageses(String metodaPagese){
        switch(metodaPagese){
            case "KARTELE":
            return true;
            case "CASH":
            return true;
            case "KREDI":
            return true;
            case "TJETER":
                return true;
                default:
                    return false;

        }
    }

    public Pagesa update(UpdatePagesaDto updatePagesaDto) throws Exception{
        if(updatePagesaDto.getPagesaId() < 0){
            throw new IllegalArgumentException("ID nuk mund te jete negative");
        }
        Pagesa pagesa = this.pagesaRepository.getById(updatePagesaDto.getPagesaId());
        if(pagesa == null){
            throw new IllegalArgumentException("Pagesa me ID: " + updatePagesaDto.getPagesaId() + " nuk ekziston!");
        }
        boolean hasChanges = false;

        if(updatePagesaDto.getMetodaPageses() != null){
        if(!isValideMetodaPageses(updatePagesaDto.getMetodaPageses())){
            throw new IllegalArgumentException("Metoda e pagese eshte njera prej opsioneve:\n" +
                    "1. KARTELE\n 2. CASH\n 3.KREDI\n 4.TJETER");
        }
        hasChanges = true;
        }
        if(updatePagesaDto.getShuma() != 0){
        if(updatePagesaDto.getShuma() < 0){
            throw new IllegalArgumentException("Shuma e pagese nuk mund te jete negative");
        }
        hasChanges = true;
        }

        if(updatePagesaDto.getDataPageses() != null){
                try {
                    LocalDate.parse(updatePagesaDto.getDataPageses().trim(), DateTimeFormatter.ofPattern("yyyy/MM/dd"));
                    hasChanges = true;
                } catch (DateTimeParseException e) {
                    throw new IllegalArgumentException("Data e pagese nuk eshte ne formatin e sakte yyyy-MM-dd.");
                }
        }

        if(!hasChanges) {
            throw new IllegalArgumentException("Duhet të përditësohet të paktën një fushë.");
        }

        Pagesa update = this.pagesaRepository.update(updatePagesaDto);

        if(update == null){
            throw new Exception("Update ka deshtuar per pagesen!");
        }
        return update;

    }

    public boolean delete(int id) throws Exception{
        if(id < 0){
            throw new IllegalArgumentException("ID nuk mund te jete negative");
        }
        Pagesa pagesa = this.pagesaRepository.getById(id);
        if(pagesa == null){
            throw new Exception("Pagesa me ID: " + id + " nuk ekziston!");
        }
        return this.pagesaRepository.delete(id);
    }
}
