package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Veturat {
    private int vetura_id;
    private String prodhuesi;
    private String modeli;
    private int viti_prodhimit;
    private String ngjyra;
    private double cmimi;
    private String gjendja;
    private int kilometrazha;
    private String tipi_karburant;

    private Veturat(int vetura_id, String prodhuesi, String modeli, int viti_prodhimit, String ngjyra, double cmimi, String gjendja, int kilometrazha, String tipi_karburant) {
        this.vetura_id = vetura_id;
        this.prodhuesi = prodhuesi;
        this.modeli = modeli;
        this.viti_prodhimit = viti_prodhimit;
        this.ngjyra = ngjyra;
        this.cmimi = cmimi;
        this.gjendja = gjendja;
        this.kilometrazha = kilometrazha;
        this.tipi_karburant = tipi_karburant;
    }

    public static Veturat getInstance(ResultSet resultSet) throws SQLException {
        int vetura_id = resultSet.getInt("vetura_id");
        String prodhuesi = resultSet.getString("prodhuesi");
        String modeli = resultSet.getString("modeli");
        int viti_prodhimit = resultSet.getInt("viti_prodhimit");
        String ngjyra = resultSet.getString("ngjyra");
        double cmimi = resultSet.getDouble("cmimi");
        String gjendja = resultSet.getString("gjendja");
        int kilometrazha = resultSet.getInt("kilometrazha");
        String tipi_karburant = resultSet.getString("tipi_karburant");
        return new Veturat(vetura_id, prodhuesi, modeli, viti_prodhimit, ngjyra, cmimi, gjendja, kilometrazha, tipi_karburant);
    }

    public int getVetura_id() {
        return vetura_id;
    }

    public String getProdhuesi() {
        return prodhuesi;
    }

    public String getModeli() {
        return modeli;
    }

    public int getViti_prodhimit() {
        return viti_prodhimit;
    }

    public String getNgjyra() {
        return ngjyra;
    }

    public double getCmimi() {
        return cmimi;
    }
    public String getGjendja(){
        return gjendja;
    }

    public int getKilometrazha() {
        return kilometrazha;
    }

    public String getTipi_karburant() {
        return tipi_karburant;
    }
}
