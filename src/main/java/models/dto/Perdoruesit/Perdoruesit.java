package models.dto.Perdoruesit;

import java.sql.SQLException;
import java.sql.ResultSet;

public class Perdoruesit {
private int pid;
private String emri;
private String email;
private String roli;
private String passwordHash;
private String salt;

    public Perdoruesit(int pid, String emri, String email, String passwordHash, String salt, String roli) {
        this.pid = pid;
        this.emri = emri;
        this.email = email;
        this.passwordHash = passwordHash;
        this.salt = salt;
        this.roli = roli;
    }

public static Perdoruesit getInstance(ResultSet resultSet) throws SQLException{
    int pid = resultSet.getInt("id");
    String emri = resultSet.getString("emri");
    String email = resultSet.getString("email");
    String pwHash = resultSet.getString("password_hash");
    String salt = resultSet.getString("salt");
    String roli = resultSet.getString("roli");

    return new Perdoruesit(pid, emri, email, pwHash,salt, roli);
}

public int getPid(){
    return pid;
}

public String getEmri(){
    return emri;
}

public String getEmail(){
    return email;
}


public String getRoli(){
    return roli;
}
    public String getPasswordHash() { return passwordHash; }
    public String getSalt() { return salt; }
}

