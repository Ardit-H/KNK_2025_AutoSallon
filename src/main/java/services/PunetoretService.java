package services;

import models.dto.Punetoret.CreatePunetoretDto;
import models.dto.Punetoret.Punetoret;
import models.dto.Punetoret.UpdatePunetoretDto;
import repository.PunetoretRepository;

import java.util.List;
import java.util.regex.Pattern;

public class PunetoretService {
    private PunetoretRepository punetoretRepository;

    public PunetoretService() {
        this.punetoretRepository = new PunetoretRepository();
    }


    public Punetoret getById(int id) throws Exception {
        if (id < 0) {
            throw new Exception("ID e punëtorit duhet të jetë pozitive!");
        }
        Punetoret punetori = punetoretRepository.getById(id);
        if (punetori == null) {
            throw new Exception("Punëtori me ID " + id + " nuk ekziston!");
        }
        return punetori;
    }

    public Punetoret create(CreatePunetoretDto dto) {
        validateCreateDto(dto);
        return punetoretRepository.create(dto);
    }

    private void validateCreateDto(CreatePunetoretDto dto) {
        if (isNullOrShort(dto.getEmri(), 3)) {
            throw new IllegalArgumentException("Emri duhet të ketë të paktën 3 karaktere.");
        }
        if (isNullOrShort(dto.getMbiemri(), 3)) {
            throw new IllegalArgumentException("Mbiemri duhet të ketë të paktën 3 karaktere.");
        }
        if (!isValidEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Email-i është i pavlefshëm.");
        }
        if (dto.getPaga() <= 0) {
            throw new IllegalArgumentException("Paga duhet të jetë pozitive.");
        }
    }

    private boolean isValidEmail(String email) {
        return email != null && Pattern.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", email);
    }

    private boolean isNullOrShort(String value, int minLength) {
        return value == null || value.trim().length() < minLength;
    }

    public Punetoret update(UpdatePunetoretDto dto) throws Exception {
        if (dto.getId() <= 0) {
            throw new Exception("ID e punëtorit është e pavlefshme.");
        }

        Punetoret ekzistues = punetoretRepository.getById(dto.getId());
        if (ekzistues == null) {
            throw new Exception("Punëtori me ID " + dto.getId() + " nuk ekziston.");
        }

        if (dto.getEmail() != null && !isValidEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Email-i është i pavlefshëm.");
        }

        Punetoret updated = punetoretRepository.update(dto);
        if (updated == null) {
            throw new Exception("Update-i dështoi.");
        }

        return updated;
    }

    public boolean delete(int id) throws Exception {
        if (id <= 0) {
            throw new IllegalArgumentException("ID është e pavlefshme.");
        }

        Punetoret punetori = punetoretRepository.getById(id);
        if (punetori == null) {
            throw new Exception("Punëtori nuk ekziston.");
        }
        return punetoretRepository.delete(id);
    }

    public List<Punetoret> getAll() {
        return punetoretRepository.getAll();
    }





}
