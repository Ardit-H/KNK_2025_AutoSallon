package repository;

import models.dto.Shitjet.CreateShitjetDto;
import models.dto.Shitjet.Shitjet;
import models.dto.Shitjet.UpdateShitjeDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ShitjetRepository extends BaseRepository<Shitjet, CreateShitjetDto, UpdateShitjeDto> {
    public ShitjetRepository(){ super("shitjet");}
    public Shitjet fromResultSet(ResultSet rs) throws SQLException{
        return Shitjet.getInstance(rs);
    }
    public Shitjet create(CreateShitjetDto shitjetDto){
        String query = """
                INSERT INTO SHITJET (KID, VETURA_ID, PUNETOR_ID, CMIMI_FINAL)
                VALUES(?,?,?,?)
                """;
        try{
            PreparedStatement pst = this.connection.prepareStatement(
                    query, Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, shitjetDto.getKid());
            pst.setInt(2, shitjetDto.getVetura_id());
            pst.setInt(3, shitjetDto.getPunetor_id());
            pst.setDouble(4, shitjetDto.getCmimi_final());
            pst.execute();
            ResultSet result = pst.getGeneratedKeys();
            if(result.next()){
                int id = result.getInt(1);
                return this.getById(id);
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public Shitjet update(UpdateShitjeDto shitjeDto){
        StringBuilder query = new StringBuilder("UPDATE SHITJET SET");
        ArrayList<Object> params = new ArrayList<>();

        if(shitjeDto.getKid() != null){
            query.append("KID = ?, ");
            params.add(shitjeDto.getKid());
        }
        if(shitjeDto.getVetura_id() != null){
            query.append("VETURA_ID = ?, ");
            params.add(shitjeDto.getVetura_id());
        }
        if(shitjeDto.getPunetor_id() != null){
            query.append("PUNETOR_ID = ?, ");
            params.add(shitjeDto.getPunetor_id());
        }
        if(shitjeDto.getCmimi_final() != null){
            query.append("CMIMI_FINAL = ?, ");
            params.add(shitjeDto.getCmimi_final());
        }
        if(params.isEmpty()){
            return getById(shitjeDto.getShitjet_id());
        }

        query.setLength(query.length() - 2);
        query.append(" WHERE ID = ?");
        params.add(shitjeDto.getShitjet_id());

        try{
            PreparedStatement pst = this.connection.prepareStatement(query.toString());
            for(int i = 0; i < params.size(); i++){
                pst.setObject(i + 1, params.get(i));
            }
            int updated = pst.executeUpdate();
            if(updated == 1){
                return this.getById(shitjeDto.getShitjet_id());
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
