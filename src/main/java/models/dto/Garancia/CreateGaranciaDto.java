package models.dto.Garancia;

public class CreateGaranciaDto {
    private int vid;
    private int kid;
    private String llojiGarancise;
    private String dataFillimit;
    private String dataMbarimit;

    public CreateGaranciaDto(String llojiGarancise, String dataFillimit, String dataMbarimit, int kid, int vid) {
        this.llojiGarancise = llojiGarancise;
        this.dataFillimit = dataFillimit;
        this.dataMbarimit = dataMbarimit;
    }

    public int getVid() {
        return vid;
    }

    public void setVid(int vid) {
        this.vid = vid;
    }

    public int getKid() {
        return kid;
    }

    public void setKid(int kid) {
        this.kid = kid;
    }

    public String getLlojiGarancise() {
        return llojiGarancise;
    }

    public void setLlojiGarancise( String llojiGarancise) {
        this.llojiGarancise = llojiGarancise;
    }

    public String getDataFillimit() {
        return dataFillimit;
    }

    public void setDataFillimit(String dataFillimit) {
        this.dataFillimit = dataFillimit;
    }

    public String getDataMbarimit() {
        return dataMbarimit;
    }

    public void setDataMbarimit(String dataMbarimit) {
        this.dataMbarimit = dataMbarimit;
    }

}
