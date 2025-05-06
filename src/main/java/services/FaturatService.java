package services;

import CustomExceptions.*;
import models.dto.Faturat.CreateFaturatDto;
import models.dto.Faturat.Faturat;
import models.dto.Faturat.UpdateFaturatDto;
import repository.FaturatRepository;

import java.util.List;
import java.util.regex.Pattern;

public class FaturatService {
    private FaturatRepository faturatRepository;

    public FaturatService() {
        this.faturatRepository = new FaturatRepository();
    }

    public List<Faturat> getAll() {
        return faturatRepository.getAll();
    }

    public Faturat getById(int id) throws Exception {
        if (id <= 0) {
            throw new InvalidInputException("ID duhet të jetë pozitive.");
        }

        Faturat fatura = faturatRepository.getById(id);
        if (fatura == null) {
            throw new ResourceNotFoundException("Fatura me ID " + id + " nuk ekziston.");
        }
        return fatura;
    }

    public Faturat create(CreateFaturatDto dto) throws Exception {
        validateCreateDto(dto);
        return faturatRepository.create(dto);
    }

    private void validateCreateDto(CreateFaturatDto dto) throws Exception {
        if (dto.getShitjeId() <= 0) {
            throw new ValidationException("ID e shitjes është e pavlefshme.");
        }
        if (dto.getShumaTotale() < 0) {
            throw new ValidationException("Shuma totale duhet të jetë pozitive.");
        }
        if (dto.getLlojiPageses() == null || !isValidPaymentType(dto.getLlojiPageses())) {
            throw new ValidationException("Lloji i pagesës është i pavlefshëm.");
        }
    }

    public Faturat update(UpdateFaturatDto dto) throws Exception {
        if (dto.getId() <= 0) {
            throw new ValidationException("ID është e pavlefshme.");
        }

        Faturat existing = faturatRepository.getById(dto.getId());
        if (existing == null) {
            throw new ResourceNotFoundException("Fatura nuk ekziston.");
        }

        boolean hasChanges = false;

        if (dto.getShumaTotale() >= 0) hasChanges = true;
        if (dto.getLlojiPageses() != null) {
            if (!isValidPaymentType(dto.getLlojiPageses())) {
                throw new ValidationException("Lloji i pagesës është i pavlefshëm.");
            }
            hasChanges = true;
        }

        if (!hasChanges) {
            throw new InvalidInputException("Duhet të përditësohet të paktën një fushë.");
        }

        Faturat updated = faturatRepository.update(dto);
        if (updated == null) {
            throw new OperationFailedException("Update-i dështoi.");
        }
        return updated;
    }

    public boolean delete(int id) throws Exception {
        if (id <= 0) {
            throw new ValidationException("ID është e pavlefshme.");
        }

        Faturat fatura = faturatRepository.getById(id);
        if (fatura == null) {
            throw new ResourceNotFoundException("Fatura nuk ekziston.");
        }

        return faturatRepository.delete(id);
    }

    private boolean isValidPaymentType(String type) {
        return List.of("CASH", "KARTE", "BANK", "TJETER").contains(type.toUpperCase());
    }
}
