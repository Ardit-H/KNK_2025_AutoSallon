package models.dto.Veturat;

public class CreateVeturatDto {
    private String prodhuesi;
    private String modeli;
    private int vitiProdhimit;
    private String ngjyra;
    private double cmimi;
    private String gjendja;
    private int kilometrazha;
    private String tipiKarburant;

    public CreateVeturatDto(String prodhuesi, String modeli, int vitiProdhimit, String ngjyra, double cmimi, String gjendja, int kilometrazha, String tipiKarburant){
        this.prodhuesi = prodhuesi;
        this.modeli = modeli;
        this.vitiProdhimit = vitiProdhimit;
        this.ngjyra = ngjyra;
        this.cmimi = cmimi;
        this.gjendja = gjendja;
        this.kilometrazha = kilometrazha;
        this.tipiKarburant = tipiKarburant;
    }

    public String getProdhuesi() {
        return prodhuesi;
    }

    public String getModeli() {
        return modeli;
    }

    public int getVitiProdhimit() {
        return vitiProdhimit;
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

    public String getTipiKarburant() {
        return tipiKarburant;
    }

    public void setProdhuesi(String prodhuesi) {
        this.prodhuesi = prodhuesi;
    }

    public void setModeli(String modeli) {
        this.modeli = modeli;
    }

    public void setViti_prodhimit(int vitiProdhimit) {
        this.vitiProdhimit = vitiProdhimit;
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

    public void setTipiKarburant(String tipiKarburant) {
        this.tipiKarburant = tipiKarburant;
    }
}
