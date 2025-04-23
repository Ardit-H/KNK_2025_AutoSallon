package repository;

import models.dto.Ofertat.CreateOfertaDto;
import models.dto.Ofertat.Oferta;
import models.dto.Ofertat.UpdateOfertaDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class OfertaRepository extends BaseRepository <Oferta, CreateOfertaDto, UpdateOfertaDto>{
    public OfertaRepository() {super("ofertat");};

    public Oferta fromResultSet(ResultSet rs) throws SQLException {
        return Oferta.getInstance(rs);
    }

    public Oferta create(CreateOfertaDto ofertaDto) {
        String query = """
                INSERT INTO OFERTAT(veturaId, zbritja, cmimiFinal, dataFillimit, dataMbarimit)
                VALUES(?, ?, ?, ?, ?)""";

        try{
            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setInt(1, ofertaDto.getVeturaId());
            pstm.setDouble(2, ofertaDto.getZbritja());
            pstm.setDouble(3, ofertaDto.getCmimiFinal());
            pstm.setString(4, ofertaDto.getDataFillimit());
            pstm.setString(5, ofertaDto.getDataMbarimit());
            pstm.execute();

            ResultSet rs = pstm.getGeneratedKeys();
            if(rs.next()){
                int id = rs.getInt(1);
                return this.getById(id);
            }

        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public Oferta update(UpdateOfertaDto ofertaDto) {
        StringBuilder query = new StringBuilder("UPDATE OFERTAT SET ");
        ArrayList<Object> params = new ArrayList<>();

        if(ofertaDto.getZbritja() != 0){
            query.append("ZBRITJA = ?, ");
            params.add(ofertaDto.getZbritja());
        }
        if(ofertaDto.getCmimiFinal() != 0){
            query.append("CMIMIFINAL = ?, ");
            params.add(ofertaDto.getCmimiFinal());
        }
        if(ofertaDto.getDataFillimit() != null){
            query.append("DATAFILLIMIT = ?, ");
        }
        if(ofertaDto.getDataMbarimit() != null){
            query.append("DATAMBARIMIT = ?, ");
            params.add(ofertaDto.getDataMbarimit());
        }

        query.setLength(query.length() - 2);
        query.append(" WHERE ID = ?");
        params.add(ofertaDto.getOfertaId());

        try{
            PreparedStatement pstm = this.connection.prepareStatement(query.toString());
            for(int i = 0; i < params.size(); i++){
                pstm.setObject(i + 1, params.get(i));
            }
            int updated = pstm.executeUpdate();
            if(updated == 1) {
                return this.getById(ofertaDto.getOfertaId());
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

}
