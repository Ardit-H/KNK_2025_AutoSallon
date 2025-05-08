package services;

import models.dto.Perdoruesit.CreatePerdoruesitDto;
import models.dto.Perdoruesit.Perdoruesit;
import models.dto.Perdoruesit.UpdatePerdoruesitDto;
import repository.PerdoruesitRepository;
import utils.PasswordUtil;

import java.util.List;
import java.util.regex.Pattern;

public class PerdoruesitService {
    private PerdoruesitRepository perdoruesitRepository;

    public PerdoruesitService() {
        this.perdoruesitRepository = new PerdoruesitRepository();
    }

    public List<Perdoruesit> getAll() {
        return perdoruesitRepository.getAll();
    }

    public Perdoruesit getById(int id) throws Exception {
        if (id < 0) {
            throw new Exception("ID e perdoruesit duhet të jetë pozitive!");
        }
        Perdoruesit perdoruesi = this.perdoruesitRepository.getById(id);
        if (perdoruesi == null) {
            throw new Exception("Perdoruesi me id: " + id + " nuk ekziston!");
        }
        return perdoruesi;
    }

    public Perdoruesit create(CreatePerdoruesitDto dto) {
        validateCreateDto(dto);

        String salt = PasswordUtil.generateSalt();
        String hashed = PasswordUtil.hashPassword(dto.getFjalekalimi(), salt);

        return perdoruesitRepository.create(dto.getEmri(), dto.getEmail(), hashed, salt);
    }

    private void validateCreateDto(CreatePerdoruesitDto createPerdoruesitDto) {
        if (isNullOrShort(createPerdoruesitDto.getEmri(), 3)) {
            throw new IllegalArgumentException("Emri duhet te kete te pakten 3 karaktere");
        }
        if (!isValidEmail(createPerdoruesitDto.getEmail())) {
            throw new IllegalArgumentException("Email eshte i pavlefshem!");
        }
        if (perdoruesitRepository.getByEmail(createPerdoruesitDto.getEmail()) != null) {
            throw new IllegalArgumentException("Ky email ekziston ne sistem!");
        }

    }

    private boolean isValidEmail(String email) {
        return email != null && Pattern.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9._]+\\.[A-Za-z]{2,}$", email);
    }

    private boolean isValidPhone(String phone) {
        return phone != null && Pattern.matches("^\\+?[0-9]{7,15}$", phone);
    }

    private boolean isNullOrShort(String s, int minLength) {
        return s == null || s.trim().length() < minLength;
    }

    public Perdoruesit update(UpdatePerdoruesitDto updatePerdoruesitDto) throws Exception {
        if (updatePerdoruesitDto.getId() <= 0) {
            throw new Exception("Id e perdoruesit eshte e pavlefshme!");
        }
        Perdoruesit perdoruesi = perdoruesitRepository.getById(updatePerdoruesitDto.getId());
        if (perdoruesi == null) {
            throw new Exception("Perdoruesi me ID " + updatePerdoruesitDto.getId() + " nuk ekziston.");
        }
        boolean hasChanges = false;

        if (updatePerdoruesitDto.getEmail() != null) {
            if (!isValidEmail(updatePerdoruesitDto.getEmail())) {
                throw new IllegalArgumentException("Email-i është i pavlefshëm.");
            }
            hasChanges = true;
        }





        if (!hasChanges) {
            throw new IllegalArgumentException("Duhet të përditësohet të paktën një fushë.");
        }
        Perdoruesit updated = perdoruesitRepository.update(updatePerdoruesitDto);
        if (updated == null) {
            throw new Exception("Update-i dështoi. Perdoruesi nuk u përditësua.");
        }

        return updated;
    }

    public boolean delete(int id) throws Exception {
        if (id <= 0) {
            throw new IllegalArgumentException("ID e perdoruesit është e pavlefshme.Duhet te jete>0 !");
        }
        Perdoruesit perdoruesi = perdoruesitRepository.getById(id);
        if (perdoruesi == null) {
            throw new Exception("Perdoruesi nuk ekzistone!");
        }
        return perdoruesitRepository.delete(id);
    }
}
