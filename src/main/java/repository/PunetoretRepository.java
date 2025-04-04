package repository;
import Database.DBConnector;
import models.Punetoret;
import models.dto.Punetoret.CreatePunetoretDto;
import models.dto.Punetoret.UpdatePunetoretEmailDto;
import java.sql.Statement;


import java.sql.*;
import java.util.ArrayList;
public class PunetoretRepository {
private Connection connection;
public PunetoretRepository(){
    this.connection = DBConnector.getConnection();
}
public ArrayList<Punetoret> getPunetoret(){
    ArrayList<Punetoret> punetoret = new ArrayList<>();
    String query = "SELECT * FROM PUNETORET";
    try{
        Statement statement = this.connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while(resultSet.next()){
            punetoret.add(Punetoret.getInstance(resultSet));
        }
    }catch (SQLException e){
        e.printStackTrace();
    }
    return punetoret;
}

public Punetoret getById(int id){
    String query = "SELECT * FROM PUNETORET WHERE PUNETOR_ID = ?";
    try{
        PreparedStatement statement = this.connection.prepareStatement(query);
        ResultSet result = statement.executeQuery();
        if(result.next()){
            return Punetoret.getInstance(result);
        }
    }catch (SQLException e){
        e.printStackTrace();
    }
    return null;
}

public Punetoret create(CreatePunetoretDto punetoretDto){
    String query = """
    INSERT INTO PUNETORET (emri, mbiemri, pozita, telefoni, email, paga, dataPunesimit )
    VALUES (?,?,?,?,?,?,?)""";
    try{
        PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        pstm.setString(1, punetoretDto.getEmri());
        pstm.setString(2, punetoretDto.getMbiemri());
        pstm.setString(3, punetoretDto.getPozita());
        pstm.setString(4, punetoretDto.getTelefoni());
        pstm.setString(5, punetoretDto.getEmail());
        pstm.setDouble(6, punetoretDto.getPaga());
        pstm.setString(7, punetoretDto.getDataPunesimit());
        pstm.execute();
        ResultSet resultSet = pstm.getGeneratedKeys();
        if(resultSet.next()){
            int id = resultSet.getInt(1);
            return this.getById(id);
        }
    }catch (SQLException e){
        e.printStackTrace();
    }
return null;
}

public Punetoret updateEmail(UpdatePunetoretEmailDto punetoretDto){
    String query = """
            UPDATE PUNETORET
            SET EMAIL = ?
            WHERE PUNETORETID = ?
            """;
    try{
        PreparedStatement pstm = this.connection.prepareStatement(query);
        pstm.setString(1, punetoretDto.getEmail());
        pstm.setInt(2, punetoretDto.getId());
        int updateRecords = pstm.executeUpdate();
        if(updateRecords==1){
            return this.getById(punetoretDto.getId());
        }

    }catch (SQLException e){
        e.printStackTrace();
    }
    return null;
}
public boolean delete(int id){
    String query = """
            DELETE FROM PUNETORET
            WHERE PUNETORETID = ?
            """;
    try{ PreparedStatement pstm = this.connection.prepareStatement(query);
        pstm.setInt(1, id);
        return pstm.executeUpdate()==1;
    } catch (SQLException e) {
        e.printStackTrace();
    }
   return false;
}

}
