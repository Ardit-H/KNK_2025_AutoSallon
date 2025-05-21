package repository;

import Database.DBConnector;
import models.dto.Perdoruesit.Perdoruesit;
import models.dto.Perdoruesit.CreatePerdoruesitDto;
import models.dto.Perdoruesit.UpdatePerdoruesitDto;

import java.sql.*;
import java.util.ArrayList;
public class PerdoruesitRepository extends BaseRepository<Perdoruesit, CreatePerdoruesitDto, UpdatePerdoruesitDto> {
    public PerdoruesitRepository(){
        super("perdoruesi");
    }

    public Perdoruesit fromResultSet(ResultSet result) throws SQLException{
        return Perdoruesit.getInstance(result);

    }

    public Perdoruesit create(CreatePerdoruesitDto perdoruesitDto){
        String query = """
                INSERT INTO PERDORUESI (emri, email, fjalekalimi)
                VALUES (?,?,?)""";
        try{
            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, perdoruesitDto.getEmri());
            pstm.setString(2, perdoruesitDto.getEmail());
            pstm.setString(3, perdoruesitDto.getFjalekalimi());
            pstm.execute();
            ResultSet resultSet = pstm.getGeneratedKeys();
            if(resultSet.next()){
                int id = resultSet.getInt(1);
                return this.getById(id);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public Perdoruesit create(String emri, String mbiemri, String email, String passwordHash, String salt, String nrtelefonit, String adresa) {
        String sql = "INSERT INTO perdoruesi (emri, mbiemri, email, nrtelefonit, adresa, roli, passwordHash, salt) VALUES (?, ?, ?, ?, ?, ?, ?, ?) RETURNING *";


        try (
             PreparedStatement stmt = this.connection.prepareStatement(sql)) {

            stmt.setString(1, emri);
            stmt.setString(2, mbiemri);
            stmt.setString(3, email);
            stmt.setString(4, nrtelefonit);
            stmt.setString(5, adresa);
            stmt.setString(6, "user");
            stmt.setString(7, passwordHash);
            stmt.setString(8, salt);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Perdoruesit.getInstance(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Nuk u krijua përdoruesi", e);
        }

        return null;
    }
    public Perdoruesit update(UpdatePerdoruesitDto perdoruesitDto){
        StringBuilder query = new StringBuilder("UPDATE PERDORUESI SET ");
        ArrayList<Object> params = new ArrayList<>();

        if(perdoruesitDto.getEmail() != null){
            query.append("EMAIL = ?, ");
            params.add(perdoruesitDto.getEmail());
        }

        if(perdoruesitDto.getPasswordhash() != null){
            query.append("PASSWORDHASH = ?, ");
            params.add(perdoruesitDto.getPasswordhash());
        }
        if(perdoruesitDto.getSalt() != null){
            query.append("SALT = ?, ");
            params.add(perdoruesitDto.getSalt());
        }
        if(perdoruesitDto.getRoli() != null){
            query.append("ROLI = ?, ");
            params.add(perdoruesitDto.getRoli());
        }
        if (perdoruesitDto.getNrtelefonit() != null) {
            query.append("NRTELEFONIT = ?, ");
            params.add(perdoruesitDto.getNrtelefonit());
        }
        if (perdoruesitDto.getAdresa() != null) {
            query.append("ADRESA = ?, ");
            params.add(perdoruesitDto.getAdresa());
        }

        if(params.isEmpty()){
            return getById(perdoruesitDto.getId());
        }

        query.setLength(query.length() - 2);
        query.append(" WHERE ID = ?");
        params.add(perdoruesitDto.getId());


        try{
            PreparedStatement pstm = this.connection.prepareStatement(query.toString());
            for(int i =0; i<params.size(); i++){
                pstm.setObject(i+1, params.get(i));
            }
            int updated = pstm.executeUpdate();
            if(updated ==1){
                return this.getById(perdoruesitDto.getId());
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public Perdoruesit getByEmail(String email){
        String sql = "SELECT * FROM perdoruesi WHERE email = ?";

        try  {
            PreparedStatement pstm = this.connection.prepareStatement(sql);
            pstm.setString(1, email);

            try (ResultSet rs=pstm.executeQuery()){
                if (rs.next()){
                    return Perdoruesit.getInstance(rs);
                }
            }

        } catch (SQLException e){
            throw new RuntimeException("Gabim gjatë kërkimit të përdoruesit", e);
        }

        return null;
    }
    public Perdoruesit  findById(Integer perdoruesiId){
        String sql = "SELECT * FROM perdoruesi WHERE id = ?";
        try  {
            PreparedStatement pstm = this.connection.prepareStatement(sql);
            pstm.setInt(1, perdoruesiId);
            try (ResultSet rs=pstm.executeQuery()){
                if (rs.next()){
                    return Perdoruesit.getInstance(rs);
                }
            }
        } catch (SQLException e){
            throw new RuntimeException("Gabim gjatë kërkimit të përdoruesit", e);
        }
        return null;
    }
    public int getTotalPerdoruesit() throws SQLException {
        String query = "SELECT COUNT(*) FROM perdoruesi";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            return rs.next() ? rs.getInt(1) : 0;
        }
    }
}
