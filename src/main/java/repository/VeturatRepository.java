package repository;

import Database.DBConnector;
import models.Veturat;
import models.dto.Veturat.CreateVeturatDto;
import models.dto.Veturat.UpdateVeturatDto;

import java.sql.*;
import java.util.ArrayList;

public class VeturatRepository {
    private Connection connection;
    public VeturatRepository(){
        this.connection = DBConnector.getConnection();
    }
    public ArrayList<Veturat>getVeturat(){
        ArrayList<Veturat>veturat = new ArrayList<>();
        String query = "SELECT * FORM VETURAT";
        try{
            Statement statement=this.connection.createStatement();
            ResultSet resultSet= statement.executeQuery(query);
            while(resultSet.next()){
                veturat.add(Veturat.getInstance(resultSet));
            }
        }catch (SQLException e){
          e.printStackTrace();
        }
        return veturat;
    }

    public Veturat getById(int id){
        String query="SELECT * FROM VETURAT WHERE KID = ?";
        try{
            PreparedStatement statement=this.connection.prepareStatement(query);
            statement.setInt(1,id);
            ResultSet result= statement.executeQuery();
            if(result.next()){
                return Veturat.getInstance(result);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public Veturat create (CreateVeturatDto veturatDto){
        String query = """
                INSERT INTO VETURAT(prodhuesi, modeli, vitiprodhimit, ngjyra, cmimi, gjendja, kilometrazha, tipikarburant)
                VALUES (?,?,?,?,?,?,?,?)
                """ ;
               try {
                   PreparedStatement pstm=this.connection.prepareStatement(
                           query,Statement.RETURN_GENERATED_KEYS);
                   pstm.setString(1, veturatDto.getProdhuesi());
                   pstm.setString(2, veturatDto.getModeli());
                   pstm.setInt(3, veturatDto.getVitiprodhimit());
                   pstm.setString(4, veturatDto.getNgjyra());
                   pstm.setDouble(5,veturatDto.getCmimi());
                   pstm.setString(6, veturatDto.getGjendja());
                   pstm.setInt(7, veturatDto.getKilometrazha());
                   pstm.setString(8, veturatDto.getTipikarburant());
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

    public Veturat updateNgjyra(UpdateVeturatDto veturatDto){
        String query= """
                UPDATE VETURAT 
                SET NGJYRA=?
                WHERE KID=?
                """;
        try{
            PreparedStatement pstm=this.connection.prepareStatement(query);
            pstm.setString(1, veturatDto.getNgjyra());
            pstm.setInt(2,veturatDto.getVeturaid());
            int updateRecords=pstm.executeUpdate();
            if(updateRecords==1){
                return this.getById(veturatDto.getVeturaid());
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean delete(int id){
        String query= """
                DELETE FROM VETURAT 
                WHERE KID=?
                """;
        try{
            PreparedStatement pstm=this.connection.prepareStatement(query);
            pstm.setInt(1,id);
            return pstm.executeUpdate()==1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
