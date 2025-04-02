package models.dto.Veturat;

public class CreateVeturatDto {
    private String prodhuesi;
    private String modeli;
    private int vitiprodhimit;
    private String ngjyra;
    private double cmimi;
    private String gjendja;
    private int kilometrazha;
    private String tipikarburant;

    public CreateVeturatDto(String prodhuesi, String modeli, int vitiprodhimit, String ngjyra, double cmimi, String gjendja, int kilometrazha, String tipikarburant){
        this.prodhuesi = prodhuesi;
        this.modeli = modeli;
        this.vitiprodhimit = vitiprodhimit;
        this.ngjyra = ngjyra;
        this.cmimi = cmimi;
        this.gjendja = gjendja;
        this.kilometrazha = kilometrazha;
        this.tipikarburant = tipikarburant;
    }

    public String getProdhuesi() {
        return prodhuesi;
    }

    public String getModeli() {
        return modeli;
    }

    public int getVitiprodhimit() {
        return vitiprodhimit;
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

    public String getTipikarburant() {
        return tipikarburant;
    }

    public void setProdhuesi(String prodhuesi) {
        this.prodhuesi = prodhuesi;
    }

    public void setModeli(String modeli) {
        this.modeli = modeli;
    }

    public void setViti_prodhimit(int vitiprodhimit) {
        this.vitiprodhimit = vitiprodhimit;
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

    public void setTipikarburant(String tipikarburant) {
        this.tipikarburant = tipikarburant;
    }
}
