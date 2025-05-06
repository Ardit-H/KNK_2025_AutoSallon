package Test;

import Database.DBConnector;
import models.dto.Faturat.CreateFaturatDto;
import models.dto.Faturat.Faturat;
import models.dto.Faturat.UpdateFaturatDto;
import repository.FaturatRepository;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class FaturatRepositoryTest {
    public static void main(String[] args) {
        Connection connection = DBConnector.getConnection();

        try {
            // Leximi i fundit nga databaza me SQL direkt
            Statement stm = connection.createStatement();
            String query = "SELECT * FROM Faturat ORDER BY ID DESC LIMIT 1";
            ResultSet result = stm.executeQuery(query);
            if (result.next()) {
                Faturat faturat = Faturat.getInstance(result);
                System.out.println("Id: " + faturat.getId());
                System.out.println("Shuma Totale: " + faturat.getShumaTotale());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        FaturatRepository faturatRepository = new FaturatRepository();

        // Testo getById
        Faturat faturat = faturatRepository.getById(3);
        if (faturat != null) {
            System.out.println("ID: " + faturat.getId());
            System.out.println("Lloji i Pagesës: " + faturat.getLlojiPageses());
        }

        // Testo update
        UpdateFaturatDto updateDto = new UpdateFaturatDto();
        updateDto.setId(3);
        updateDto.setShumaTotale(245.50);
        updateDto.setLlojiPageses("Kredi");
        faturatRepository.update(updateDto);

        // Testo create
//        CreateFaturatDto createDto = new CreateFaturatDto();
//        createDto.setShitjeId(1);
//        createDto.setDataFatures(new String("31.05.2025"));
//        createDto.setShumaTotale(129.99);
//        createDto.setLlojiPageses("Cash");
//        faturatRepository.create(createDto);

        // Testo delete
//        faturatRepository.delete(5);
    }
}
