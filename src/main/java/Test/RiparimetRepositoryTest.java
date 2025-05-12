package Test;

import Database.DBConnector;
import models.dto.Riparimet.CreateRiparimetDto;
import models.dto.Riparimet.Riparimet;
import models.dto.Riparimet.UpdateRiparimetDto;
import repository.RiparimetRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RiparimetRepositoryTest {
    public static void main(String[] args) {
        Connection connection = DBConnector.getConnection();

        try {
            // Merr riparimin e fundit të shtuar
            Statement stmt = connection.createStatement();
            String query = "SELECT * FROM riparimet ORDER BY id DESC LIMIT 1";
            ResultSet result = stmt.executeQuery(query);
            if (result.next()) {
                Riparimet riparimi = Riparimet.getInstance(result);
                System.out.println("ID: " + riparimi.getId());
                System.out.println("Statusi: " + riparimi.getStatusi());
                System.out.println("Data Riparimit: " + riparimi.getDataRiparimit());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        RiparimetRepository repository = new RiparimetRepository();

        // Merr një riparim sipas ID
        Riparimet riparimi = repository.getById(2);
        if (riparimi != null) {
            System.out.println("Riparimi ekziston me ID: " + riparimi.getId());
        }

        // Update i një riparimi ekzistues
        UpdateRiparimetDto update = new UpdateRiparimetDto();
        update.setId(2);
        update.setStatusi("Rivlerësuar");
        update.setKostoRiparimit(75.0);
        repository.update(update);

        // Krijimi i një riparimi të ri
//        CreateRiparimetDto createDto = new CreateRiparimetDto();
//        createDto.setVeturaId(1);
//        createDto.setSherbimiId(1);
//        createDto.setStatusi("Ne Pritje");
//        createDto.setKostoRiparimit(45.5);
//        createDto.setDataRiparimit("2024-05-10");
//        repository.create(createDto);

        // Fshirja e një riparimi
//        repository.delete(5);
    }
}
