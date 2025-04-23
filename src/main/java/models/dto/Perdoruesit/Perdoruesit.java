package models.dto.Perdoruesit;

import java.sql.SQLException;
import java.sql.ResultSet;

public class Perdoruesit {
private int pid;
private String emri;
private String email;
private String fjalekalimi;
private String roli;

private Perdoruesit(int pid, String emri, String email, String fjalekalimi, String roli){
    this.pid = pid;
    this.emri = emri;
    this.email = email;
    this.fjalekalimi = fjalekalimi;
    this.roli = roli;
}

public static Perdoruesit getInstance(ResultSet resultSet) throws SQLException{
    int pid = resultSet.getInt("perdorues_id");
    String emri = resultSet.getString("emri");
    String email = resultSet.getString("email");
    String fjalekalimi = resultSet.getString("fjalekalimi");
    String roli = resultSet.getString("roli");

    return new Perdoruesit(pid, emri, email, fjalekalimi, roli);
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

public String getFjalekalimi(){
    return fjalekalimi;
}

public String getRoli(){
    return roli;
}
}
