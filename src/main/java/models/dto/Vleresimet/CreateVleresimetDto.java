package models.dto.Vleresimet;

public class CreateVleresimetDto {
    private int klientiId;
    private int veturaId;
    private int vleresimi;
    private String komenti;

    public String getDataVleresimit() {
        return dataVleresimit;
    }

    public int getKlientiId() {
        return klientiId;
    }

    public void setKlientiId(int klientiId) {
        this.klientiId = klientiId;
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

    public CreateVleresimetDto(int klientiId, int veturaId, int vleresimi, String komenti) {
        this.klientiId = klientiId;
        this.veturaId = veturaId;
        this.vleresimi = vleresimi;
        this.komenti = komenti;

    }
}
