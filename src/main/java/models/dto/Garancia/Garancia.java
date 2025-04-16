package models.dto.Garancia;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Garancia {
    private int gid;
    private int vid;
    private int kid;
    private String llojiGarancise;
    private String dataFillimit;
    private String dataMbarimit;

    public Garancia(int gid, int vid, int kid, String llojiGarancise, String dataFillimit, String dataMbarimit) {
        this.gid = gid;
        this.vid = vid;
        this.kid = kid;
        this.llojiGarancise = llojiGarancise;
        this.dataFillimit = dataFillimit;
        this.dataMbarimit = dataMbarimit;
    }

    public static Garancia getInstance(ResultSet resultSet)throws SQLException{
        int gid = resultSet.getInt("id");
        int vid = resultSet.getInt("Vetura ID");
        int kid = resultSet.getInt("Klienti ID");
        String llojiGarancise = resultSet.getString("Lloji i garancise");
        String dataFillimit = resultSet.getString("Data e fillimit");
        String dataMbarimit = resultSet.getString("Data e mbarimit");
        return new Garancia(gid,vid,kid,llojiGarancise,dataFillimit,dataMbarimit);
    }

    public int getGid() {
        return gid;
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
