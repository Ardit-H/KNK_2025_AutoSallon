package models.dto.StatistikatEShitjeve;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StatistikatEShitjeve {

    private int statistika_id;
    private String muaji;
    private double fitimi;
    private double shpenzimet;
    private double totali_shitjeve;

    private StatistikatEShitjeve(int statistika_id, String muaji, double fitimi, double shpenzimet, double totali_shitjeve) {
        this.statistika_id = statistika_id;
        this.muaji = muaji;
        this.fitimi = fitimi;
        this.shpenzimet = shpenzimet;
        this.totali_shitjeve = totali_shitjeve;
    }

    public static StatistikatEShitjeve getInstance(ResultSet rs) throws SQLException {
        int statistika_id = rs.getInt("Statistikat_id");
        String muaji = rs.getString("Muaji");
        Double fitimi = rs.getDouble("Fitimi_total");
        Double shpenzimet = rs.getDouble("Shpenzimet_total");
        Double totali_shitjeve = rs.getDouble("totali_shitjeve");
        return new StatistikatEShitjeve(statistika_id, muaji, fitimi, shpenzimet, totali_shitjeve);
    }
}
