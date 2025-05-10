package services;

import CustomExceptions.DuplicateResourceException;
import CustomExceptions.InvalidInputException;
import CustomExceptions.ResourceNotFoundException;
import CustomExceptions.ValidationException;
import models.dto.Klientet.Klientet;
import models.dto.Sherbimet.Sherbimet;
import models.dto.Vleresimet.CreateVleresimetDto;
import models.dto.Vleresimet.UpdateVleresimetDto;
import models.dto.Vleresimet.Vleresimet;
import repository.KlientetRepository;
import repository.PerdoruesitRepository;
import repository.VeturatRepository;
import repository.VleresimetRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class VleresimetService {
    private VleresimetRepository vleresimetRepository;
    private PerdoruesitRepository perdoruesitRepository;
    private VeturatRepository veturatRepository;
    public VleresimetService(){
        this.vleresimetRepository=new VleresimetRepository();
        this.perdoruesitRepository=new PerdoruesitRepository();
        this.veturatRepository=new VeturatRepository();
    }
    public Vleresimet create(CreateVleresimetDto dto)throws Exception{
        if(perdoruesitRepository.getById(dto.getPerdoruesiId())==null){
            throw new ResourceNotFoundException("Perdoruesi me ID " + dto.getPerdoruesiId() + " nuk ekziston!");
        }

        if(veturatRepository.getById(dto.getVeturaId())==null){
            throw new ResourceNotFoundException("Vetura me ID " + dto.getVeturaId() + " nuk ekziston!");
        }

        if (hasVotedBefore(dto.getPerdoruesiId(), dto.getVeturaId())) {
            throw new DuplicateResourceException("Perdoruesi ka bërë tashmë një vlerësim për këtë veturë!");
        }

        if(dto.getVleresimi() < 1 || dto.getVleresimi() > 5){
            throw new InvalidInputException("Vlerësimi duhet të jetë ndërmjet 1 dhe 5!");
        }

        return vleresimetRepository.create(dto);
    }
    private boolean hasVotedBefore(int perdoruesiId, int veturaId) throws SQLException {
        List<Vleresimet> vleresimetList = vleresimetRepository.getAll();
        for (Vleresimet vleresim : vleresimetList) {
            if (vleresim.getPerdoruesiId() == perdoruesiId && vleresim.getVeturaId() == veturaId) {
                return true;  // Klienti ka bërë një vlerësim për këtë veturë
            }
        }
        return false; // Klienti nuk ka bërë ende vlerësim
    }
    public Vleresimet update(UpdateVleresimetDto dto) throws Exception{
        if(dto.getVleresimiId()<=0){
            throw new ValidationException("ID eshte e pavlefshme!");
        }
        Vleresimet vleresimi = vleresimetRepository.getById(dto.getVleresimiId());
        if(vleresimi==null){
            throw new ResourceNotFoundException("Vleresimi me ID: "+dto.getVleresimiId()+" nuk ekziston.");
        }
        if(dto.getPerdoruesiId()!=null && perdoruesitRepository.getById(dto.getPerdoruesiId())==null){
            throw new ResourceNotFoundException("Perdoruesi me ID " + dto.getPerdoruesiId() + " nuk ekziston!");
        }

        if(dto.getVeturaId()!=null && veturatRepository.getById(dto.getVeturaId())==null){
            throw new ResourceNotFoundException("Vetura me ID " + dto.getVeturaId() + " nuk ekziston!");
        }

        if(dto.getVleresimi()!=null && (dto.getVleresimi() < 1 || dto.getVleresimi()>5)){
            throw new InvalidInputException("Vlerësimi duhet të jetë ndërmjet 1 dhe 5!");
        }

        return vleresimetRepository.update(dto);
    }

    public Vleresimet getById(int id)throws Exception{
        if(id<0){
            throw new InvalidInputException("ID e vleresimit duhet të jetë pozitive!");
        }
        Vleresimet vleresimet=this.vleresimetRepository.getById(id);
        if(vleresimet==null){
            throw new ResourceNotFoundException("Vleresimi me id: "+id+" nuk ekziston!");
        }
        return vleresimet;
    }

    public List<Vleresimet> getAll(){
        return vleresimetRepository.getAll();
    }

    public boolean delete(int id)throws Exception{
        if(id <= 0){
            throw new ValidationException("ID e vleresimit është e pavlefshme.Duhet te jete>0 !");
        }
        Vleresimet vleresimet=vleresimetRepository.getById(id);
        if(vleresimet==null){
            throw new ResourceNotFoundException("Vleresimi nuk ekzistone!");
        }
        return vleresimetRepository.delete(id);
    }
    public List<Vleresimet> getVleresimetByPerdoruesiId(int perdoruesiId){
        List<Vleresimet> result = new ArrayList<>();
        List<Vleresimet> vleresimet = vleresimetRepository.getAll();
        for(Vleresimet vleresim : vleresimet){
            if (vleresim.getPerdoruesiId() == perdoruesiId) {
                result.add(vleresim);
            }
        }
        return result;
    }
    public List<Vleresimet> getVleresimetByVeturaId(int veturaId){
        List<Vleresimet> vleresimet = vleresimetRepository.getAll();
        List<Vleresimet> veturaVleresimet = new ArrayList<>();

        for (Vleresimet vleresim : vleresimet) {
            if (vleresim.getVeturaId() == veturaId) {
                veturaVleresimet.add(vleresim);
            }
        }

        return veturaVleresimet;
    }
    public double getMesatarjaEVleresimevePerVeture(int veturaId){
        List<Vleresimet> vleresime = vleresimetRepository.getAll();
        int total = 0;
        int count = 0;

        for(Vleresimet vleresim : vleresime){
            if(vleresim.getVeturaId() == veturaId){
                total += vleresim.getVleresimi();
                count++;
            }
        }
        if(count == 0){
            return 0.0;
        }
        return (double) total/count;
    }
    public void showPositiveAndNegativeVleresimet(int veturaId){
        List<Vleresimet> vleresimet = getVleresimetByVeturaId(veturaId);

        System.out.println("Vlerësime Pozitive:");
        for (Vleresimet vleresim : vleresimet) {
            if (vleresim.getVleresimi() > 3) {
                System.out.println(vleresim.getKomenti());
            }
        }

        System.out.println("Vlerësime Negative:");
        for (Vleresimet vleresim : vleresimet) {
            if (vleresim.getVleresimi() <= 3) {
                System.out.println(vleresim.getKomenti());
            }
        }
    }
    public List<Vleresimet> getVleresimetByUserId(int userId) {
        return vleresimetRepository.getByUserId(userId);
    }
}
