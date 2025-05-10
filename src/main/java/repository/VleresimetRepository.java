package repository;

import Database.DBConnector;
import models.dto.Klientet.Klientet;
import models.dto.Klientet.UpdateKlientiDto;
import models.dto.Vleresimet.CreateVleresimetDto;
import models.dto.Vleresimet.UpdateVleresimetDto;
import models.dto.Vleresimet.Vleresimet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VleresimetRepository extends BaseRepository<Vleresimet, CreateVleresimetDto, UpdateVleresimetDto> {
     public VleresimetRepository(){
         super("vleresimet");
     }
    public Vleresimet fromResultSet(ResultSet result) throws SQLException {
        return Vleresimet.getInstance(result);
    }
    public Vleresimet create(CreateVleresimetDto vleresimetDto){
        String query = """
                INSERT INTO 
                VLERESIMET (PERDORUESI_ID,VETURA_ID,VLERESIMI,KOMENTI)
                VALUES (?, ?, ?, ?)
                """;
        try{
            PreparedStatement pstm =
                    this.connection.prepareStatement(
                            query, Statement.RETURN_GENERATED_KEYS);
            pstm.setInt(1,vleresimetDto.getPerdoruesiId());
            pstm.setInt(2,vleresimetDto.getVeturaId());
            pstm.setInt(3, vleresimetDto.getVleresimi());
            pstm.setString(4, vleresimetDto.getKomenti());
            pstm.execute();
            ResultSet res = pstm.getGeneratedKeys();
            if(res.next()){
                int id = res.getInt(1);
                return this.getById(id);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public Vleresimet update(UpdateVleresimetDto vleresimetDto){
        StringBuilder query=new StringBuilder("UPDATE VLERESIMET SET ");
        ArrayList<Object> params=new ArrayList<>();

        if(vleresimetDto.getPerdoruesiId() != null){
            query.append("PERDORUESI_ID = ?, ");
            params.add(vleresimetDto.getPerdoruesiId());
        }
        if(vleresimetDto.getVeturaId() != null){
            query.append("VETURA_ID = ?, ");
            params.add(vleresimetDto.getVeturaId());
        }

        if(vleresimetDto.getVleresimi() != null){
            query.append("VLERESIMI = ?, ");
            params.add(vleresimetDto.getVleresimi());
        }
        if(vleresimetDto.getKomenti() != null){
            query.append("KOMENTI = ?, ");
            params.add(vleresimetDto.getKomenti());
        }
        if(params.isEmpty()){
            return getById(vleresimetDto.getVleresimiId());
        }

        query.setLength(query.length() - 2);
        query.append(" WHERE ID = ?");
        params.add(vleresimetDto.getVleresimiId());

        try{
            PreparedStatement pstm=this.connection.prepareStatement(query.toString());
            for(int i = 0; i < params.size(); i++){
                pstm.setObject(i + 1, params.get(i));
            }
            int updated=pstm.executeUpdate();
            if(updated == 1) {
                return this.getById(vleresimetDto.getVleresimiId());
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Vleresimet> getByUserId(int userId){
        List<Vleresimet> vleresimetList=new ArrayList<>();
        String sql = """
        SELECT v.*, ve.prodhuesi, ve.modeli
        FROM Vleresimet v
        JOIN Veturat ve ON v.vetura_id = ve.id
        WHERE v.perdoruesi_id = ?
    """;
        try(
            PreparedStatement stmt=this.connection.prepareStatement(sql)){
            stmt.setInt(1, userId);
            ResultSet rs=stmt.executeQuery();
            while (rs.next()){
                Vleresimet v=Vleresimet.getInstance(rs); // kjo e mbush pjesën e zakonshme
                String veturaEmri=rs.getString("prodhuesi") + " " + rs.getString("modeli");
                v.setVeturaEmri(veturaEmri);
                vleresimetList.add(v);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return vleresimetList;
    }
    public List<Vleresimet> getAllWithJoins(){
        List<Vleresimet> vleresimetList = new ArrayList<>();
        String sql = """
        SELECT v.*, ve.prodhuesi, ve.modeli, p.emri, p.mbiemri
        FROM Vleresimet v
        JOIN Veturat ve ON v.vetura_id = ve.id
        JOIN Perdoruesi p ON v.perdoruesi_id = p.id
    """;
        try (
             PreparedStatement stmt = this.connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Vleresimet v = Vleresimet.getInstance(rs);
                v.setVeturaEmri(rs.getString("prodhuesi") + " " + rs.getString("modeli"));
                v.setPerdoruesiEmriPlote(rs.getString("emri") + " " + rs.getString("mbiemri"));
                vleresimetList.add(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vleresimetList;
    }

}


