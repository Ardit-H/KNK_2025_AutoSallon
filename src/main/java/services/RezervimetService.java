package services;

import models.dto.Rezervimet.CreateRezervimetDto;
import models.dto.Rezervimet.Rezervimet;
import models.dto.Rezervimet.UpdateRezervimetDto;
import models.dto.Veturat.Veturat;
import repository.RezervimetRepository;
import services.VeturatService;
import repository.VeturatRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RezervimetService {
    private RezervimetRepository rezervimetRepository;

    public RezervimetService() {
        this.rezervimetRepository = new RezervimetRepository();
    }

    public List<Rezervimet> getAll() {
        return rezervimetRepository.getAll();
    }

    public Rezervimet getById(int id) throws Exception {
        if (id < 0) {
            throw new Exception("ID e rezervimit duhet të jetë pozitive!");
        }
        Rezervimet rezervimi = rezervimetRepository.getById(id);
        if (rezervimi == null) {
            throw new Exception("Rezervimi me ID " + id + " nuk ekziston!");
        }
        return rezervimi;
    }

    public Rezervimet create(CreateRezervimetDto dto) {

        return rezervimetRepository.create(dto);
    }

    public Rezervimet update(UpdateRezervimetDto dto) throws Exception {
        if (dto.getRezervimiId() <= 0) {
            throw new Exception("ID është e pavlefshme.");
        }

        Rezervimet ekzistuese = rezervimetRepository.getById(dto.getRezervimiId());
        if (ekzistuese == null) {
            throw new Exception("Rezervimi me ID " + dto.getRezervimiId() + " nuk ekziston.");
        }

        Rezervimet updated = rezervimetRepository.update(dto);
        if (updated == null) {
            throw new Exception("Update-i dështoi.");
        }
        return updated;
    }

    public boolean delete(int id) throws Exception {
        if (id <= 0) {
            throw new IllegalArgumentException("ID është e pavlefshme.");
        }

        Rezervimet rezervimi = rezervimetRepository.getById(id);
        if (rezervimi == null) {
            throw new Exception("Rezervimi nuk ekziston.");
        }
        return rezervimetRepository.delete(id);
    }


    public List<Veturat> gjejVeturatELiraPerDate(String data) throws Exception {
        VeturatRepository veturatRepository = new VeturatRepository();
        List<Veturat> teGjitha = veturatRepository.getAll();
        List<Rezervimet> rezervime = rezervimetRepository.getAll();
        Set<Integer> veturaTeRezervuara = rezervime.stream()
                .filter(r -> r.getDataRezervimit().equals(data))
                .map(r -> r.getVeturaId())
                .collect(Collectors.toSet());

        return teGjitha.stream()
                .filter(v -> !veturaTeRezervuara.contains(v.getId()))
                .collect(Collectors.toList());
    }


}
