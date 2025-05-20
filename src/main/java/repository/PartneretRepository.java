package repository;

import models.dto.Partneret.Partneret;
import models.dto.Partneret.CreatePartneretDto;
import models.dto.Partneret.UpdatePartneretDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PartneretRepository extends BaseRepository<Partneret, CreatePartneretDto, UpdatePartneretDto> {

    public PartneretRepository() {
        super("partneret");
    }

    @Override
    public Partneret fromResultSet(ResultSet result) throws SQLException {
        return Partneret.getInstance(result);
    }

    @Override
    public Partneret create(CreatePartneretDto dto) {
        String query = """
                INSERT INTO partneret(emri_kompanise, lloji_partnerit, person_kontakti, email, telefoni, adresa)
                VALUES (?, ?, ?, ?, ?, ?)
                """;
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, dto.getEmriKompanise());
            stmt.setString(2, dto.getLlojiPartnerit());
            stmt.setString(3, dto.getPersonKontakti());
            stmt.setString(4, dto.getEmail());
            stmt.setString(5, dto.getTelefoni());
            stmt.setString(6, dto.getAdresa());
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
    public Partneret update(UpdatePartneretDto dto) {
        StringBuilder query = new StringBuilder("UPDATE partneret SET ");
        ArrayList<Object> params = new ArrayList<>();

        if (dto.getEmail() != null) {
            query.append("email = ?, ");
            params.add(dto.getEmail());
        }
        if (dto.getTelefoni() != null) {
            query.append("telefoni = ?, ");
            params.add(dto.getTelefoni());
        }
        if (dto.getAdresa() != null) {
            query.append("adresa = ?, ");
            params.add(dto.getAdresa());
        }
        if (dto.getPersonKontakti() != null) {
            query.append("person_kontakti = ?, ");
            params.add(dto.getPersonKontakti());
        }

        if (params.isEmpty()) {
            return getById(dto.getId());
        }

        // Hiq presjen e fundit dhe shto pjesën WHERE
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


    public List<Partneret> searchByCompanyName(String name) {
        String where = "LOWER(emri_kompanise) LIKE ?";
        return searchWithCustomWhere(where, "%" + name.toLowerCase() + "%");
    }

    public boolean existsByEmail(String email) {
        String query = "SELECT COUNT(*) FROM partneret WHERE email = ?";
        try (PreparedStatement stmt = this.connection.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean existsByEmailExceptId(String email, int id) {
        String query = "SELECT COUNT(*) FROM partneret WHERE email = ? AND id != ?";
        try (PreparedStatement stmt = this.connection.prepareStatement(query)) {
            stmt.setString(1, email);
            stmt.setInt(2, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean existsByPhoneNumber(String telefoni) {
        String query = "SELECT COUNT(*) FROM partneret WHERE telefoni = ?";
        try (PreparedStatement stmt = this.connection.prepareStatement(query)) {
            stmt.setString(1, telefoni);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean existsByPhoneNumberExceptId(String telefoni, int id) {
        String query = "SELECT COUNT(*) FROM partneret WHERE telefoni = ? AND id != ?";
        try (PreparedStatement stmt = this.connection.prepareStatement(query)) {
            stmt.setString(1, telefoni);
            stmt.setInt(2, id);
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
