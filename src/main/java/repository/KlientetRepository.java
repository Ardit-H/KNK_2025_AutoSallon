package repository;

import Database.DBConnector;
import models.dto.Klientet.Klientet;
import models.dto.Klientet.CreateKlientetDto;
import models.dto.Klientet.UpdateKlientiDto;

import java.sql.*;
import java.util.ArrayList;

public class KlientetRepository {
    private Connection connection;
    public KlientetRepository(){
        this.connection= DBConnector.getConnection();
    }
    public ArrayList<Klientet> getKlientet() {
        ArrayList<Klientet> klientet = new ArrayList<>();
        String query = "SELECT * FROM KLIENTET";
        try {
            Statement statement=this.connection.createStatement();
            ResultSet resultSet= statement.executeQuery(query);
            while(resultSet.next()){
                klientet.add(Klientet.getInstance(resultSet));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return klientet;
    }
    public Klientet getById(int id){
        String query="SELECT * FROM KLIENTET WHERE KID = ?";
        try{
            PreparedStatement statement=this.connection.prepareStatement(query);
            statement.setInt(1,id);
            ResultSet result= statement.executeQuery();
            if(result.next()){
                return Klientet.getInstance(result);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public Klientet create(CreateKlientetDto klientetDto){
        String query ="""
                INSERT INTO KLIENTET(emri,mbiemri,email,nrtelefonit,adresa)
                 VALUES(?,?,?,?,?)
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
        StringBuilder query = new StringBuilder("UPDATE KLIENTET SET ");
        ArrayList<Object> params = new ArrayList<>();

        if (klientetDto.getEmail() != null) {
            query.append("EMAIL = ?, ");
            params.add(klientetDto.getEmail());
        }
        if (klientetDto.getNrtelefonit() != null) {
            query.append("NRTELEFONIT = ?, ");
            params.add(klientetDto.getNrtelefonit());
        }
        if (klientetDto.getAdresa() != null) {
            query.append("ADRESA = ?, ");
            params.add(klientetDto.getAdresa());
        }
        if (params.isEmpty()) {
            return getById(klientetDto.getId());
        }

        query.setLength(query.length() - 2);
        query.append(" WHERE KID = ?");
        params.add(klientetDto.getId());

        try {
            PreparedStatement pstm = this.connection.prepareStatement(query.toString());
            for (int i = 0; i < params.size(); i++) {
                pstm.setObject(i + 1, params.get(i));
            }
            int updated = pstm.executeUpdate();
            if (updated == 1) {
                return this.getById(klientetDto.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
   /*
    public Klientet updateEmail(UpdateKlientetEmailDto klientetDto){
        String query= """
                UPDATE KLIENTET
                SET EMAIL=?
                WHERE KID=?
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

    */
    public boolean delete(int id){
        String query= """
                DELETE FROM KLIENTET 
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
