package services;

import CustomExceptions.*;
import models.dto.Lokacionet.CreateLokacionetDto;
import models.dto.Lokacionet.Lokacionet;
import models.dto.Lokacionet.UpdateLokacionetDto;
import repository.LokacionetRepository;

import java.util.List;
import java.util.regex.Pattern;

public class LokacionetService {
    private LokacionetRepository lokacionetRepository;
    public LokacionetService(){
        this.lokacionetRepository = new LokacionetRepository();
    }
    public List<Lokacionet> getAll(){
        return lokacionetRepository.getAll();
    }
    public Lokacionet getById(int id) throws Exception{
        if(id<0){
            throw new InvalidInputException("ID e lokacionit duhet te jete pozitive!");
        }
        Lokacionet lokacioni = this.lokacionetRepository.getById(id);
        if(lokacioni == null){
            throw new ResourceNotFoundException("Lokacioni me id: " + id + " nuk ekziston!");
        }
        return lokacioni;
    }
    public Lokacionet create(CreateLokacionetDto createLokacionet) throws Exception{
        validateCreateDto(createLokacionet);
        return lokacionetRepository.create(createLokacionet);
    }
    private void validateCreateDto(CreateLokacionetDto createLokacionetDto) throws Exception{
        if(isNullOrShort(createLokacionetDto.getEmri_lokacionit(), 3)){
            throw new InvalidInputException("Emri duhet te kete te pakten 3 karaktere.");
        }
        if(isNullOrShort(createLokacionetDto.getAdresa(), 5)){
            throw new InvalidInputException("Adresa duhet te permbaje te pakten 5 karaktere.");
        }
        if(isNullOrShort(createLokacionetDto.getQyteti(), 3)){
            throw new InvalidInputException("Emri qytetit duhet te kete te pakten 3 karaktere.");
        }
        if(!isValidPhone(createLokacionetDto.getNrtelefonit())){
            throw new ValidationException("Numri i telefonit eshte i pavlefshem!");
        }
        if(lokacionetRepository.existsByPhoneNumber(createLokacionetDto.getNrtelefonit())){
            throw new DuplicateResourceException("Ky numer telefoni eshte tashme i regjistruar!");
        }
    }
    private boolean isValidPhone(String phone){
        return phone != null && Pattern.matches("^\\+?[0-9]{7,15}$", phone);
    }
    private boolean isNullOrShort(String s, int minLength){
        return s == null || s.trim().length() < minLength;
    }
    public Lokacionet update(UpdateLokacionetDto updateLokacionetDto) throws Exception{
        if(updateLokacionetDto.getId() <= 0){
            throw new ValidationException("Id e lokacionit eshte e pavlefshme!");
        }
        Lokacionet lokacioni = lokacionetRepository.getById(updateLokacionetDto.getId());
        if(lokacioni==null){
            throw new ResourceNotFoundException("Lokacioni me ID " + updateLokacionetDto.getId() + " nuk ekziston.");
        }
        boolean hasChanges=false;

        if(updateLokacionetDto.getAdresa()!=null){
            if(updateLokacionetDto.getAdresa().trim().length() < 5){
                throw new InvalidInputException("Adresa duhet te kete te pakten 5 karaktere.");
            }
            hasChanges=true;
        }
        if(updateLokacionetDto.getQyteti()!=null){
            if(updateLokacionetDto.getQyteti().trim().length() < 3){
                throw new InvalidInputException("Emri qytetit duhet te kete te pakten 3 karaktere.");
            }
            hasChanges=true;
        }
        if(updateLokacionetDto.getNrtelefonit()!=null){
            if(!isValidPhone(updateLokacionetDto.getNrtelefonit())){
                throw new ValidationException("Numri i telefonit eshte i pavlefshem.");
            }
            if(lokacionetRepository.existsByPhoneNumberExceptId(updateLokacionetDto.getNrtelefonit(), updateLokacionetDto.getId())){
                throw new DuplicateResourceException("Ky numer telefoni eshte tashme i regjistruar!");
            }
            hasChanges=true;
        }

        if(!hasChanges){
            throw new ValidationException("Duhet te perditesohet te pakten nje fushe.");
        }
        Lokacionet updated = lokacionetRepository.update(updateLokacionetDto);
        if(updated == null){
            throw new OperationFailedException("Update-i deshtoi. Lokacioni nuk u perditesua.");
        }
        return updated;
    }
    public boolean delete(int id) throws Exception{
        if(id <= 0){
            throw new ValidationException("Id e lokacionit eshte e pavlefshme. Duhet te jete > 0.");
        }
        Lokacionet lokacioni = lokacionetRepository.getById(id);
        if(lokacioni==null){
            throw new ResourceNotFoundException("Lokacioni nuk ekziston!");
        }
        return lokacionetRepository.delete(id);
    }
    public List<Lokacionet> kerkoLokacionMeEmrin(String emriL){
        return lokacionetRepository.searchByFullName(emriL);
    }

}
