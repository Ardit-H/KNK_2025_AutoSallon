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

import java.sql.SQLException;
import java.util.List;

public class ShitjetService {
    private final ShitjetRepository shitjetRepository;
    private final KlientetRepository klientetRepository;
    private final VeturatRepository veturatRepository;
    private final PunetoretRepository punetoretRepository;

    public ShitjetService() {
        this.shitjetRepository = new ShitjetRepository();
        this.klientetRepository = new KlientetRepository();
        this.veturatRepository = new VeturatRepository();
        this.punetoretRepository = new PunetoretRepository();
    }

    public List<Shitjet> getAll() {
        return shitjetRepository.getAll();
    }

    public Shitjet getById(int id) throws Exception {
        validateId(id);
        Shitjet shitja = shitjetRepository.getById(id);
        if (shitja == null) {
            throw new ResourceNotFoundException("Shitja me ID " + id + " nuk ekziston!");
        }
        return shitja;
    }

    public Shitjet create(CreateShitjetDto dto) throws Exception {
        validateCreateDto(dto);
        if (hasBuyBefore(dto.getKid(), dto.getVetura_id())) {
            throw new DuplicateResourceException("Klienti e ka bërë tashmë një blerje për këtë veturë!");
        }
        return shitjetRepository.create(dto);
    }

    public Shitjet update(UpdateShitjeDto dto) throws Exception {
        validateUpdateDto(dto);

        Shitjet ekzistuese = shitjetRepository.getById(dto.getShitjet_id());
        if (ekzistuese == null) {
            throw new ResourceNotFoundException("Shitja me ID: " + dto.getShitjet_id() + " nuk ekziston!");
        }

        // Validimi i referencave nëse janë ndryshuar
        if (dto.getKid() != null && klientetRepository.getById(dto.getKid()) == null) {
            throw new ResourceNotFoundException("Klienti me ID " + dto.getKid() + " nuk ekziston!");
        }
        if (dto.getVetura_id() != null && veturatRepository.getById(dto.getVetura_id()) == null) {
            throw new ResourceNotFoundException("Vetura me ID " + dto.getVetura_id() + " nuk ekziston!");
        }
        if (dto.getPunetor_id() != null && punetoretRepository.getById(dto.getPunetor_id()) == null) {
            throw new ResourceNotFoundException("Punëtori me ID " + dto.getPunetor_id() + " nuk ekziston!");
        }

        return shitjetRepository.update(dto);
    }

    public boolean delete(int id) throws Exception {
        validateId(id);
        Shitjet ekzistuese = shitjetRepository.getById(id);
        if (ekzistuese == null) {
            throw new ResourceNotFoundException("Shitja me ID " + id + " nuk ekziston!");
        }
        return shitjetRepository.delete(id);
    }

    public List<Shitjet> kerkoShitjetMeKlientId(int kid) {
        return shitjetRepository.searchByClientId(kid);
    }

    // --- Metoda ndihmëse private ---

    private void validateId(int id) throws InvalidInputException {
        if (id <= 0) {
            throw new InvalidInputException("ID duhet të jetë pozitive dhe më e madhe se 0!");
        }
    }

    private void validateCreateDto(CreateShitjetDto dto) throws Exception {
        if (dto == null) {
            throw new ValidationException("Data për krijimin e shitjes është e zbrazët.");
        }
        if (dto.getKid() <= 0) {
            throw new ValidationException("ID e klientit është e pavlefshme.");
        }
        if (dto.getVetura_id() <= 0) {
            throw new ValidationException("ID e veturës është e pavlefshme.");
        }
        if (dto.getPunetor_id() <= 0) {
            throw new ValidationException("ID e punëtorit është e pavlefshme.");
        }
        if (dto.getData_shitjes() == null) {
            throw new ValidationException("Data e shitjes duhet të specifikohet.");
        }
        if (dto.getCmimi_final() <= 0) {
            throw new ValidationException("Çmimi final duhet të jetë më i madh se 0.");
        }

        // Kontrollo nëse ekzistojnë referencat
        if (klientetRepository.getById(dto.getKid()) == null) {
            throw new ResourceNotFoundException("Klienti me ID " + dto.getKid() + " nuk ekziston!");
        }
        if (veturatRepository.getById(dto.getVetura_id()) == null) {
            throw new ResourceNotFoundException("Vetura me ID " + dto.getVetura_id() + " nuk ekziston!");
        }
        if (punetoretRepository.getById(dto.getPunetor_id()) == null) {
            throw new ResourceNotFoundException("Punëtori me ID " + dto.getPunetor_id() + " nuk ekziston!");
        }
    }

    private void validateUpdateDto(UpdateShitjeDto dto) throws ValidationException {
        if (dto == null) {
            throw new ValidationException("Data për përditësimin e shitjes është e zbrazët.");
        }
        if (dto.getShitjet_id() <= 0) {
            throw new ValidationException("ID e shitjes është e pavlefshme.");
        }
        if (dto.getCmimi_final() != null && dto.getCmimi_final() <= 0) {
            throw new ValidationException("Çmimi final duhet të jetë më i madh se 0.");
        }
        // Shto validime të tjera sipas nevojës
    }

    private boolean hasBuyBefore(int kid, int vetura_id) throws SQLException {
        List<Shitjet> shitjetList = shitjetRepository.getAll();
        for (Shitjet s : shitjetList) {
            if (s.getKid() == kid && s.getVetura_id() == vetura_id) {
                return true;
            }
        }
        return false;
    }
}