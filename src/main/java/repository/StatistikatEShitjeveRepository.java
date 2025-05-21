package repository;

import models.dto.StatistikatEShitjeve.CreateStatistikatEShitjeveDto;
import models.dto.StatistikatEShitjeve.StatistikatEShitjeve;
import models.dto.StatistikatEShitjeve.UpdateStatistikatEShitjeveDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StatistikatEShitjeveRepository extends BaseRepository<StatistikatEShitjeve, CreateStatistikatEShitjeveDto, UpdateStatistikatEShitjeveDto> {
    public StatistikatEShitjeveRepository(){super("statistikat_e_shitjeve");}

    public StatistikatEShitjeve fromResultSet(ResultSet rs) throws SQLException {
        return StatistikatEShitjeve.getInstance(rs);
    }

    public StatistikatEShitjeve create(CreateStatistikatEShitjeveDto statistikatEShitjeveDto){
        String query = """
                INSERT INTO statistikat_e_shitjeve(muaji, fitimi, shpenzimet, totali_shitjeve)
                VALUES(?,?,?,?)
                """;
        try{
            PreparedStatement ps = this.connection.prepareStatement(
                    query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, statistikatEShitjeveDto.getMuaji());
            ps.setDouble(2, statistikatEShitjeveDto.getFitimi());
            ps.setDouble(3, statistikatEShitjeveDto.getShpenzimet());
            ps.setDouble(4, statistikatEShitjeveDto.getTotali_shitjeve());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                int id = rs.getInt(1);
                return this.getById(id);
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public StatistikatEShitjeve update(UpdateStatistikatEShitjeveDto statistikatEShitjeveDto){
        StringBuilder query = new StringBuilder("UPDATE statistikat_e_shitjeve SET ");
        ArrayList<Object> params = new ArrayList<>();


        if(statistikatEShitjeveDto.getFitimi() != null){
            query.append("FITIMI = ?, ");
            params.add(statistikatEShitjeveDto.getFitimi());
        }
        if(statistikatEShitjeveDto.getShpenzimet() != null){
            query.append("SHPENZIMET = ?, ");
            params.add(statistikatEShitjeveDto.getShpenzimet());
        }
        if(statistikatEShitjeveDto.getTotaliShitjeve() != null){
            query.append("TOTALI_SHITJEVE = ?, ");
            params.add(statistikatEShitjeveDto.getTotaliShitjeve());
        }
        if(params.isEmpty()){
            return getById(statistikatEShitjeveDto.getId());
        }

        query.setLength(query.length() - 2);
        query.append("WHERE ID = ?");
        params.add(statistikatEShitjeveDto.getId());

        try{
            PreparedStatement pst = this.connection.prepareStatement(query.toString());
            for(int i = 0; i < params.size(); i++){
                pst.setObject(i + 1, params.get(i));
            }
            int updated = pst.executeUpdate();
            if(updated == 1){
                return this.getById(statistikatEShitjeveDto.getId());
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public List<StatistikatEShitjeve> searchhByMonth(String month){
        String where = "LOWER(muaji) LIKE ?";
        String searchValue = "%" + month.toLowerCase() + "%";
        return searchWithCustomWhere(where, searchValue);
    }
}
