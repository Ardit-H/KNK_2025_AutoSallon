package repository;

import models.dto.Lokacionet.CreateLokacionetDto;
import models.dto.Lokacionet.Lokacionet;
import models.dto.Lokacionet.UpdateLokacionetDto;

import java.util.List;
import java.sql.*;
import java.util.ArrayList;

public class LokacionetRepository extends BaseRepository<Lokacionet, CreateLokacionetDto, UpdateLokacionetDto> {
    public LokacionetRepository(){super("lokacionet");}

    public Lokacionet fromResultSet(ResultSet rs) throws SQLException {
        return Lokacionet.getInstance(rs);
    }

    public Lokacionet create(CreateLokacionetDto lokacionetDto){
        String query = """
                INSERT INTO LOKACIONET(emri, adresa, qyteti, nrtelefonit)
                VALUES(?,?,?,?)
                """;
        try{
            PreparedStatement ps = this.connection.prepareStatement(
                    query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, lokacionetDto.getEmri_lokacionit());
            ps.setString(2, lokacionetDto.getAdresa());
            ps.setString(3, lokacionetDto.getQyteti());
            ps.setString(4, lokacionetDto.getNrtelefonit());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                int id = rs.getInt(1);
                return this.getById(id);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Lokacionet update(UpdateLokacionetDto lokacionetDto){
        StringBuilder query= new StringBuilder("UPDATE LOKACIONET SET ");
        ArrayList<Object> params = new ArrayList<>();


        if(lokacionetDto.getAdresa() != null){
            query.append("ADRESA = ?, ");
            params.add(lokacionetDto.getAdresa());
        }
        if(lokacionetDto.getQyteti() != null){
            query.append("Qyteti = ?, ");
            params.add(lokacionetDto.getQyteti());
        }
        if(lokacionetDto.getNrtelefonit() != null){
            query.append("nrtelefonit = ?, ");
            params.add(lokacionetDto.getNrtelefonit());
        }
        if(params.isEmpty()){
            return getById(lokacionetDto.getId());
        }

        query.setLength(query.length() - 2);
        query.append("WHERE ID = ?");
        params.add(lokacionetDto.getId());

        try{
            PreparedStatement pst = this.connection.prepareStatement(query.toString());
            for(int i = 0; i < params.size(); i++){
                pst.setObject(i + 1, params.get(i));
            }
            int updated = pst.executeUpdate();
            if(updated == 1){
                return this.getById(lokacionetDto.getId());
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public List<Lokacionet> searchByFullName(String fullName) {
        if (fullName == null || fullName.trim().isEmpty()) {
            return this.getAll();
        }
        String where = "LOWER(emri_lokacionit) LIKE ?";
        String searchValue = "%" + fullName.toLowerCase() + "%";
        return this.searchWithCustomWhere(where, searchValue);
    }
    public boolean existsByPhoneNumber(String nrTelefonit){
        String query = "SELECT COUNT(*) FROM lokacionet WHERE nrTelefonit = ?";
        try(PreparedStatement pstm = this.connection.prepareStatement(query)){
            pstm.setString(1, nrTelefonit);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                return rs.getInt(1)>0;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public boolean existsByPhoneNumberExceptId(String nrTelefonit, int id){
        String query = "SELECT COUNT(*) FROM lokacionet WHERE nrTelefonit = ? AND id != ?";
        try(PreparedStatement pstm = this.connection.prepareStatement(query)){
            pstm.setString(1, nrTelefonit);
            pstm.setInt(2, id);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                return rs.getInt(1)>0;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

}
