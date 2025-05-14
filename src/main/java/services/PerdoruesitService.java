package services;

import models.dto.Klientet.CreateKlientetDto;
import models.dto.Klientet.Klientet;
import models.dto.Perdoruesit.CreatePerdoruesitDto;
import models.dto.Perdoruesit.Perdoruesit;
import models.dto.Perdoruesit.UpdatePerdoruesitDto;
import repository.KlientetRepository;
import repository.PerdoruesitRepository;
import utils.PasswordUtil;

import java.util.List;
import java.util.regex.Pattern;

public class PerdoruesitService {
    private PerdoruesitRepository perdoruesitRepository;
    private KlientetRepository klientetRepository;
    private KlientetService klientetService;

    public PerdoruesitService() {
        this.perdoruesitRepository = new PerdoruesitRepository();
        this.klientetRepository=new KlientetRepository();
        this.klientetService=new KlientetService();
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

        Perdoruesit perdoruesi= perdoruesitRepository.create(dto.getEmri(),dto.getMbiemri(), dto.getEmail(), hashed, salt,dto.getNrtelefonit(),dto.getAdresa());

        return perdoruesi;
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
        if (isNullOrShort(createPerdoruesitDto.getMbiemri(), 3)) {
            throw new IllegalArgumentException("Mbiemri duhet të ketë të paktën 3 karaktere.");
        }
        if (!isValidPhone(createPerdoruesitDto.getNrtelefonit())) {
            throw new IllegalArgumentException("Numri i telefonit është i pavlefshëm.");
        }
        if (isNullOrShort(createPerdoruesitDto.getAdresa(), 5)) {
            throw new IllegalArgumentException("Adresa duhet të ketë të paktën 5 karaktere.");
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

    public Perdoruesit update(UpdatePerdoruesitDto dto) throws Exception {
        if (dto.getId() <= 0) {
            throw new Exception("Id e perdoruesit është e pavlefshme!");
        }

        Perdoruesit perdoruesi = perdoruesitRepository.getById(dto.getId());
        if (perdoruesi == null) {
            throw new Exception("Perdoruesi me ID " + dto.getId() + " nuk ekziston.");
        }

        boolean hasChanges = false;

        if (dto.getEmail() != null) {
            if (!isValidEmail(dto.getEmail())) {
                throw new IllegalArgumentException("Email-i është i pavlefshëm.");
            }
            hasChanges = true;
        }
        if(dto.getPasswordhash()!=null){
            hasChanges = true;

        }
        if(dto.getSalt()!=null){
            hasChanges = true;

        }
        if (dto.getFjalekalimi() != null) {
            String salt = PasswordUtil.generateSalt();
            String hashed = PasswordUtil.hashPassword(dto.getFjalekalimi(), salt);
            dto.setFjalekalimi(hashed);
            dto.setSalt(salt);
            hasChanges = true;
        }

        if (dto.getRoli() != null) {
            hasChanges = true;
        }

        if (!hasChanges) {
            throw new IllegalArgumentException("Duhet të përditësohet të paktën një fushë.");
        }

        Perdoruesit updated = perdoruesitRepository.update(dto);
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
