package models.dto.Porosite;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Porosia {
    private int porosiaId;
    private int kid;
    private int veturaId;
    private double cmimiOfruar;
    private String statusiPorosise;

    public Porosia(int porosiaId, int kid, int veturaId, double cmimiOfruar, String statusiPorosise) {
        this.porosiaId = porosiaId;
        this.kid = kid;
        this.veturaId = veturaId;
        this.cmimiOfruar = cmimiOfruar;
        this.statusiPorosise = statusiPorosise;
    }

    public static Porosia getInstance(ResultSet rs)throws SQLException {
        int porosiaId = rs.getInt("porosi_id");
        int kid = rs.getInt("kid");
        int veturaId = rs.getInt("vetura_id");
        double cmimiOfruar = rs.getDouble("cmimi_ofruar");
        String statusiPorosise = rs.getString("statusi_porosise");

       return new Porosia(porosiaId, kid, veturaId, cmimiOfruar, statusiPorosise);
    }

    public int getPorosiaId() {
        return porosiaId;
    }

    public int getKid() {
        return kid;
    }

    public int getVeturaId() {
        return veturaId;
    }

    public double getCmimiOfruar() {
        return cmimiOfruar;
    }

    public String getStatusiPorosise() {
        return statusiPorosise;
    }
}
