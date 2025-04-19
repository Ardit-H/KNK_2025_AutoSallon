package services;

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
            throw new Exception("ID e klientit duhet të jetë pozitive!");
        }
        Klientet klienti=this.klientetRepository.getById(id);
        if(klienti==null){
            throw new Exception("Klienti me id: "+id+" nuk ekziston!");
        }
        return klienti;
    }
    public Klientet create(CreateKlientetDto createKlientet){
        validateCreateDto(createKlientet);
        return klientetRepository.create(createKlientet);
    }
    private void validateCreateDto(CreateKlientetDto createKlientetDto){
        if(isNullOrShort(createKlientetDto.getEmri(),3)){
            throw new IllegalArgumentException("Emri duhet te kete te pakten 3 karaktere");
        }
        if(isNullOrShort(createKlientetDto.getMbiemri(),3)){
            throw new IllegalArgumentException("Mbiemri duhet te kete te pakten 3 karaktere");
        }
        if(!isValidEmail(createKlientetDto.getEmail())){
            throw new IllegalArgumentException("Email eshte i pavlefshem!");
        }
        if(!isValidPhone(createKlientetDto.getNrtelefonit())){
            throw new IllegalArgumentException("Numri i telefonit eshte i pavlefshem!");
        }
        if(isNullOrShort(createKlientetDto.getAdresa(),5)){
            throw new IllegalArgumentException("Adresa duhet te kete te pakten 5 karaktere!");
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
            throw new Exception("Id e klientit eshte e pavlefshme!");
        }
        Klientet klienti=klientetRepository.getById(updateKlientiDto.getId());
        if (klienti==null) {
            throw new Exception("Klienti me ID " + updateKlientiDto.getId() + " nuk ekziston.");
        }
        boolean hasChanges=false;

        if(updateKlientiDto.getEmail()!=null) {
            if (!isValidEmail(updateKlientiDto.getEmail())) {
                throw new IllegalArgumentException("Email-i është i pavlefshëm.");
            }
            hasChanges=true;
        }
        if(updateKlientiDto.getNrtelefonit()!=null) {
            if (!isValidPhone(updateKlientiDto.getNrtelefonit())) {
                throw new IllegalArgumentException("Numri i telefonit është i pavlefshëm.");
            }
            hasChanges=true;
        }
        if(updateKlientiDto.getAdresa()!=null) {
            if (updateKlientiDto.getAdresa().trim().length() < 5) {
                throw new IllegalArgumentException("Adresa duhet të ketë të paktën 5 karaktere.");
            }
            hasChanges=true;
        }

        if(!hasChanges) {
            throw new IllegalArgumentException("Duhet të përditësohet të paktën një fushë.");
        }
        Klientet updated = klientetRepository.update(updateKlientiDto);
        if (updated == null) {
            throw new Exception("Update-i dështoi. Klienti nuk u përditësua.");
        }

        return updated;
    }
}
