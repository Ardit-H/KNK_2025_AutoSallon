package repository;

import models.dto.Riparimet.CreateRiparimetDto;
import models.dto.Riparimet.Riparimet;
import models.dto.Riparimet.UpdateRiparimetDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RiparimetRepository extends BaseRepository<Riparimet, CreateRiparimetDto, UpdateRiparimetDto> {

    public RiparimetRepository() {
        super("riparimet");
    }

    @Override
    public Riparimet fromResultSet(ResultSet result) throws SQLException {
        return Riparimet.getInstance(result);
    }

    @Override
    public Riparimet create(CreateRiparimetDto dto) {
        String query = """
            INSERT INTO riparimet (veturaId, sherbimiId, statusi, kostoRiparimit, dataRiparimit)
            VALUES (?, ?, ?, ?, ?)
        """;

        try {
            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setInt(1, dto.getVeturaId());
            pstm.setInt(2, dto.getSherbimiId());
            pstm.setString(3, dto.getStatusi());
            pstm.setDouble(4, dto.getKostoRiparimit());
            pstm.executeUpdate();

            ResultSet rs = pstm.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                return this.getById(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Riparimet update(UpdateRiparimetDto dto) {
        StringBuilder query = new StringBuilder("UPDATE riparimet SET ");
        ArrayList<Object> params = new ArrayList<>();

        if (dto.getStatusi() != null) {
            query.append("statusi = ?, ");
            params.add(dto.getStatusi());
        }
        if (dto.getKostoRiparimit() != 0.0) {
            query.append("kostoRiparimit = ?, ");
            params.add(dto.getKostoRiparimit());
        }

        if (params.isEmpty()) return getById(dto.getId());

        query.setLength(query.length() - 2);
        query.append(" WHERE id = ?");
        params.add(dto.getId());

        try {
            PreparedStatement pstm = this.connection.prepareStatement(query.toString());
            for (int i = 0; i < params.size(); i++) {
                pstm.setObject(i + 1, params.get(i));
            }

            int updated = pstm.executeUpdate();
            if (updated == 1) {
                return this.getById(dto.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Riparimet> searchByStatus(String status) {
        String where = "LOWER(statusi) LIKE ?";
        String searchValue = "%" + status.toLowerCase() + "%";
        return searchWithCustomWhere(where, searchValue);
    }
}
