package models.dto.Punetoret;

public class UpdatePunetoretDto {
    private int id;
    private String emri;
    private String mbiemri;
    private String email;
    private String pozita;
    private Double paga;  // Objekt për të lejuar null

    public UpdatePunetoretDto() {}

    public UpdatePunetoretDto(int id, String emri, String mbiemri, String email, String pozita, Double paga) {
        this.id = id;
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.email = email;
        this.pozita = pozita;
        this.paga = paga;
    }

    // Getters & Setters

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getEmri() {
        return emri;
    }
    public void setEmri(String emri) {
        this.emri = emri;
    }

    public String getMbiemri() {
        return mbiemri;
    }
    public void setMbiemri(String mbiemri) {
        this.mbiemri = mbiemri;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPozita() {
        return pozita;
    }
    public void setPozita(String pozita) {
        this.pozita = pozita;
    }

    public Double getPaga() {
        return paga;
    }
    public void setPaga(Double paga) {
        this.paga = paga;
    }
}
