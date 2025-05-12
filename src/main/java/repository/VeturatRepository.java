package repository;

import Database.DBConnector;
import models.dto.Veturat.Veturat;
import models.dto.Veturat.CreateVeturatDto;
import models.dto.Veturat.UpdateVeturatDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeturatRepository extends BaseRepository<Veturat, CreateVeturatDto, UpdateVeturatDto>{
    public VeturatRepository(){
        super("veturat");
    }

    protected Veturat fromResultSet(ResultSet result) throws SQLException{
        return Veturat.getInstance(result);
    }

    public Veturat create(CreateVeturatDto veturatDto){
        String query ="""
                INSERT INTO VETURAT(prodhuesi,modeli,viti_prodhimit,ngjyra,cmimi,gjendja,kilometrazha,tipi_karburant)
                 VALUES(?, ?, ?, ?, ?, ?, ?, ?)
                """;
        try{
            PreparedStatement pstm=this.connection.prepareStatement(
                    query,Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1,veturatDto.getProdhuesi());
            pstm.setString(2,veturatDto.getModeli());
            pstm.setInt(3,veturatDto.getVitiProdhimit());
            pstm.setString(4,veturatDto.getNgjyra());
            pstm.setDouble(5,veturatDto.getCmimi());
            pstm.setString(6,veturatDto.getGjendja());
            pstm.setInt(7,veturatDto.getKilometrazha());
            pstm.setString(8,veturatDto.getTipiKarburant());

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
            query.append("GJENDJA = ?, ");
            params.add(veturatDto.getGjendja());
        }
        if(veturatDto.getNgjyra() != null){
            query.append("NGJYRA = ?, ");
            params.add(veturatDto.getNgjyra());
        }
        if(veturatDto.getKilometrazha() != 0) {
            query.append("KILOMETRAZHA = ?, ");
            params.add(veturatDto.getKilometrazha());
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

    public List<Veturat> kerkoSipasProdhuesit(String prodhuesi) {
        List<Veturat> rezultatet = new ArrayList<>();
        String sql = "SELECT * FROM veturat WHERE LOWER(prodhuesi) LIKE ?";

        try (PreparedStatement stmt = this.connection.prepareStatement(sql)){

            stmt.setString(1, "%" + prodhuesi.toLowerCase() + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Veturat v = new Veturat(
                        rs.getInt("id"),
                        rs.getString("prodhuesi"),
                        rs.getString("modeli"),
                        rs.getInt("viti_prodhimit"),
                        rs.getString("ngjyra"),
                        rs.getDouble("cmimi"),
                        rs.getString("gjendja"),
                        rs.getInt("kilometrazha"),
                        rs.getString("tipi_karburant")
                );
                rezultatet.add(v);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rezultatet;
    }
}
