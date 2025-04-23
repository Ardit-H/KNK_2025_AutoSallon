package repository;

import models.dto.Rezervimet.Rezervimet;
import models.dto.Rezervimet.CreateRezervimetDto;
import models.dto.Rezervimet.UpdateRezervimetDto;
import models.dto.Klientet.Klientet;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RezervimetRepository extends BaseRepository<Rezervimet, CreateRezervimetDto, UpdateRezervimetDto> {
    public RezervimetRepository() {
        super("rezervimet");
    }

    public Rezervimet fromResultSet(ResultSet result) throws SQLException {
        return Rezervimet.getInstance(result);
    }

    public Rezervimet create(CreateRezervimetDto rezervimetDto) {
        String query = """
                INSERT INTO
                 REZERVIMET 
                (KLIENTI_ID, VETURA_ID, DATAREZERVIMIT, STATUSI)
                VALUES(?, ?, ?, ?)
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setInt(1, rezervimetDto.getKlientiId());
            pstm.setInt(2, rezervimetDto.getVeturaId());
            pstm.setString(3, rezervimetDto.getDataRezervimit());
            pstm.setString(4, rezervimetDto.getStatusi());
            pstm.execute();
            ResultSet res = pstm.getGeneratedKeys();
            if (res.next()) {
                int id = res.getInt(1);
                return this.getById(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Rezervimet update(UpdateRezervimetDto rezervimetDto) {
        StringBuilder query = new StringBuilder("UPDATE REZERVIMET SET ");
        ArrayList<Object> params = new ArrayList<>();
        if (rezervimetDto.getKlientiId() != null) {
            query.append("KLIENTI_ID = ?, ");
            params.add(rezervimetDto.getKlientiId());
        }
        if (rezervimetDto.getVeturaId() != null) {
            query.append("VETURA_ID = ?, ");
            params.add(rezervimetDto.getVeturaId());
        }
        if (rezervimetDto.getDataRezervimit() != null) {
            query.append("DATAREZERVIMIT = ?, ");
            params.add(rezervimetDto.getDataRezervimit());
        }

        if (rezervimetDto.getStatusi() != null) {
            query.append("STATUSI = ?, ");
            params.add(rezervimetDto.getStatusi());
        }

        if (params.isEmpty()) {
            return getById(rezervimetDto.getRezervimiId());
        }

        query.setLength(query.length() - 2);
        query.append("WHERE ID = ?");
        params.add(rezervimetDto.getRezervimiId());

        try {
            PreparedStatement pstm = this.connection.prepareStatement(query.toString());
            for (int i = 0; i < params.size(); i++) {
                pstm.setObject(i + 1, params.get(i));
            }
            int updated = pstm.executeUpdate();
            if (updated == 1) {
                return this.getById(rezervimetDto.getRezervimiId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }
}