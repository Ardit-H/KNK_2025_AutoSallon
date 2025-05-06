package repository;

import models.dto.Faturat.CreateFaturatDto;
import models.dto.Faturat.Faturat;
import models.dto.Faturat.UpdateFaturatDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FaturatRepository extends BaseRepository<Faturat, CreateFaturatDto, UpdateFaturatDto> {

    public FaturatRepository() {
        super("faturat");
    }

    @Override
    public Faturat fromResultSet(ResultSet result) throws SQLException {
        return Faturat.getInstance(result);
    }

    @Override
    public Faturat create(CreateFaturatDto dto) {
        String query = """
            INSERT INTO faturat (shitje_id, dataFatures, shumaTotale, llojiPageses)
            VALUES (?, ?, ?, ?)
        """;
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, dto.getShitjeId());
            stmt.setDate(2, Date.valueOf(dto.getDataFatures()));
            stmt.setDouble(3, dto.getShumaTotale());
            stmt.setString(4, dto.getLlojiPageses());
            stmt.execute();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return getById(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Faturat update(UpdateFaturatDto dto) {
        StringBuilder query = new StringBuilder("UPDATE faturat SET ");
        ArrayList<Object> params = new ArrayList<>();

        if (dto.getShumaTotale() != null) {
            query.append("shumaTotale = ?, ");
            params.add(dto.getShumaTotale());
        }

        if (dto.getLlojiPageses() != null) {
            query.append("llojiPageses = ?, ");
            params.add(dto.getLlojiPageses());
        }

        if (params.isEmpty()) {
            return getById(dto.getId());
        }

        query.setLength(query.length() - 2);
        query.append(" WHERE id = ?");
        params.add(dto.getId());

        try {
            PreparedStatement stmt = this.connection.prepareStatement(query.toString());
            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }
            int updated = stmt.executeUpdate();
            if (updated == 1) {
                return getById(dto.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Faturat> getByShitjeId(int shitjeId) {
        String where = "shitje_id = ?";
        List<Object> params = List.of(shitjeId);
        return searchWithCustomWhere(where, params.toArray());
    }

    public boolean existsForShitjeId(int shitjeId) {
        String query = "SELECT COUNT(*) FROM faturat WHERE shitje_id = ?";
        try (PreparedStatement stmt = this.connection.prepareStatement(query)) {
            stmt.setInt(1, shitjeId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

