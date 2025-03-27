package models.dto.Veturat;

public class CreateVeturatDto {
    private String prodhuesi;
    private String modeli;
    private int viti_prodhimit;
    private String ngjyra;
    private double cmimi;
    private String gjendja;
    private int kilometrazha;
    private String tipi_karburant;

    public CreateVeturatDto(String prodhuesi, String modeli, int viti_prodhimit, String ngjyra, double cmimi, String gjendja, int kilometrazha, String tipi_karburant){
        this.prodhuesi = prodhuesi;
        this.modeli = modeli;
        this.viti_prodhimit = viti_prodhimit;
        this.ngjyra = ngjyra;
        this.cmimi = cmimi;
        this.gjendja = gjendja;
        this.kilometrazha = kilometrazha;
        this.tipi_karburant = tipi_karburant;
    }

    public String getProdhuesi() {
        return prodhuesi;
    }

    public String getModeli() {
        return modeli;
    }

    public int getViti_prodhimit() {
        return viti_prodhimit;
    }

    public String getNgjyra() {
        return ngjyra;
    }

    public double getCmimi() {
        return cmimi;
    }
    public String getGjendja(){
        return gjendja;
    }

    public int getKilometrazha() {
        return kilometrazha;
    }

    public String getTipi_karburant() {
        return tipi_karburant;
    }

    public void setProdhuesi(String prodhuesi) {
        this.prodhuesi = prodhuesi;
    }

    public void setModeli(String modeli) {
        this.modeli = modeli;
    }

    public void setViti_prodhimit(int viti_prodhimit) {
        this.viti_prodhimit = viti_prodhimit;
    }

    public void setNgjyra(String ngjyra) {
        this.ngjyra = ngjyra;
    }

    public void setGjendja(String gjendja) {
        this.gjendja = gjendja;
    }

    public void setCmimi(double cmimi) {
        this.cmimi = cmimi;
    }

    public void setKilometrazha(int kilometrazha) {
        this.kilometrazha = kilometrazha;
    }

    public void setTipi_karburant(String tipi_karburant) {
        this.tipi_karburant = tipi_karburant;
    }
}
