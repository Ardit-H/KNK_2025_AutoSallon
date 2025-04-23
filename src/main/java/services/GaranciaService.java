package services;

import models.dto.Garancia.CreateGaranciaDto;
import models.dto.Garancia.UpdateGaranciaDto;
import models.dto.Garancia.Garancia;
import repository.GaranciaRepository;

import java.util.List;

public class GaranciaService {
    private GaranciaRepository garanciaRepository;

    public GaranciaService() {
        this.garanciaRepository = new GaranciaRepository();
    }

    public List<Garancia> getAll() {
        return garanciaRepository.getAll();
    }

    public Garancia getById(int id) throws Exception {
        if (id <= 0) {
            throw new IllegalArgumentException("ID duhet të jetë më e madhe se 0.");
        }

        Garancia garancia = garanciaRepository.getById(id);
        if (garancia == null) {
            throw new Exception("Garancia me ID " + id + " nuk ekziston.");
        }

        return garancia;
    }

    public Garancia create(CreateGaranciaDto dto) {
        validateCreateDto(dto);
        return garanciaRepository.create(dto);
    }

    public Garancia update(UpdateGaranciaDto dto) throws Exception {
        if (dto.getId() <= 0) {
            throw new IllegalArgumentException("ID duhet të jetë më e madhe se 0.");
        }

        Garancia ekzistuese = garanciaRepository.getById(dto.getId());
        if (ekzistuese == null) {
            throw new Exception("Garancia me ID " + dto.getId() + " nuk ekziston.");
        }

        boolean kaNdryshime = false;

        if (dto.getDataFillimit() != null && !dto.getDataFillimit().trim().isEmpty()) {
            kaNdryshime = true;
        }

        if (dto.getDataMbarimit() != null && !dto.getDataMbarimit().trim().isEmpty()) {
            kaNdryshime = true;
        }

        if (!kaNdryshime) {
            throw new IllegalArgumentException("Të paktën një fushë duhet të përditësohet.");
        }

        Garancia ePerditesuar = garanciaRepository.update(dto);
        if (ePerditesuar == null) {
            throw new Exception("Update-i dështoi.");
        }

        return ePerditesuar;
    }

    public boolean delete(int id) throws Exception {
        if (id <= 0) {
            throw new IllegalArgumentException("ID është e pavlefshme.");
        }

        Garancia garancia = garanciaRepository.getById(id);
        if (garancia == null) {
            throw new Exception("Garancia nuk ekziston.");
        }

        return garanciaRepository.delete(id);
    }

    private void validateCreateDto(CreateGaranciaDto dto) {
        if (dto.getDataFillimit() == null || dto.getDataFillimit().trim().isEmpty()) {
            throw new IllegalArgumentException("Data e fillimit është e detyrueshme.");
        }

        if (dto.getDataMbarimit() == null || dto.getDataMbarimit().trim().isEmpty()) {
            throw new IllegalArgumentException("Data e mbarimit është e detyrueshme.");
        }
    }
}
