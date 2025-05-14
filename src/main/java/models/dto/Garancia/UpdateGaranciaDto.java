package models.dto.Garancia;

public class UpdateGaranciaDto {
    private int id;
    private String llojiGarancise;
    private String dataFillimit;
    private String dataMbarimit;

    public UpdateGaranciaDto(){}

    public int getId() {
        return id;
    }

    public String getLlojiGarancise() {
        return llojiGarancise;
    }

    public void setLlojiGarancise(String llojiGarancise) {
        this.llojiGarancise = llojiGarancise;
    }

    public void setId(int id) {
        this.id = id;
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
