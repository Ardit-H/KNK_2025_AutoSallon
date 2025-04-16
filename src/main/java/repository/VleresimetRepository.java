package repository;

import models.dto.Klientet.Klientet;
import models.dto.Klientet.UpdateKlientiDto;
import models.dto.Vleresimet.CreateVleresimetDto;
import models.dto.Vleresimet.UpdateVleresimetDto;
import models.dto.Vleresimet.Vleresimet;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
                VLERESIMET (KLIENTI_ID,VETURA_ID,VLERESIMI,KOMENTI)
                VALUES (?, ?, ?, ?)
                """;
        try{
            PreparedStatement pstm =
                    this.connection.prepareStatement(
                            query, Statement.RETURN_GENERATED_KEYS);
            pstm.setInt(1,vleresimetDto.getKlientiId());
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

        if(vleresimetDto.getKlientiId() != null){
            query.append("KLIENTI_ID = ?, ");
            params.add(vleresimetDto.getKlientiId());
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
}


