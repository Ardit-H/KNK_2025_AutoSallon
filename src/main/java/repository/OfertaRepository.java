package repository;

import models.dto.Ofertat.CreateOfertaDto;
import models.dto.Ofertat.Oferta;
import models.dto.Ofertat.UpdateOfertaDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.sql.Date;

public class OfertaRepository extends BaseRepository <Oferta, CreateOfertaDto, UpdateOfertaDto>{
    public OfertaRepository() {super("ofertat");};

    public Oferta fromResultSet(ResultSet rs) throws SQLException {
        return Oferta.getInstance(rs);
    }
    public Date data(String data){
        try {
            DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDate d = LocalDate.parse(data, formater);
            Date date = Date.valueOf(d);
            return date;
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }
        return null;
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
            pstm.setDate(4, this.data(ofertaDto.getDataFillimit()));
            pstm.setDate(5, this.data(ofertaDto.getDataMbarimit()));
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
            params.add(Date.valueOf(LocalDate.parse(ofertaDto.getDataFillimit(), DateTimeFormatter.ofPattern("yyyy/MM/dd"))));
        }
        if(ofertaDto.getDataMbarimit() != null){
            query.append("DATAMBARIMIT = ?, ");
            params.add(Date.valueOf(LocalDate.parse(ofertaDto.getDataMbarimit(), DateTimeFormatter.ofPattern("yyyy/MM/dd"))));
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
