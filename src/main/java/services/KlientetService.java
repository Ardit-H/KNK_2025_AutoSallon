package services;

import CustomExceptions.*;
import models.dto.Klientet.CreateKlientetDto;
import models.dto.Klientet.Klientet;
import models.dto.Klientet.UpdateKlientiDto;
import repository.KlientetRepository;

import java.util.List;
import java.util.regex.Pattern;

public class KlientetService {
    private KlientetRepository klientetRepository;
    public KlientetService(){
        this.klientetRepository=new KlientetRepository();
    }
    public List<Klientet> getAll() {
        return klientetRepository.getAll();
    }
    public Klientet getById(int id)throws Exception{
        if(id<0){
            throw new InvalidInputException("ID e klientit duhet të jetë pozitive!");
        }
        Klientet klienti=this.klientetRepository.getById(id);
        if(klienti==null){
            throw new ResourceNotFoundException("Klienti me id: "+id+" nuk ekziston!");
        }
        return klienti;
    }
    public Klientet create(CreateKlientetDto createKlientet)throws Exception{
        validateCreateDto(createKlientet);
        return klientetRepository.create(createKlientet);
    }
    private void validateCreateDto(CreateKlientetDto createKlientetDto)throws Exception{
        if(isNullOrShort(createKlientetDto.getEmri(),3)){
            throw new InvalidInputException("Emri duhet te kete te pakten 3 karaktere");
        }
        if(isNullOrShort(createKlientetDto.getMbiemri(),3)){
            throw new InvalidInputException("Mbiemri duhet te kete te pakten 3 karaktere");
        }
        if(!isValidEmail(createKlientetDto.getEmail())){
            throw new ValidationException("Email eshte i pavlefshem!");
        }
        if (klientetRepository.existsByEmail(createKlientetDto.getEmail())) {
            throw new DuplicateResourceException("Email që keni shënuar është tashmë i regjistruar!");
        }
        if(!isValidPhone(createKlientetDto.getNrtelefonit())){
            throw new ValidationException("Numri i telefonit eshte i pavlefshem!");
        }
        if(isNullOrShort(createKlientetDto.getAdresa(),5)){
            throw new InvalidInputException("Adresa duhet te kete te pakten 5 karaktere!");
        }
        if (klientetRepository.existsByPhoneNumber(createKlientetDto.getNrtelefonit())) {
            throw new DuplicateResourceException("Ky numër telefoni është tashmë i regjistruar!");
        }
    }
    private boolean isValidEmail(String email){
        return email!=null && Pattern.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9._]+\\.[A-Za-z]{2,}$", email);
    }
    private boolean isValidPhone(String phone) {
        return phone != null && Pattern.matches("^\\+?[0-9]{7,15}$", phone);
    }

    private boolean isNullOrShort(String s, int minLength) {
        return s == null || s.trim().length() < minLength;
    }
    public Klientet update(UpdateKlientiDto updateKlientiDto)throws Exception{
        if(updateKlientiDto.getId()<=0){
            throw new ValidationException("Id e klientit eshte e pavlefshme!");
        }
        Klientet klienti=klientetRepository.getById(updateKlientiDto.getId());
        if (klienti==null) {
            throw new ResourceNotFoundException("Klienti me ID " + updateKlientiDto.getId() + " nuk ekziston.");
        }
        boolean hasChanges=false;

        if(updateKlientiDto.getEmail()!=null) {
            if (!isValidEmail(updateKlientiDto.getEmail())) {
                throw new ValidationException("Email-i është i pavlefshëm.");
            }
            if (klientetRepository.existsByEmailExceptId(updateKlientiDto.getEmail(), updateKlientiDto.getId())) {
                throw new DuplicateResourceException("Ky email është tashmë i regjistruar për një klient tjetër!");
            }
            hasChanges=true;
        }
        if(updateKlientiDto.getNrtelefonit()!=null) {
            if (!isValidPhone(updateKlientiDto.getNrtelefonit())) {
                throw new ValidationException("Numri i telefonit është i pavlefshëm.");
            }
            if (klientetRepository.existsByPhoneNumberExceptId(updateKlientiDto.getNrtelefonit(), updateKlientiDto.getId())) {
                throw new DuplicateResourceException("Ky numër telefoni është tashmë i regjistruar për një klient tjetër!");
            }
            hasChanges=true;
        }
        if(updateKlientiDto.getAdresa()!=null) {
            if (updateKlientiDto.getAdresa().trim().length() < 5) {
                throw new InvalidInputException("Adresa duhet të ketë të paktën 5 karaktere.");
            }
            hasChanges=true;
        }

        if(!hasChanges) {
            throw new InvalidInputException("Duhet të përditësohet të paktën një fushë.");
        }
        Klientet updated = klientetRepository.update(updateKlientiDto);
        if (updated == null) {
            throw new OperationFailedException("Update-i dështoi. Klienti nuk u përditësua.");
        }

        return updated;
    }
    public boolean delete(int id) throws Exception{
        if (id <= 0) {
            throw new ValidationException("ID e klientit është e pavlefshme.Duhet te jete>0 !");
        }
        Klientet klienti=klientetRepository.getById(id);
        if(klienti==null){
            throw new ResourceNotFoundException("Klienti nuk ekzistone!");
        }
        return klientetRepository.delete(id);
    }
    public List<Klientet> kerkoKlientetMeEmriPlote(String emriPlote) {
        return klientetRepository.searchByFullName(emriPlote);
    }
}
