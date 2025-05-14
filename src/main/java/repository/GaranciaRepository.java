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
        super("garancia");
    }

    public Garancia fromResultSet(ResultSet result) throws SQLException{
        return Garancia.getInstance(result);
    }

    public Garancia create(CreateGaranciaDto garanciaDto){
        String query ="""
                INSERT INTO GARANCIA(vid,kid,lloji_garancise,data_fillimit,data_mbarimit)
                 VALUES(?,?,?,?,?)
                """;
        try{
            PreparedStatement pstm=this.connection.prepareStatement(
                    query,Statement.RETURN_GENERATED_KEYS);
            pstm.setInt(1,garanciaDto.getVid());
            pstm.setInt(2, garanciaDto.getKid());
            pstm.setString(3,garanciaDto.getLlojiGarancise());
            pstm.setDate(4, java.sql.Date.valueOf(garanciaDto.getDataFillimit()));
            pstm.setDate(5, java.sql.Date.valueOf(garanciaDto.getDataMbarimit()));
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
        StringBuilder query = new StringBuilder("UPDATE GARANCIA SET ");
        ArrayList<Object> params = new ArrayList<>();

        if (garanciaDto.getLlojiGarancise() != null) {
            query.append("LLOJI_GARANCISE = ?, ");
            params.add(garanciaDto.getLlojiGarancise());
        }
        if (garanciaDto.getDataFillimit() != null) {
            query.append("DATA_FILLIMIT = ?, ");
            params.add(java.sql.Date.valueOf(garanciaDto.getDataFillimit()));
        }
        if (garanciaDto.getDataMbarimit() != null) {
            query.append("DATA_MBARIMIT = ?, ");
            params.add(java.sql.Date.valueOf(garanciaDto.getDataMbarimit()));
        }

        if (params.isEmpty()) {
            return getById(garanciaDto.getId());
        }

        query.setLength(query.length() - 2);
        query.append(" WHERE ID = ?");
        params.add(garanciaDto.getId());

        try {
            PreparedStatement pstm = this.connection.prepareStatement(query.toString());
            for (int i = 0; i < params.size(); i++) {
                pstm.setObject(i + 1, params.get(i));
            }
            int updated = pstm.executeUpdate();
            if (updated == 1) {
                return this.getById(garanciaDto.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
