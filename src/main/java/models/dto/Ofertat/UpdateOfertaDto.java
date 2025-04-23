package models.dto.Ofertat;

public class UpdateOfertaDto {
    private int ofertaId;
    private double zbritja;
    private double cmimiFinal;
    private String dataFillimit;
    private String dataMbarimit;

    public int getOfertaId() {
        return ofertaId;
    }

    public double getZbritja() {
        return zbritja;
    }

    public double getCmimiFinal() {
        return cmimiFinal;
    }

    public String getDataFillimit() {
        return dataFillimit;
    }

    public String getDataMbarimit() {
        return dataMbarimit;
    }

    public void setOfertaId(int ofertaId) {
        this.ofertaId = ofertaId;
    }

    public void setZbritja(double zbritja) {
        this.zbritja = zbritja;
    }

    public void setCmimiFinal(double cmimiFinal) {
        this.cmimiFinal = cmimiFinal;
    }

    public void setDataFillimit(String dataFillimit) {
        this.dataFillimit = dataFillimit;
    }

    public void setDataMbarimit(String dataMbarimit) {
        this.dataMbarimit = dataMbarimit;
    }
}
