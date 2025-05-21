package services;

import CustomExceptions.*;
import models.dto.Partneret.CreatePartneretDto;
import models.dto.Partneret.Partneret;
import models.dto.Partneret.UpdatePartneretDto;
import repository.PartneretRepository;

import java.util.List;
import java.util.regex.Pattern;

public class PartneretService {
    private PartneretRepository partneretRepository;

    public PartneretService() {
        this.partneretRepository = new PartneretRepository();
    }

    public List<Partneret> getAll() {
        return partneretRepository.getAll();
    }

    public Partneret getById(int id) throws Exception {
        if (id < 0) {
            throw new InvalidInputException("ID duhet të jetë pozitive.");
        }
        Partneret partneri = partneretRepository.getById(id);
        if (partneri == null) {
            throw new ResourceNotFoundException("Partneri me ID " + id + " nuk ekziston.");
        }
        return partneri;
    }

    public Partneret create(CreatePartneretDto dto) throws Exception {
        validateCreateDto(dto);
        return partneretRepository.create(dto);
    }

    private void validateCreateDto(CreatePartneretDto dto) throws Exception {
        if (isNullOrShort(dto.getEmriKompanise(), 3)) {
            throw new InvalidInputException("Emri i kompanisë duhet të ketë të paktën 3 karaktere.");
        }
        if (!isValidPartnerType(dto.getLlojiPartnerit())) {
            throw new ValidationException("Lloji i partnerit është i pavlefshëm.");
        }
        if (dto.getEmail() != null && !isValidEmail(dto.getEmail())) {
            throw new ValidationException("Email është i pavlefshëm.");
        }
        if (dto.getEmail() != null && partneretRepository.existsByEmail(dto.getEmail())) {
            throw new DuplicateResourceException("Ky email është tashmë i regjistruar!");
        }
        if (dto.getTelefoni() != null && !isValidPhone(dto.getTelefoni())) {
            throw new ValidationException("Numri i telefonit është i pavlefshëm.");
        }
        if (dto.getTelefoni() != null && partneretRepository.existsByPhoneNumber(dto.getTelefoni())) {
            throw new DuplicateResourceException("Ky numër telefoni është tashmë i regjistruar!");
        }
        if (isNullOrShort(dto.getAdresa(), 5)) {
            throw new InvalidInputException("Adresa duhet të ketë të paktën 5 karaktere.");
        }
    }

    public Partneret update(UpdatePartneretDto dto) throws Exception {
        if (dto.getId() <= 0) {
            throw new ValidationException("ID është e pavlefshme.");
        }

        Partneret existing = partneretRepository.getById(dto.getId());
        if (existing == null) {
            throw new ResourceNotFoundException("Partneri nuk ekziston.");
        }

        boolean hasChanges = false;

        if (dto.getEmail() != null) {
            if (!isValidEmail(dto.getEmail())) {
                throw new ValidationException("Email është i pavlefshëm.");
            }
            if (partneretRepository.existsByEmailExceptId(dto.getEmail(), dto.getId())) {
                throw new DuplicateResourceException("Ky email është tashmë i përdorur nga një partner tjetër.");
            }
            hasChanges = true;
        }

        if (dto.getTelefoni() != null) {
            if (!isValidPhone(dto.getTelefoni())) {
                throw new ValidationException("Numri i telefonit është i pavlefshëm.");
            }
            if (partneretRepository.existsByPhoneNumberExceptId(dto.getTelefoni(), dto.getId())) {
                throw new DuplicateResourceException("Ky numër telefoni është tashmë i përdorur nga një partner tjetër.");
            }
            hasChanges = true;
        }

        if (dto.getAdresa() != null) {
            if (dto.getAdresa().trim().length() < 5) {
                throw new InvalidInputException("Adresa duhet të ketë të paktën 5 karaktere.");
            }
            hasChanges = true;
        }

        if (dto.getPersonKontakti() != null) {
            if (dto.getPersonKontakti().trim().length() < 3) {
                throw new InvalidInputException("Emri i personit kontaktues duhet të ketë të paktën 3 karaktere.");
            }
            hasChanges = true;
        }

        if (!hasChanges) {
            throw new InvalidInputException("Duhet të përditësohet të paktën një fushë.");
        }

        Partneret updated = partneretRepository.update(dto);
        if (updated == null) {
            throw new OperationFailedException("Update-i dështoi.");
        }
        return updated;
    }


    public boolean delete(int id) throws Exception {
        if (id <= 0) {
            throw new ValidationException("ID është e pavlefshme.");
        }

        Partneret partneri = partneretRepository.getById(id);
        if (partneri == null) {
            throw new ResourceNotFoundException("Partneri nuk ekziston.");
        }

        return partneretRepository.delete(id);
    }

    private boolean isValidEmail(String email) {
        return email != null && Pattern.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", email);
    }

    private boolean isValidPhone(String phone) {
        return phone != null && Pattern.matches("^\\+?[0-9]{7,15}$", phone);
    }

    private boolean isNullOrShort(String s, int minLength) {
        return s == null || s.trim().length() < minLength;
    }

    private boolean isValidPartnerType(String type) {
        return type != null && List.of("Servis", "Sigurim", "Marketing", "Tjetër").contains(type);
    }
}
