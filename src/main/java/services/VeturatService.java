package services;

import models.dto.Veturat.CreateVeturatDto;
import models.dto.Veturat.Veturat;
import models.dto.Veturat.UpdateVeturatDto;
import repository.VeturatRepository;

import java.util.List;
import java.util.regex.Pattern;

public class VeturatService {
    private VeturatRepository veturatRepository;

    public VeturatService() {
        this.veturatRepository = new VeturatRepository();
    }

    public List<Veturat> getAll() {
        return veturatRepository.getAll();
    }
    public Veturat getById(int id) throws Exception {
        if (id < 0) {
            throw new Exception("ID e veturës duhet të jetë pozitive!");
        }
        Veturat vetura = veturatRepository.getById(id);
        if (vetura == null) {
            throw new Exception("Vetura me ID: " + id + " nuk ekziston!");
        }
        return vetura;
    }

    public Veturat create(CreateVeturatDto createVeturat) {
        validateCreateDto(createVeturat);
        return veturatRepository.create(createVeturat);
    }

    private void validateCreateDto(CreateVeturatDto dto) {
        if (isNullOrShort(dto.getProdhuesi(), 2)) {
            throw new IllegalArgumentException("Prodhuesi duhet të ketë të paktën 2 karaktere.");
        }
        if (isNullOrShort(dto.getModeli(), 2)) {
            throw new IllegalArgumentException("Modeli duhet të ketë të paktën 2 karaktere.");
        }
        if (dto.getVitiProdhimit() < 1900 || dto.getVitiProdhimit() > 2025) {
            throw new IllegalArgumentException("Viti i prodhimit është i pavlefshëm.");
        }
        if (isNullOrShort(dto.getNgjyra(), 3)) {
            throw new IllegalArgumentException("Ngjyra duhet të ketë të paktën 3 karaktere.");
        }
        if (dto.getCmimi() <= 0) {
            throw new IllegalArgumentException("Çmimi duhet të jetë më i madh se 0.");
        }
        if (isNullOrShort(dto.getGjendja(), 2)) {
            throw new IllegalArgumentException("Gjendja nuk është e vlefshme.");
        }
        if (dto.getKilometrazha() < 0) {
            throw new IllegalArgumentException("Kilometrazha nuk mund të jetë negative.");
        }
        if (isNullOrShort(dto.getTipiKarburant(), 3)) {
            throw new IllegalArgumentException("Tipi i karburantit duhet të jetë i vlefshëm.");
        }
    }

    private boolean isNullOrShort(String s, int minLength) {
        return s == null || s.trim().length() < minLength;
    }

    public Veturat update(UpdateVeturatDto updateDto) throws Exception {
        if (updateDto.getId() <= 0) {
            throw new Exception("ID e veturës është e pavlefshme!");
        }

        Veturat ekzistuese = veturatRepository.getById(updateDto.getId());
        if (ekzistuese == null) {
            throw new Exception("Vetura me ID " + updateDto.getId() + " nuk ekziston.");
        }

        boolean hasChanges = false;

        if (updateDto.getGjendja() != null && updateDto.getGjendja().trim().length() >= 2) {
            hasChanges = true;
        }

        if (!hasChanges) {
            throw new IllegalArgumentException("Duhet të përditësohet të paktën një fushë.");
        }

        Veturat updated = veturatRepository.update(updateDto);
        if (updated == null) {
            throw new Exception("Update-i dështoi. Vetura nuk u përditësua.");
        }

        return updated;
    }

    public boolean delete(int id) throws Exception {
        if (id <= 0) {
            throw new IllegalArgumentException("ID e veturës është e pavlefshme. Duhet të jetë > 0!");
        }

        Veturat vetura = veturatRepository.getById(id);
        if (vetura == null) {
            throw new Exception("Vetura nuk ekziston!");
        }

        return veturatRepository.delete(id);
    }



}
