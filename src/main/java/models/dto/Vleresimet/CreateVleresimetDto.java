package models.dto.Vleresimet;

public class CreateVleresimetDto {
    private int perdoruesiId;
    private int veturaId;
    private int vleresimi;
    private String komenti;

    public CreateVleresimetDto(int perdoruesiId, int veturaId, int vleresimi, String komenti) {
        this.perdoruesiId = perdoruesiId;
        this.veturaId = veturaId;
        this.vleresimi = vleresimi;
        this.komenti = komenti;

    }
    public String getDataVleresimit() {
        return dataVleresimit;
    }

    public int getPerdoruesiId() {
        return perdoruesiId;
    }

    public void setPerdoruesiId(int perdoruesiId) {
        this.perdoruesiId = perdoruesiId;
    }

    public int getVeturaId() {
        return veturaId;
    }

    public void setVeturaId(int veturaId) {
        this.veturaId = veturaId;
    }

    public int getVleresimi() {
        return vleresimi;
    }

    public void setVleresimi(int vleresimi) {
        this.vleresimi = vleresimi;
    }

    public String getKomenti() {
        return komenti;
    }

    public void setKomenti(String komenti) {
        this.komenti = komenti;
    }

    private String dataVleresimit;

}
