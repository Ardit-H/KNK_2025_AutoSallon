package repository;

import models.dto.Pagesat.CreatePagesaDto;
import models.dto.Pagesat.Pagesa;
import models.dto.Pagesat.UpdatePagesaDto;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;


public class PagesaRepository extends BaseRepository <Pagesa, CreatePagesaDto, UpdatePagesaDto>{
    public PagesaRepository() {super("pagesat");}

    public Pagesa fromResultSet(ResultSet resultSet) throws SQLException {
        return Pagesa.getInstance(resultSet);
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

    public Pagesa create(CreatePagesaDto pagesaDto) {
        String query = """
                 INSERT INTO PAGESAT(porosiaId, metodaPageses, shuma, dataPageses)
                 VALUES (?, ?, ?, ?)
                 """;

        try{
            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setInt(1, pagesaDto.getPorosiaId());
            pstm.setString(2, pagesaDto.getMetodaPageses());
            pstm.setDouble(3, pagesaDto.getShuma());
            pstm.setDate(4, data(pagesaDto.getDataPageses()));
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

    public Pagesa update(UpdatePagesaDto pagesaDto) {
        StringBuilder query = new StringBuilder("UPDATE PAGESAT SET ");
        ArrayList<Object> params = new ArrayList<>();

        if(pagesaDto.getMetodaPageses() != null){
            query.append("METODAPAGESES = ?, ");
            params.add(pagesaDto.getMetodaPageses());
        }
        if(pagesaDto.getShuma() != 0){
            query.append("SHUMA = ?, ");
            params.add(pagesaDto.getShuma());
        }
        if(pagesaDto.getDataPageses() != null){
            query.append("DATAPAGESES = ?, ");
            params.add(Date.valueOf(LocalDate.parse(pagesaDto.getDataPageses(), DateTimeFormatter.ofPattern("yyyy/MM/dd"))));
        }
        if(params.isEmpty()){
            return getById(pagesaDto.getPagesaId());
        }

        query.setLength(query.length()-2);
        query.append(" WHERE ID = ?");
        params.add(pagesaDto.getPagesaId());

        try{
            PreparedStatement pstm = this.connection.prepareStatement(query.toString());
            for(int i = 0; i < params.size(); i++){
                pstm.setObject(i + 1, params.get(i));
            }
            int updated=pstm.executeUpdate();
            if(updated == 1) {
                return this.getById(pagesaDto.getPagesaId());
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
