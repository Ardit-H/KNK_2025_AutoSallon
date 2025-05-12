package services;

import CustomExceptions.InvalidInputException;
import CustomExceptions.ValidationException;
import com.mysql.cj.xdevapi.Session;
import models.dto.Perdoruesit.Perdoruesit;
import repository.PerdoruesitRepository;
import utils.PasswordUtil;

public class LoginService {
    private  PerdoruesitRepository perdoruesitRepository;

    public LoginService(){
    this.perdoruesitRepository=new PerdoruesitRepository();
    }

    public Perdoruesit login(String email, String password) throws Exception{
        if (email == null || password==null || email.isBlank() || password.isBlank()) {
            throw new ValidationException("Email dhe fjalëkalimi janë të detyrueshëm!");
        }
        Perdoruesit user = perdoruesitRepository.getByEmail(email);
        if (user == null) {
            throw new InvalidInputException("Email ose fjalëkalimi është i pasaktë!");
        }

        String expectedHash = PasswordUtil.hashPassword(password, user.getSalt());
        if (!expectedHash.equals(user.getPasswordHash())) {
            throw new InvalidInputException("Email ose fjalëkalimi është i pasaktë!");
        }
        SessionManager.getInstance().loginUser(user);
        return user;
    }
}
