package models.dto.Klientet;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Klientet {

    private int kid;
    private String emri;
    private String mbiemri;
    private String email;
    private String nrtelefonit;
    private String adresa;
    private String data_regjistrimit;

    private Klientet(int kid,String emri,String mbiemri,String email,String nrtelefonit,String adresa,String data_regjistrimit){
        this.kid=kid;
        this.emri=emri;
        this.mbiemri=mbiemri;
        this.email=email;
        this.nrtelefonit=nrtelefonit;
        this.adresa=adresa;
        this.data_regjistrimit=data_regjistrimit;
    }

public static Klientet getInstance(ResultSet resultSet)throws SQLException{
    int kid=resultSet.getInt("kid");
    String emri=resultSet.getString("emri");
    String mbiemri=resultSet.getString("mbiemri");
    String email=resultSet.getString("email");
    String nrtelefonit=resultSet.getString("nrtelefonit");
    String adresa=resultSet.getString("adresa");
    String data_regjistrimit=resultSet.getString("data_regjistrimit");
    return new Klientet(kid,emri,mbiemri,email,nrtelefonit,adresa,data_regjistrimit);
}

    public int getKid() {
        return kid;
    }

    public String getEmri() {
        return emri;
    }

    public String getMbiemri() {
        return mbiemri;
    }

    public String getEmail() {
        return email;
    }

    public String getNrtelefonit() {
        return nrtelefonit;
    }

    public String getAdresa() {
        return adresa;
    }

    public String getData_regjistrimit() {
        return data_regjistrimit;
    }
}
