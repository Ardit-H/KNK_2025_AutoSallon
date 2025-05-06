package Test;

import Database.DBConnector;
import models.dto.Partneret.CreatePartneretDto;
import models.dto.Partneret.Partneret;
import models.dto.Partneret.UpdatePartneretDto;
import repository.PartneretRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PartneretRepositoryTest {
    public static void main(String[] args) {
        Connection connection = DBConnector.getConnection();

        try {
            // Leximi i fundit nga databaza me SQL direkt
            Statement stm = connection.createStatement();
            String query = "SELECT * FROM PARTNERET ORDER BY ID DESC LIMIT 1";
            ResultSet result = stm.executeQuery(query);
            if (result.next()) {
                Partneret partneri = Partneret.getInstance(result);
                System.out.println("Id: " + partneri.getId());
                System.out.println("Email: " + partneri.getEmail());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        PartneretRepository partneretRepository = new PartneretRepository();

        // Testo getById
        Partneret partner = partneretRepository.getById(3);
        if (partner != null) {
            System.out.println("ID: " + partner.getId());
            System.out.println("Emri i Kompanisë: " + partner.getEmriKompanise());
        }

        // Testo update
        UpdatePartneretDto updateDto = new UpdatePartneretDto();
        updateDto.setId(3);
        updateDto.setEmail("partneri.ri@email.com");
        updateDto.setAdresa("Rruga e Re, Gjakovë");
        updateDto.setTelefoni("+38349123456");
        partneretRepository.update(updateDto);

        // Testo create
//        CreatePartneretDto createDto = new CreatePartneretDto(
//                "Biznesi X", "Shitje", "biznesx@example.com", "+38345111111", "Mitrovicë"
//        );
//        partneretRepository.create(createDto);

        // Testo delete
//        partneretRepository.delete(5);
    }
}