package services;

import CustomExceptions.InvalidInputException;
import CustomExceptions.ValidationException;
import models.dto.Perdoruesit.Perdoruesit;
import repository.PerdoruesitRepository;

public class LoginService {
    private  PerdoruesitRepository perdoruesitRepository;

    public LoginService(){
    this.perdoruesitRepository=new PerdoruesitRepository();
    }

    public Perdoruesit login(String email,String fjalekalimi)throws Exception{
        if(email==null || fjalekalimi==null||email.isBlank() || fjalekalimi.isBlank()){
            throw new ValidationException("Email dhe fjalekalimi jane te detyrueshem!");
        }
        Perdoruesit user=perdoruesitRepository.getByEmailAndPassword(email,fjalekalimi);
        if(user==null){
            throw new InvalidInputException("Email ose fjalekalimi eshte i pasakte!");
        }
        return user;
    }
}
