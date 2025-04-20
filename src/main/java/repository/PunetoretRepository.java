package repository;

import Database.DBConnector;
import models.dto.Punetoret.Punetoret;
import models.dto.Punetoret.CreatePunetoretDto;
import models.dto.Punetoret.UpdatePunetoretDto;

import java.sql.*;
import java.util.ArrayList;

public class PunetoretRepository extends BaseRepository<Punetoret, CreatePunetoretDto, UpdatePunetoretDto> {
    public PunetoretRepository() {
        super("punetoret");
    }

    @Override
    public Punetoret fromResultSet(ResultSet result) throws SQLException {
        return Punetoret.getInstance(result);
    }

    @Override
    public Punetoret create(CreatePunetoretDto dto) {
        String query = """
            INSERT INTO PUNETORET (emri, mbiemri, pozita, telefoni, email, paga, dataPunesimit)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, dto.getEmri());
            pstm.setString(2, dto.getMbiemri());
            pstm.setString(3, dto.getPozita());
            pstm.setString(4, dto.getTelefoni());
            pstm.setString(5, dto.getEmail());
            pstm.setDouble(6, dto.getPaga());
            pstm.setString(7, dto.getDataPunesimit());
            pstm.execute();

            ResultSet rs = pstm.getGeneratedKeys();
            if (rs.next()) {
                return this.getById(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Punetoret update(UpdatePunetoretDto dto) {
        StringBuilder query = new StringBuilder("UPDATE PUNETORET SET ");
        ArrayList<Object> params = new ArrayList<>();

        if (dto.getEmail() != null) {
            query.append("EMAIL = ?, ");
            params.add(dto.getEmail());
        }

        if (dto.getPozita() != null) {
            query.append("POZITA = ?, ");
            params.add(dto.getPozita());
        }

        if (dto.getPaga() != 0) {
            query.append("PAGA = ?, ");
            params.add(dto.getPaga());
        }

        if (params.isEmpty()) {
            return getById(dto.getId());
        }


        query.setLength(query.length() - 2);
        query.append(" WHERE PUNETOR_ID = ?");
        params.add(dto.getId());

        try {
            PreparedStatement pstm = this.connection.prepareStatement(query.toString());
            for (int i = 0; i < params.size(); i++) {
                pstm.setObject(i + 1, params.get(i));
            }
            int updated = pstm.executeUpdate();
            if (updated == 1) {
                return getById(dto.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
