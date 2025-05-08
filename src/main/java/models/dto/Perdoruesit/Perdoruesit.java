package models.dto.Perdoruesit;

import java.sql.SQLException;
import java.sql.ResultSet;

public class Perdoruesit {
private int pid;
private String emri;
private String mbiemri;
private String email;
private String nrtelefonit;
private String adresa;
private String dataRegjistrimit;
private String roli;
private String passwordHash;
private String salt;

    public Perdoruesit(int pid, String emri, String mbiemri, String email, String nrtelefonit, String adresa,
                       String dataRegjistrimit, String roli, String passwordHash, String salt) {
        this.pid = pid;
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.email = email;
        this.nrtelefonit = nrtelefonit;
        this.adresa = adresa;
        this.dataRegjistrimit = dataRegjistrimit;
        this.roli = roli;
        this.passwordHash = passwordHash;
        this.salt = salt;
    }

    public static Perdoruesit getInstance(ResultSet resultSet) throws SQLException {
        int pid = resultSet.getInt("id");
        String emri = resultSet.getString("emri");
        String mbiemri = resultSet.getString("mbiemri");
        String email = resultSet.getString("email");
        String nrtelefonit = resultSet.getString("nrtelefonit");
        String adresa = resultSet.getString("adresa");
        String dataRegjistrimit = resultSet.getString("data_regjistrimit");
        String roli = resultSet.getString("roli");
        String passwordHash = resultSet.getString("passwordhash");
        String salt = resultSet.getString("salt");

        return new Perdoruesit(pid, emri, mbiemri, email, nrtelefonit, adresa, dataRegjistrimit, roli, passwordHash, salt);
    }

    public int getPid() { return pid; }
    public String getEmri() { return emri; }
    public String getMbiemri() { return mbiemri; }
    public String getEmail() { return email; }
    public String getNrtelefonit() { return nrtelefonit; }
    public String getAdresa() { return adresa; }
    public String getDataRegjistrimit() { return dataRegjistrimit; }
    public String getRoli() { return roli; }
    public String getPasswordHash() { return passwordHash; }
    public String getSalt() { return salt; }
}

