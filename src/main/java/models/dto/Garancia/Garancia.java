package models.dto.Garancia;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Garancia {
    private int id;
    private int vid;
    private int kid;
    private String llojiGarancise;
    private String dataFillimit;
    private String dataMbarimit;

    public Garancia(int id, int vid, int kid, String llojiGarancise, String dataFillimit, String dataMbarimit) {
        this.id = id;
        this.vid = vid;
        this.kid = kid;
        this.llojiGarancise = llojiGarancise;
        this.dataFillimit = dataFillimit;
        this.dataMbarimit = dataMbarimit;
    }

    public static Garancia getInstance(ResultSet resultSet)throws SQLException{
        int id = resultSet.getInt("id");
        int vid = resultSet.getInt("vid");
        int kid = resultSet.getInt("kid");
        String llojiGarancise = resultSet.getString("Lloji i garancise");
        String dataFillimit = resultSet.getString("Data e fillimit");
        String dataMbarimit = resultSet.getString("Data e mbarimit");
        return new Garancia(id,vid,kid,llojiGarancise,dataFillimit,dataMbarimit);
    }

    public int getId() {
        return id;
    }

    public int getVid() {
        return vid;
    }

    public int getKid() {
        return kid;
    }

    public String getLlojiGarancise() {
        return llojiGarancise;
    }

    public String getDataFillimit() {
        return dataFillimit;
    }

    public String getDataMbarimit() {
        return dataMbarimit;
    }
}
