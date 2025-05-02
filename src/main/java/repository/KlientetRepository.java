package repository;

import Database.DBConnector;
import models.dto.Klientet.Klientet;
import models.dto.Klientet.CreateKlientetDto;
import models.dto.Klientet.UpdateKlientiDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KlientetRepository extends BaseRepository<Klientet, CreateKlientetDto, UpdateKlientiDto>{
    public KlientetRepository(){
        super("klientet");
    }

    public Klientet fromResultSet(ResultSet result) throws SQLException{
        return Klientet.getInstance(result);
    }

    public Klientet create(CreateKlientetDto klientetDto){
        String query ="""
                INSERT INTO KLIENTET(emri,mbiemri,email,nrtelefonit,adresa)
                 VALUES(?,?,?,?,?)
                """;
        try{
            PreparedStatement pstm=this.connection.prepareStatement(
                    query,Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1,klientetDto.getEmri());
            pstm.setString(2,klientetDto.getMbiemri());
            pstm.setString(3,klientetDto.getEmail());
            pstm.setString(4,klientetDto.getNrtelefonit());
            pstm.setString(5,klientetDto.getAdresa());
            pstm.execute();
            ResultSet resultSet=pstm.getGeneratedKeys();
            if(resultSet.next()){
                int id=resultSet.getInt(1);
                return this.getById(id);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public Klientet update(UpdateKlientiDto klientetDto){
        StringBuilder query=new StringBuilder("UPDATE KLIENTET SET ");
        ArrayList<Object> params=new ArrayList<>();

        if(klientetDto.getEmail() != null){
            query.append("EMAIL = ?, ");
            params.add(klientetDto.getEmail());
        }
        if(klientetDto.getNrtelefonit() != null){
            query.append("NRTELEFONIT = ?, ");
            params.add(klientetDto.getNrtelefonit());
        }
        if(klientetDto.getAdresa() != null){
            query.append("ADRESA = ?, ");
            params.add(klientetDto.getAdresa());
        }
        if(params.isEmpty()){
            return getById(klientetDto.getId());
        }

        query.setLength(query.length() - 2);
        query.append(" WHERE ID = ?");
        params.add(klientetDto.getId());

        try{
            PreparedStatement pstm=this.connection.prepareStatement(query.toString());
            for(int i = 0; i < params.size(); i++){
                pstm.setObject(i + 1, params.get(i));
            }
            int updated=pstm.executeUpdate();
            if(updated == 1) {
                return this.getById(klientetDto.getId());
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Klientet> searchByFullName(String fullName) {
        String sql = "SELECT * FROM klientet WHERE LOWER(CONCAT(emri, ' ', mbiemri)) LIKE ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + fullName.toLowerCase() + "%");
            ResultSet rs = stmt.executeQuery();
            List<Klientet> result = new ArrayList<>();
            while (rs.next()) {
                result.add(Klientet.getInstance(rs));
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}