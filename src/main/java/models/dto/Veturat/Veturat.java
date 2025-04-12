package models.dto.Veturat;

import models.dto.Garancia.Garancia;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Veturat {
    private int veturaId;
    private String prodhuesi;
    private String modeli;
    private int vitiProdhimit;
    private String ngjyra;
    private double cmimi;
    private String gjendja;
    private int kilometrazha;
    private String tipiKarburant;

    public Veturat(int veturaId, String prodhuesi, String modeli, int vitiProdhimit, String ngjyra, double cmimi, String gjendja, int kilometrazha, String tipiKarburant) {
        this.veturaId = veturaId;
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
        int veturaId = resultSet.getInt("VeturaDd");
        String prodhuesi = resultSet.getString("Prodhuesi");
        String modeli = resultSet.getString("Modeli");
        int vitiPordhimit = resultSet.getInt("Viti i prodhimit");
        String ngjyra = resultSet.getString("Ngjyra");
        double cmimi = resultSet.getDouble("Cmimi");
        String gjendja = resultSet.getString("Gjendja");
        int kilometrazha = resultSet.getInt("Kilometrazha");
        String tipiKarburant = resultSet.getString("Tipi i karburantit");
        return new Veturat(veturaId,prodhuesi,modeli,vitiPordhimit,ngjyra,cmimi,gjendja,kilometrazha,tipiKarburant);
    }


    public int getVeturaId() {
        return veturaId;
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
