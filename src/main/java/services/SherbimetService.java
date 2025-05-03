package services;

import CustomExceptions.InvalidInputException;
import CustomExceptions.OperationFailedException;
import CustomExceptions.ResourceNotFoundException;
import CustomExceptions.ValidationException;
import models.dto.Sherbimet.CreateSherbimetDto;
import models.dto.Sherbimet.Sherbimet;
import models.dto.Sherbimet.UpdateSherbimetDto;
import repository.SherbimetRepository;

import java.util.List;

public class SherbimetService {
    private SherbimetRepository sherbimet;
    public SherbimetService(){
        this.sherbimet=new SherbimetRepository();
    }
    public List<Sherbimet> getAll() {
        return sherbimet.getAll();
    }
    public Sherbimet getById(int id) throws Exception {
        if (id <= 0) {
            throw new InvalidInputException("ID duhet të jetë pozitiv.");
        }
        Sherbimet sherbimi = sherbimet.getById(id);
        if (sherbimi == null) {
            throw new ResourceNotFoundException("Shërbimi me ID " + id + " nuk ekziston.");
        }
        return sherbimi;
    }

    public Sherbimet create(CreateSherbimetDto create) throws Exception{
        if(create.getEmri()==null || create.getEmri().trim().isEmpty()){
            throw new InvalidInputException("Emri i sherbimit eshte i domosdoshem!");
        }
        if(create.getÇmimi()<0){
            throw new InvalidInputException("Cmimi duhet te jete pozitiv!");
        }
        return sherbimet.create(create);
    }
    public Sherbimet update(UpdateSherbimetDto update)throws Exception{
        if(update.getId()<=0){
            throw new ValidationException("ID eshte e pavlefshme!");
        }
        Sherbimet sherbimi = sherbimet.getById(update.getId());
        if(sherbimi==null){
            throw new ResourceNotFoundException("Sherbimi me ID: "+update.getId()+" nuk ekziston.");
        }
        boolean hasChanges=false;
        if(update.getEmri()!=null){
            if(update.getEmri().trim().isEmpty()){
                throw new InvalidInputException("Emri nuk mund te jete bosh!");
            }
            hasChanges=true;
        }
        if(update.getPershkrimi()!=null){
            hasChanges=true;
        }
        if(update.getÇmimi()!=null){
            if(update.getÇmimi()<0){
                throw  new InvalidInputException("Cmimi duhet te jete >=0!");
            }
            hasChanges=true;
        }
        if(!hasChanges){
            throw new InvalidInputException("Duhet te perditesohet te pakten nje fushe!");
        }
        Sherbimet updated = sherbimet.update(update);
        if (updated==null) {
            throw new OperationFailedException("Përditësimi dështoi!");
        }
        return updated;
    }

    public boolean delete(int id) throws Exception {
        if (id<=0) {
            throw new ValidationException("ID është e pavlefshme!");
        }
        Sherbimet sherbimi = sherbimet.getById(id);
        if (sherbimi==null) {
            throw new ResourceNotFoundException("Shërbimi nuk ekziston!");
        }
        return sherbimet.delete(id);
    }
}
