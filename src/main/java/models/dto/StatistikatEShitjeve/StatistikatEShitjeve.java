package models.dto.StatistikatEShitjeve;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StatistikatEShitjeve {

    private int statistika_id;
    private String muaji;
    private Double fitimi;
    private Double shpenzimet;
    private Double totali_shitjeve;

    private StatistikatEShitjeve(int statistika_id, String muaji, Double fitimi, Double shpenzimet, Double totali_shitjeve) {
        this.statistika_id = statistika_id;
        this.muaji = muaji;
        this.fitimi = fitimi;
        this.shpenzimet = shpenzimet;
        this.totali_shitjeve = totali_shitjeve;
    }

    public static StatistikatEShitjeve getInstance(ResultSet rs) throws SQLException {
        int statistika_id = rs.getInt("id");
        String muaji = rs.getString("muaji");
        Double fitimi = rs.getDouble("fitimi");
        Double shpenzimet = rs.getDouble("shpenzimet");
        Double totali_shitjeve = rs.getDouble("totali_shitjeve");
        return new StatistikatEShitjeve(statistika_id, muaji, fitimi, shpenzimet, totali_shitjeve);
    }

    public int getStatistika_id(){ return statistika_id; }

    public String getMuaji(){ return muaji; }

    public Double getFitimi(){ return fitimi; }

    public Double getShpenzimet(){ return shpenzimet; }

    public Double getTotali_shitjeve(){ return totali_shitjeve; }
}
