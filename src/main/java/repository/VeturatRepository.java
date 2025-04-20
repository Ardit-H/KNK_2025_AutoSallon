package repository;

import Database.DBConnector;
import models.dto.Veturat.Veturat;
import models.dto.Veturat.CreateVeturatDto;
import models.dto.Veturat.UpdateVeturatDto;

import java.sql.*;
import java.util.ArrayList;

public class VeturatRepository extends BaseRepository<Veturat, CreateVeturatDto, UpdateVeturatDto>{
    public VeturatRepository(){
        super("veturat");
    }

    public Veturat fromResultSet(ResultSet result) throws SQLException{
        return Veturat.getInstance(result);
    }

    public Veturat create(CreateVeturatDto veturatDto){
        String query ="""
                INSERT INTO VETURAT(prodhuesi,modeli,vitiProdhimit,ngjyra,cmimi,gjendja,kilometrazha,tipiKarburant)
                 VALUES(?,?,?,?,?)
                """;
        try{
            PreparedStatement pstm=this.connection.prepareStatement(
                    query,Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1,veturatDto.getProdhuesi());
            pstm.setString(1,veturatDto.getModeli());
            pstm.setInt(2,veturatDto.getVitiProdhimit());
            pstm.setString(3,veturatDto.getNgjyra());
            pstm.setDouble(4,veturatDto.getCmimi());
            pstm.setString(5,veturatDto.getGjendja());
            pstm.setInt(2,veturatDto.getKilometrazha());
            pstm.setString(5,veturatDto.getTipiKarburant());

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

    public Veturat update(UpdateVeturatDto veturatDto){
        StringBuilder query=new StringBuilder("UPDATE VETURAT SET ");
        ArrayList<Object> params=new ArrayList<>();

        if(veturatDto.getGjendja() != null){
            query.append("CMIMI = ?, ");
            params.add(veturatDto.getGjendja());
        }


        if(params.isEmpty()){
            return getById(veturatDto.getId());
        }

        query.setLength(query.length() - 2);
        query.append(" WHERE ID = ?");
        params.add(veturatDto.getId());

        try{
            PreparedStatement pstm=this.connection.prepareStatement(query.toString());
            for(int i = 0; i < params.size(); i++){
                pstm.setObject(i + 1, params.get(i));
            }
            int updated=pstm.executeUpdate();
            if(updated == 1) {
                return this.getById(veturatDto.getId());
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
