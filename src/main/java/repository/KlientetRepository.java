package repository;

import Database.DBConnector;
import models.dto.Klientet.Klientet;
import models.dto.Klientet.CreateKlientetDto;
import models.dto.Klientet.UpdateKlientiDto;

import java.sql.*;
import java.util.ArrayList;

public class KlientetRepository extends BaseRepository<Klientet, CreateKlientetDto, UpdateKlientiDto>{
    public KlientetRepository(){
        super("users");
    }

    public Klientet fromResultSet(ResultSet result) throws SQLException{
        return Klientet.getInstance(result);
    }

    public Klientet create(CreateKlientetDto klientetDto){
        String query = """
                INSERT INTO 
                USERS (NAME, EMAIL, AGE)
                VALUES (?, ?, ?)
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
        String query = """
                UPDATE USERS 
                SET EMAIL = ?
                WHERE ID = ?
                """;
        try{
            PreparedStatement pstm=this.connection.prepareStatement(query);
            pstm.setString(1, klientetDto.getEmail());
            pstm.setInt(2,klientetDto.getId());
            int updateRecords=pstm.executeUpdate();
            if(updateRecords==1){
                return this.getById(klientetDto.getId());
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

}