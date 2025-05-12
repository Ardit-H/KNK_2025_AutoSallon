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
                INSERT INTO KLIENTET(emri,mbiemri,email,nrtelefonit,adresa, perdoruesi_id)
                 VALUES(?,?,?,?,?,?)
                """;
        try{
            PreparedStatement pstm=this.connection.prepareStatement(
                    query,Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1,klientetDto.getEmri());
            pstm.setString(2,klientetDto.getMbiemri());
            pstm.setString(3,klientetDto.getEmail());
            pstm.setString(4,klientetDto.getNrtelefonit());
            pstm.setString(5,klientetDto.getAdresa());
            pstm.setObject(6, klientetDto.getPerdoruesiId(), Types.INTEGER);
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
        if(klientetDto.getPerdoruesiId()!=null){
            query.append("perdoruesi_id= ?, ");
            params.add(klientetDto.getPerdoruesiId());
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
        String where = "LOWER(CONCAT(emri, ' ', mbiemri)) LIKE ?";
        String searchValue = "%" + fullName.toLowerCase() + "%";
        return searchWithCustomWhere(where, searchValue);
    }
    public boolean existsByPhoneNumber(String nrTelefonit){
        String query="SELECT COUNT(*) FROM klientet WHERE nrtelefonit = ?";
        try(PreparedStatement stmt = this.connection.prepareStatement(query)){
            stmt.setString(1, nrTelefonit);
            ResultSet rs=stmt.executeQuery();
            if(rs.next()){
                return rs.getInt(1)>0;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public boolean existsByPhoneNumberExceptId(String nrTelefonit, int id){
        String query="SELECT COUNT(*) FROM klientet WHERE nrtelefonit = ? AND id != ?";
        try(PreparedStatement stmt = this.connection.prepareStatement(query)){
            stmt.setString(1, nrTelefonit);
            stmt.setInt(2, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return rs.getInt(1)>0;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public boolean existsByEmail(String email){
        String query = "SELECT COUNT(*) FROM klientet WHERE email = ?";
        try(PreparedStatement stmt=this.connection.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return rs.getInt(1) > 0;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public boolean existsByEmailExceptId(String email, int id){
        String query = "SELECT COUNT(*) FROM klientet WHERE email = ? AND id != ?";
        try(PreparedStatement stmt=this.connection.prepareStatement(query)) {
            stmt.setString(1, email);
            stmt.setInt(2, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return rs.getInt(1) > 0;
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public Klientet findByAllFields(String emri, String mbiemri, String email, String nrTelefonit, String adresa){
        String sql="SELECT * FROM klientet WHERE emri = ? AND mbiemri = ? AND email = ? AND nrtelefonit = ? AND adresa = ?";
        try (PreparedStatement stmt=this.connection.prepareStatement(sql)) {
            stmt.setString(1, emri);
            stmt.setString(2, mbiemri);
            stmt.setString(3, email);
            stmt.setString(4, nrTelefonit);
            stmt.setString(5, adresa);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                return Klientet.getInstance(rs);
            }
            return null;
        } catch (SQLException e){
            throw new RuntimeException("Gabim gjatë kërkimit të klientit: " + e.getMessage());
        }
    }

}