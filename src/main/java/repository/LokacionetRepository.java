package repository;

import models.dto.Lokacionet.CreateLokacionetDto;
import models.dto.Lokacionet.Lokacionet;
import models.dto.Lokacionet.UpdateLokacionetDto;

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
                VALUES(?,?,?,?,?)
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

        if(lokacionetDto.getEmri() != null){
            query.append("EMRI = ?, ");
            params.add(lokacionetDto.getEmri());
        }
        if(lokacionetDto.getAdresa() != null){
            query.append("ADRESA = ?, ");
            params.add(lokacionetDto.getAdresa());
        }
        if(lokacionetDto.getQyteti() != null){
            query.append("Qyteti = ?, ");
            params.add(lokacionetDto.getQyteti());
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

}
