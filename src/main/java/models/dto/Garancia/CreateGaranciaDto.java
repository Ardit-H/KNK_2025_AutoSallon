package models.dto.Garancia;

public class CreateGaranciaDto {
    private String llojiGarancise;
    private String dataFillimit;
    private String dataMbarimit;

    public CreateGaranciaDto(String llojiGarancise, String dataFillimit, String dataMbarimit) {
        this.llojiGarancise = llojiGarancise;
        this.dataFillimit = dataFillimit;
        this.dataMbarimit = dataMbarimit;
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
