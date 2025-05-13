package models.dto.Veturat;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Veturat {
    private int id;
    private String prodhuesi;
    private String modeli;
    private int vitiProdhimit;
    private String ngjyra;
    private double cmimi;
    private String gjendja;
    private int kilometrazha;
    private String tipiKarburant;

    public Veturat(int id, String prodhuesi, String modeli, int vitiProdhimit, String ngjyra, double cmimi, String gjendja, int kilometrazha, String tipiKarburant) {
        this.id = id;
        this.prodhuesi = prodhuesi;
        this.modeli = modeli;
        this.vitiProdhimit = vitiProdhimit;
        this.ngjyra = ngjyra;
        this.cmimi = cmimi;
        this.gjendja = gjendja;
        this.kilometrazha = kilometrazha;
        this.tipiKarburant = tipiKarburant;
    }

    public static Veturat getInstance(ResultSet resultSet)throws SQLException{
        int id = resultSet.getInt("id");
        String prodhuesi = resultSet.getString("prodhuesi");
        String modeli = resultSet.getString("modeli");
        int vitiProdhimit = resultSet.getInt("viti_prodhimit");
        String ngjyra = resultSet.getString("ngjyra");
        double cmimi = resultSet.getDouble("cmimi");
        String gjendja = resultSet.getString("gjendja");
        int kilometrazha = resultSet.getInt("kilometrazha");
        String tipiKarburant = resultSet.getString("tipi_karburant");
        return new Veturat(id,prodhuesi,modeli,vitiProdhimit,ngjyra,cmimi,gjendja,kilometrazha,tipiKarburant);
    }


    public int getId() {
        return id;
    }

    public String getProdhuesi() {
        return prodhuesi;
    }

    public String getModeli() {
        return modeli;
    }

    public int getVitiProdhimit() {
        return vitiProdhimit;
    }

    public String getNgjyra() {
        return ngjyra;
    }

    public double getCmimi() {
        return cmimi;
    }

    public String getGjendja() {
        return gjendja;
    }

    public int getKilometrazha() {
        return kilometrazha;
    }

    public String getTipiKarburant() {
        return tipiKarburant;
    }
}
