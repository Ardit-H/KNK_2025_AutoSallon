package models.dto.Pagesat;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Pagesa {
    private int pagesaId;
    private int porosiaId;
    private String metodaPageses;
    private double shuma;
    private String dataPageses;

    private Pagesa(int pagesaId, int porosiaId, String metodaPageses, double shuma, String dataPageses) {
        this.pagesaId = pagesaId;
        this.porosiaId = porosiaId;
        this.metodaPageses = metodaPageses;
        this.shuma = shuma;
        this.dataPageses = dataPageses;
    }

    public static Pagesa getInstance(ResultSet rs) throws SQLException {
        int pagesaId = rs.getInt("id");
        int porosiaId = rs.getInt("porosiaId");
        String metodaPageses = rs.getString("metodaPageses");
        double shuma = rs.getDouble("shuma");
        String dataPageses = rs.getString("dataPageses");
        return new Pagesa(pagesaId, porosiaId, metodaPageses, shuma, dataPageses);
    }

    public int getPagesaId() {
        return pagesaId;
    }

    public int getPorosiaId() {
        return porosiaId;
    }

    public String getMetodaPageses() {
        return metodaPageses;
    }

    public double getShuma() {
        return shuma;
    }

    public String getDataPageses() {
        return dataPageses;
    }
}
