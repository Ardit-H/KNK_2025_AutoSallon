package repository;

import models.dto.Porosite.CreatePorosiaDto;
import models.dto.Porosite.Porosia;
import models.dto.Porosite.UpdatePorosiaDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PorositeRepository extends BaseRepository<Porosia, CreatePorosiaDto, UpdatePorosiaDto> {
    public PorositeRepository() { super("porosite"); }

    public Porosia fromResultSet(ResultSet resultSet) throws SQLException {
        return Porosia.getInstance(resultSet);
    }

    public Porosia create(CreatePorosiaDto porosiaDto) {
        String query = """
                        INSERT INTO POROSITE(kid, veturaId, cmimiOfruar, statusiPorosise)
                        VALUES(?,?,?,?)""";

        try{
            PreparedStatement pstm = this.connection.prepareStatement(
                    query, Statement.RETURN_GENERATED_KEYS);

            pstm.setInt(1, porosiaDto.getKid());
            pstm.setInt(2, porosiaDto.getVeturaId());
            pstm.setDouble(3, porosiaDto.getCmimiOfruar());
            pstm.setString(4, porosiaDto.getStatusiPorosise());
            pstm.execute();

            ResultSet resultSet = pstm.getGeneratedKeys();
            if(resultSet.next()){
                int id = resultSet.getInt(1);
                return this.getById(id);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public Porosia update(UpdatePorosiaDto porosiaDto) {
        StringBuilder query = new StringBuilder("UPDATE POROSITE SET ");
        ArrayList<Object> params = new ArrayList<>();

        if(porosiaDto.getCmimiOfruar() != 0){
            query.append("CMIMIOFRUAR = ?, ");
            params.add(porosiaDto.getCmimiOfruar());
        }
        if(porosiaDto.getStatusiPorosise() != null){
            query.append("STATUSIPOROSISE = ?, ");
            params.add(porosiaDto.getStatusiPorosise());
        }

        if(params.isEmpty()){
            return getById(porosiaDto.getPorosiaId());
        }

        query.setLength(query.length() - 2);
        query.append(" WHERE ID = ?");
        params.add(porosiaDto.getPorosiaId());

        try{
            PreparedStatement pstm = this.connection.prepareStatement(query.toString());
            for(int i = 0; i < params.size(); i++){
                pstm.setObject(i + 1, params.get(i));
            }
            int updated = pstm.executeUpdate();
            if(updated == 1) {
                return this.getById(porosiaDto.getPorosiaId());
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

}
