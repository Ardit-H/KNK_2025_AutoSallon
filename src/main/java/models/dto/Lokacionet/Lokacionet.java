package models.dto.Lokacionet;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Lokacionet {

    private int lokacionet_id;
    private String emri_lokacionit;
    private String adresa;
    private String qyteti;
    private String nrtelefonit;

    private Lokacionet(int lokacionet_id, String emri_lokacionit, String adresa, String qyteti, String nrtelefonit){
        this.lokacionet_id = lokacionet_id;
        this.emri_lokacionit = emri_lokacionit;
        this.adresa = adresa;
        this.qyteti = qyteti;
        this.nrtelefonit = nrtelefonit;
    }

    public static Lokacionet getInstance(ResultSet rs) throws SQLException {
        int lokacionet_id = rs.getInt("lokacionet_id");
        String emri_lokacionit = rs.getString("emri_lokacionit");
        String adresa = rs.getString("adresa");
        String qyteti = rs.getString("qyteti");
        String nrtelefonit = rs.getString("nrtelefonit");
        return new Lokacionet(lokacionet_id, emri_lokacionit, adresa, qyteti, nrtelefonit);
    }

    public int getLokacionet_id(){ return lokacionet_id; }

    public String getEmri_lokacionit(){ return emri_lokacionit; }

    public String getAdresa(){ return adresa; }

    public String getQyteti(){ return qyteti; }

    public String getNrtelefonit(){ return nrtelefonit; }
}
