package repository;

import Database.DBConnector;
import models.dto.Perdoruesit.Perdoruesit;
import models.dto.Perdoruesit.CreatePerdoruesitDto;
import models.dto.Perdoruesit.UpdatePerdoruesitDto;

import java.sql.*;
import java.util.ArrayList;
public class PerdoruesitRepository extends BaseRepository<Perdoruesit, CreatePerdoruesitDto, UpdatePerdoruesitDto> {
    public PerdoruesitRepository(){
        super("perdoruesit");
    }

    public Perdoruesit fromResultSet(ResultSet result) throws SQLException{
        return Perdoruesit.getInstance(result);

    }

    public Perdoruesit create(CreatePerdoruesitDto perdoruesitDto){
        String query = """
                INSERT INTO PERDORUESIT (emri, email, fjalekalimi)
                VALUES (?,?,?)""";
        try{
            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, perdoruesitDto.getEmri());
            pstm.setString(2, perdoruesitDto.getEmail());
            pstm.setString(3, perdoruesitDto.getFjalekalimi());
            pstm.execute();
            ResultSet resultSet = pstm.getGeneratedKeys();
            if(resultSet.next()){
                int id = resultSet.getInt(1);
                return this.getById(id);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public Perdoruesit update(UpdatePerdoruesitDto perdoruesitDto){
        StringBuilder query = new StringBuilder("UPDATE PERDORUESIT SET ");
        ArrayList<Object> params = new ArrayList<>();

        if(perdoruesitDto.getEmail() != null){
            query.append("EMAIL = ?, ");
            params.add(perdoruesitDto.getEmail());
        }

        if(perdoruesitDto.getFjalekalimi() != null){
            query.append("FJALEKALIMI = ?, ");
            params.add(perdoruesitDto.getFjalekalimi());
        }

        if(perdoruesitDto.getRoli() != null){
            query.append("ROLI = ?, ");
            params.add(perdoruesitDto.getRoli());
        }

        if(params.isEmpty()){
            return getById(perdoruesitDto.getId());
        }

        query.setLength(query.length() - 2);
        query.append("WHERE ID = ?");
        params.add(perdoruesitDto.getId());


        try{
            PreparedStatement pstm = this.connection.prepareStatement(query.toString());
            for(int i =0; i<params.size(); i++){
                pstm.setObject(i+1, params.get(i));
            }
            int updated = pstm.executeUpdate();
            if(updated ==1){
                return this.getById(perdoruesitDto.getId());
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public Perdoruesit getByEmailAndPassword(String email, String fjalekalimi) {
        String query = "SELECT * FROM PERDORUESIT WHERE EMAIL = ? AND FJALEKALIMI = ?";
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setString(1, email);
            pstm.setString(2, fjalekalimi);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                return fromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
