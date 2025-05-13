package services;

import CustomExceptions.*;
import models.dto.Riparimet.CreateRiparimetDto;
import models.dto.Riparimet.Riparimet;
import models.dto.Riparimet.UpdateRiparimetDto;
import repository.RiparimetRepository;
import repository.VeturatRepository;
import repository.SherbimetRepository;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class RiparimetService {
    private final RiparimetRepository riparimetRepository;
    private final VeturatRepository veturatRepository;
    private final SherbimetRepository sherbimetRepository;

    public RiparimetService() {
        this.riparimetRepository = new RiparimetRepository();
        this.veturatRepository = new VeturatRepository();
        this.sherbimetRepository = new SherbimetRepository();
    }

    public List<Riparimet> getAll() {
        return riparimetRepository.getAll();
    }

    public Riparimet getById(int id) throws Exception {
        if (id <= 0) {
            throw new InvalidInputException("ID e riparimit duhet të jetë pozitive!");
        }
        Riparimet riparimi = riparimetRepository.getById(id);
        if (riparimi == null) {
            throw new ResourceNotFoundException("Riparimi me ID " + id + " nuk ekziston!");
        }
        return riparimi;
    }

    public Riparimet create(CreateRiparimetDto dto) throws Exception {
        validateCreateDto(dto);
        return riparimetRepository.create(dto);
    }

    private void validateCreateDto(CreateRiparimetDto dto) throws Exception {
        if (dto.getKostoRiparimit() <= 0.0) {
            throw new ValidationException("Kostoja e riparimit duhet të jetë një vlerë pozitive.");
        }
        if (dto.getStatusi() == null || dto.getStatusi().trim().isEmpty()) {
            throw new InvalidInputException("Statusi nuk duhet të jetë bosh.");
        }
    }

    public Riparimet update(UpdateRiparimetDto dto) throws Exception {
        if (dto.getId() <= 0) {
            throw new ValidationException("ID e riparimit është e pavlefshme.");
        }

        Riparimet riparimiEkzistues = riparimetRepository.getById(dto.getId());
        if (riparimiEkzistues == null) {
            throw new ResourceNotFoundException("Riparimi me ID " + dto.getId() + " nuk ekziston.");
        }

        boolean hasChanges = false;

        if (dto.getStatusi() != null && !dto.getStatusi().trim().isEmpty()) {
            hasChanges = true;
        }

        if (dto.getKostoRiparimit() > 0.0) {
            hasChanges = true;
        }

        if (!hasChanges) {
            throw new InvalidInputException("Duhet të përditësohet të paktën një fushë.");
        }

        Riparimet updated = riparimetRepository.update(dto);
        if (updated == null) {
            throw new OperationFailedException("Update-i dështoi. Riparimi nuk u përditësua.");
        }

        return updated;
    }

    public boolean delete(int id) throws Exception {
        if (id <= 0) {
            throw new ValidationException("ID është e pavlefshme. Duhet të jetë > 0.");
        }
        Riparimet riparimi = riparimetRepository.getById(id);
        if (riparimi == null) {
            throw new ResourceNotFoundException("Riparimi nuk ekziston!");
        }
        return riparimetRepository.delete(id);
    }

    private boolean isValidDate(String date) {
        try {
            LocalDate.parse(date); // format: YYYY-MM-DD
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public List<Riparimet> kerkoSipasStatusit(String statusi) {
        return riparimetRepository.searchByStatus(statusi);
    }

    public Riparimet findByAllFields(Integer veturaId, Integer sherbimiId, String statusi, Double kosto, String data) {
        return riparimetRepository.findByAllFields(veturaId, sherbimiId, statusi, kosto, data);
    }
}
