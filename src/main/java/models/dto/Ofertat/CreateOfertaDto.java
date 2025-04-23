package models.dto.Ofertat;

public class CreateOfertaDto {
    private int veturaId;
    private double zbritja;
    private double cmimiFinal;
    private String dataFillimit;
    private String dataMbarimit;

    public CreateOfertaDto(int veturaId, double zbritja, double cmimiFinal, String dataFillimit, String dataMbarimit) {
        this.veturaId = veturaId;
        this.zbritja = zbritja;
        this.cmimiFinal = cmimiFinal;
        this.dataFillimit = dataFillimit;
        this.dataMbarimit = dataMbarimit;
    }

    public int getVeturaId() {
        return veturaId;
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

    public void setVeturaId(int veturaId) {
        this.veturaId = veturaId;
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
