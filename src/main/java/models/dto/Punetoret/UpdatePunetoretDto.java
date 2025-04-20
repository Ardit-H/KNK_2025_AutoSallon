package models.dto.Punetoret;


public class UpdatePunetoretDto {
    private int id;
    private String email;
    private String pozita;
    private double paga;

    public UpdatePunetoretDto() {
    }

    public UpdatePunetoretDto(int id, String email, String pozita, double paga) {
        this.id = id;
        this.email = email;
        this.pozita = pozita;
        this.paga = paga;
    }

    public int getId() {
        return id;
    }

    public void setPunetorId(int id) {
        this.id = id;
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

    public double getPaga() {
        return paga;
    }

    public void setPaga(double paga) {
        this.paga = paga;
    }
}
