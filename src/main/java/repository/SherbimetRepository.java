package repository;

import models.dto.Klientet.CreateKlientetDto;
import models.dto.Klientet.Klientet;
import models.dto.Klientet.UpdateKlientiDto;
import models.dto.Sherbimet.CreateSherbimetDto;
import models.dto.Sherbimet.Sherbimet;
import models.dto.Sherbimet.UpdateSherbimetDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SherbimetRepository extends BaseRepository<Sherbimet, CreateSherbimetDto, UpdateSherbimetDto> {
      public SherbimetRepository(){
          super("sherbimet");
      }
      public Sherbimet fromResultSet(ResultSet result) throws SQLException {
        return Sherbimet.getInstance(result);
      }
      public Sherbimet create(CreateSherbimetDto sherbimetDto){
        String query ="""
                INSERT INTO SHERBIMET(emri,pershkrimi,çmimi)
                 VALUES(?,?,?)
                """;
        try{
            PreparedStatement pstm=this.connection.prepareStatement(
                    query, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1,sherbimetDto.getEmri());
            pstm.setString(2,sherbimetDto.getPershkrimi());
            pstm.setDouble(3,sherbimetDto.getÇmimi());
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
    public Sherbimet update(UpdateSherbimetDto sherbimetDto){
        StringBuilder query=new StringBuilder("UPDATE SHERBIMET SET ");
        ArrayList<Object> params=new ArrayList<>();

        if(sherbimetDto.getEmri() != null){
            query.append("EMRI = ?, ");
            params.add(sherbimetDto.getEmri());
        }
        if(sherbimetDto.getPershkrimi() != null){
            query.append("PERSHKRIMI = ?, ");
            params.add(sherbimetDto.getPershkrimi());
        }
        if(sherbimetDto.getÇmimi() != null){
            query.append("çmimi = ?, ");
            params.add(sherbimetDto.getÇmimi());
        }
        if(params.isEmpty()){
            return getById(sherbimetDto.getId());
        }

        query.setLength(query.length() - 2);
        query.append(" WHERE ID = ?");
        params.add(sherbimetDto.getId());

        try{
            PreparedStatement pstm=this.connection.prepareStatement(query.toString());
            for(int i = 0; i < params.size(); i++){
                pstm.setObject(i + 1, params.get(i));
            }
            int updated=pstm.executeUpdate();
            if(updated == 1) {
                return this.getById(sherbimetDto.getId());
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
