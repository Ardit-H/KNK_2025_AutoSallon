package models.dto.Sherbimet;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Sherbimet {
    private int id;
    private String emri;
    private String pershkrimi;
    private double çmimi;

    public Sherbimet(int id, String emri, String pershkrimi, double çmimi) {
        this.id = id;
        this.emri = emri;
        this.pershkrimi = pershkrimi;
        this.çmimi = çmimi;
    }
    public static Sherbimet getInstance(ResultSet resultSet)throws SQLException{
        int id=resultSet.getInt("id");
        String emri=resultSet.getString("emri");
        String pershkrimi=resultSet.getString("pershkrimi");
        double çmimi=resultSet.getDouble("çmimi");
        return new Sherbimet(id,emri,pershkrimi,çmimi);
    }

    public int getId() {
        return id;
    }

    public String getEmri() {
        return emri;
    }

    public String getPershkrimi() {
        return pershkrimi;
    }

    public double getÇmimi() {
        return çmimi;
    }
}

