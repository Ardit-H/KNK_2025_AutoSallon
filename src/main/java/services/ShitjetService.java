package services;

import CustomExceptions.DuplicateResourceException;
import CustomExceptions.InvalidInputException;
import CustomExceptions.ResourceNotFoundException;
import CustomExceptions.ValidationException;
import models.dto.Shitjet.CreateShitjetDto;
import models.dto.Shitjet.Shitjet;
import models.dto.Shitjet.UpdateShitjeDto;
import repository.KlientetRepository;
import repository.PunetoretRepository;
import repository.ShitjetRepository;
import repository.VeturatRepository;

import java.util.List;
import java.sql.SQLException;

public class ShitjetService {
    private ShitjetRepository shitjetRepository;
    private KlientetRepository klientetRepository;
    private VeturatRepository veturatRepository;
    private PunetoretRepository punetoretRepository;
    public ShitjetService(){
        this.shitjetRepository = new ShitjetRepository();
        this.klientetRepository = new KlientetRepository();
        this.veturatRepository = new VeturatRepository();
        this.punetoretRepository = new PunetoretRepository();
    }
    public Shitjet create(CreateShitjetDto dto) throws Exception{
        if(shitjetRepository.getById(dto.getKid())==null){
            throw new ResourceNotFoundException("Klienti me ID " + dto.getKid() + " nuk ekziston!");
        }

        if(veturatRepository.getById(dto.getVetura_id())==null){
            throw new ResourceNotFoundException("Vetura me ID " + dto.getVetura_id() + " nuk ekziston!");
        }

        if(punetoretRepository.getById(dto.getPunetor_id())==null){
            throw new ResourceNotFoundException("Punetori me ID " + dto.getPunetor_id() + " nuk ekziston!");
        }

        if(hasBuyBefore(dto.getKid(), dto.getVetura_id())){
            throw new DuplicateResourceException("Klienti e ka bere tashme nje blerje per kete veture!");
        }

        return shitjetRepository.create(dto);
    }
    private boolean hasBuyBefore(int kid, int vetura_id) throws SQLException{
        List<Shitjet> shitjetList = shitjetRepository.getAll();
        for(Shitjet shitja : shitjetList){
            if(shitja.getKid() == kid && shitja.getVetura_id() == vetura_id){
                return true;
            }
        }
        return false;
    }
    public Shitjet update(UpdateShitjeDto dto) throws Exception{
        if(dto.getShitjet_id() <= 0){
            throw new ValidationException("ID eshte e pavlefshme!");
        }
        Shitjet shitja = shitjetRepository.getById(dto.getShitjet_id());
        if(shitja==null){
            throw new ResourceNotFoundException("Shitja me ID: " + dto.getShitjet_id() + " nuk ekziston!");
        }
        if(dto.getKid()!=null && klientetRepository.getById(dto.getKid())==null){
            throw new ResourceNotFoundException("Klienti me ID " + dto.getKid() + " nuk ekziston!");
        }
        if(dto.getVetura_id()!=null && veturatRepository.getById(dto.getVetura_id())==null){
            throw new ResourceNotFoundException("Vetura me ID " + dto.getVetura_id() + " nuk ekziston!");
        }
        if(dto.getPunetor_id()!=null && punetoretRepository.getById(dto.getPunetor_id())==null){
            throw new ResourceNotFoundException("Punetori me ID " + dto.getPunetor_id() + " nuk ekziston!");
        }

        return shitjetRepository.update(dto);
    }
    public Shitjet getById(int id) throws Exception{
        if(id<0){
            throw new InvalidInputException("ID e shitjes duhet te jete pozitive!");
        }
        Shitjet shitjet = this.shitjetRepository.getById(id);
        if(shitjet==null){
            throw new ResourceNotFoundException("Shitja me ID " + id + " nuk ekziston!");
        }
        return shitjet;
    }
}
