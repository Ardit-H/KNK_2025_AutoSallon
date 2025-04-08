package models.dto.Shitjet;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Shitjet {

    private int shitje_id;
    private int kid;
    private int vetura_id;
    private int punetor_id;
    private String data_shitjes;
    private double cmimi_final;

    public Shitjet(int shitje_id, int kid, int vetura_id, int punetor_id, String data_shitjes, double cmimi_final) {
        this.shitje_id = shitje_id;
        this.kid = kid;
        this.vetura_id = vetura_id;
        this.punetor_id = punetor_id;
        this.data_shitjes = data_shitjes;
        this.cmimi_final = cmimi_final;
    }

    public static Shitjet getInstance(ResultSet Rs)throws SQLException{
        int shitje_id = Rs.getInt("shitje_id");
        int kid = Rs.getInt("kid");
        int vetura_id = Rs.getInt("vetura_id");
        int punetor_id = Rs.getInt("punetor_id");
        String data_shitjes = Rs.getString("data_shitjes");
        double cmimi_final = Rs.getDouble("cmimi_final");
        return new Shitjet(shitje_id, kid, vetura_id, punetor_id, data_shitjes, cmimi_final);
    }

    public int getShitje_id() { return shitje_id; }

    public int getKid() { return kid; }

    public int getVetura_id() { return vetura_id; }

    public int getPunetor_id() { return punetor_id; }

    public String getData_shitjes() { return data_shitjes; }

    public double getCmimi_final() { return cmimi_final; }
}
