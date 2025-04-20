package Test;


import Database.DBConnector;
import models.dto.Rezervimet.CreateRezervimetDto;
import models.dto.Rezervimet.UpdateRezervimetDto;
import models.dto.Rezervimet.Rezervimet;
import repository.RezervimetRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RezervimetRepositoryTest {
    public static void main(String[] args) {
        Connection connection = DBConnector.getConnection();

        try {
            Statement stm = connection.createStatement();
            String query = "SELECT * FROM rezervimet ORDER BY id DESC LIMIT 1";
            ResultSet result = stm.executeQuery(query);

            if (result.next()) {
                Rezervimet rezervimi = Rezervimet.getInstance(result);
                System.out.println("ID: " + rezervimi.getRezervimiId());
                System.out.println("Klienti ID: " + rezervimi.getKlientiId());
                System.out.println("Veturat ID: " + rezervimi.getVeturaId());
                System.out.println("Data: " + rezervimi.getDataRezervimit());
                System.out.println("Statusi: " + rezervimi.getStatusi());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        RezervimetRepository rezervimetRepository = new RezervimetRepository();


        Rezervimet rezervimi = rezervimetRepository.getById(2);
        if (rezervimi != null) {
            System.out.println("Rezervimi me ID 2 ekziston: " + rezervimi.getRezervimiId());
        }


        UpdateRezervimetDto update = new UpdateRezervimetDto();
        update.setRezervimiId(1);
        //update.setStatusi("anuluar"); // shembull
        rezervimetRepository.update(update);


       // CreateRezervimetDto createDto = new CreateRezervimetDto(1, 2, "2025-04-20", "aktiv");
        //rezervimetRepository.create(createDto);
    }
}

