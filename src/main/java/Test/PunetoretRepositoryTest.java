package Test;

import Database.DBConnector;
import models.dto.Punetoret.Punetoret;
import models.dto.Punetoret.UpdatePunetoretDto;
import repository.PunetoretRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PunetoretRepositoryTest {
    public static void main(String[] args) {
        Connection connection = DBConnector.getConnection();

        
        try {
            Statement stm = connection.createStatement();
            String query = "SELECT * FROM punetoret ORDER BY punetor_id DESC LIMIT 1";
            ResultSet result = stm.executeQuery(query);

            if (result.next()) {
                Punetoret punetori = Punetoret.getInstance(result);
                System.out.println("ID: " + punetori.getPunetor_id());
                System.out.println("Emri: " + punetori.getEmri());
                System.out.println("Mbiemri: " + punetori.getMbiemri());
                System.out.println("Pozita: " + punetori.getPozita());
                System.out.println("Telefoni: " + punetori.getTelefoni());
                System.out.println("Email: " + punetori.getEmail());
                System.out.println("Paga: " + punetori.getPaga());
                System.out.println("Data Punesimit: " + punetori.getData_punesimit());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        PunetoretRepository punetoretRepository = new PunetoretRepository();

        // Merr punëtor sipas ID-së
        Punetoret punetori = punetoretRepository.getById(2);
        if (punetori != null) {
            System.out.println("Punëtori me ID 2 ekziston: " + punetori.getEmri() + " " + punetori.getMbiemri());
        }

        // Përditëso një punëtor
        UpdatePunetoretDto update = new UpdatePunetoretDto();
        update.setPunetorId(1);
        update.setPaga(750.0);
        update.setPozita("Menaxher");
        punetoretRepository.update(update);

        // Shto një punëtor të ri
//        CreatePunetoretDto createDto = new CreatePunetoretDto("Elira", "Krasniqi", "Recepsioniste", "049123456", "elira@gmail.com", 550.0, "2025-04-20");
//        punetoretRepository.create(createDto);
    }
}

