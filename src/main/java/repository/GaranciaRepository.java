package repository;

import Database.DBConnector;
import models.dto.Garancia.Garancia;
import models.dto.Garancia.CreateGaranciaDto;
import models.dto.Garancia.UpdateGaranciaDto;
import java.sql.Statement;
import java.sql.*;
import java.util.ArrayList;

public class GaranciaRepository extends BaseRepository<Garancia, CreateGaranciaDto, UpdateGaranciaDto>{
    public GaranciaRepository(){
        super("Garancia");
    }

    public Garancia fromResultSet(ResultSet result) throws SQLException{
        return Garancia.getInstance(result);
    }

    public Garancia create(CreateGaranciaDto garanciaDto){
        String query ="""
                INSERT INTO GARANCIA(vid,kid,llojiGarancise, dataFillimit, dataMbarimit)
                 VALUES(?,?,?,?,?)
                """;
        try{
            PreparedStatement pstm=this.connection.prepareStatement(
                    query,Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1,garanciaDto.getLlojiGarancise());
            pstm.setString(2, garanciaDto.getDataFillimit());
            pstm.setString(3, garanciaDto.getDataMbarimit());

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

    public Garancia update(UpdateGaranciaDto garanciaDto){
        StringBuilder query=new StringBuilder("UPDATE GARANCIA SET ");
        ArrayList<Object> params=new ArrayList<>();

        if(garanciaDto.getDataFillimit() != null){
            query.append("DATAFILLMIT = ?, ");
            params.add(garanciaDto.getDataFillimit());
        }
        if(garanciaDto.getDataMbarimit() != null){
            query.append("DATAMBARIMIT = ?, ");
            params.add(garanciaDto.getDataMbarimit());
        }
        if(params.isEmpty()){
            return getById(garanciaDto.getId());
        }

        query.setLength(query.length() - 2);
        query.append(" WHERE ID = ?");
        params.add(garanciaDto.getId());

        try{
            PreparedStatement pstm=this.connection.prepareStatement(query.toString());
            for(int i = 0; i < params.size(); i++){
                pstm.setObject(i + 1, params.get(i));
            }
            int updated=pstm.executeUpdate();
            if(updated == 1) {
                return this.getById(garanciaDto.getId());
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
