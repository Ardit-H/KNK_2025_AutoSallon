package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Porosia {
    private int porosia_id;
    private int kid;
    private int vetura_id;
    private double cmimi_ofruar;
    private String statusi_porosise;

    public Porosia(int porosia_id, int kid, int vetura_id, double cmimi_ofruar, String statusi_porosise) {
        this.porosia_id = porosia_id;
        this.kid = kid;
        this.vetura_id = vetura_id;
        this.cmimi_ofruar = cmimi_ofruar;
        this.statusi_porosise = statusi_porosise;
    }

    public static Porosia getInstance(ResultSet rs)throws SQLException {
        int porosia_id = rs.getInt("porosi_id");
        int kid = rs.getInt("kid");
        int vetura_id = rs.getInt("vetura_id");
        double cmimi_ofruar = rs.getDouble("cmimi_ofruar");
        String statusi_porosise = rs.getString("statusi_porosise");

       return new Porosia(porosia_id, kid, vetura_id, cmimi_ofruar, statusi_porosise);
    }

    public int getPorosia_id() {
        return porosia_id;
    }

    public int getKid() {
        return kid;
    }

    public int getVetura_id() {
        return vetura_id;
    }

    public double getCmimi_ofruar() {
        return cmimi_ofruar;
    }

    public String getStatusi_porosise() {
        return statusi_porosise;
    }


}
