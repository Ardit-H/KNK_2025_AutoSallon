package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Veturat {
    private int veturaid;
    private String prodhuesi;
    private String modeli;
    private int vitiprodhimit;
    private String ngjyra;
    private double cmimi;
    private String gjendja;
    private int kilometrazha;
    private String tipikarburant;

    private Veturat(int veturaid, String prodhuesi, String modeli, int vitiprodhimit, String ngjyra, double cmimi, String gjendja, int kilometrazha, String tipikarburant) {
        this.veturaid = veturaid;
        this.prodhuesi = prodhuesi;
        this.modeli = modeli;
        this.vitiprodhimit = vitiprodhimit;
        this.ngjyra = ngjyra;
        this.cmimi = cmimi;
        this.gjendja = gjendja;
        this.kilometrazha = kilometrazha;
        this.tipikarburant = tipikarburant;
    }

    public static Veturat getInstance(ResultSet resultSet) throws SQLException {
        int veturaid = resultSet.getInt("veturaid");
        String prodhuesi = resultSet.getString("prodhuesi");
        String modeli = resultSet.getString("modeli");
        int vitiprodhimit = resultSet.getInt("vitiprodhimit");
        String ngjyra = resultSet.getString("ngjyra");
        double cmimi = resultSet.getDouble("cmimi");
        String gjendja = resultSet.getString("gjendja");
        int kilometrazha = resultSet.getInt("kilometrazha");
        String tipikarburant = resultSet.getString("tipikarburant");
        return new Veturat(veturaid, prodhuesi, modeli, vitiprodhimit, ngjyra, cmimi, gjendja, kilometrazha, tipikarburant);
    }

    public int getVeturaid() {
        return veturaid;
    }

    public String getProdhuesi() {
        return prodhuesi;
    }

    public String getModeli() {
        return modeli;
    }

    public int getVitiprodhimit() {
        return vitiprodhimit;
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

    public String getTipikarburant() {
        return tipikarburant;
    }
}
