package models.dto.Perdoruesit;

public class CreatePerdoruesitDto {
    private String emri;
    private String email;
    private String fjalekalimi;
    private String roli;

    public CreatePerdoruesitDto(String emri, String email, String fjalekalimi, String roli){
        this.emri = emri;
        this.email = email;
        this.fjalekalimi = fjalekalimi;
        this.roli = roli;
    }

    public void setEmri(String emri){
        this.emri = emri;
    }

    public String getEmri(){
        return emri;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return email;
    }

    public void setFjalekalimi(String fjalekalimi){
        this.fjalekalimi = fjalekalimi;
    }

    public String getFjalekalimi(){
        return fjalekalimi;
    }

    public  void setRoli(String roli){
        this.roli = roli;
    }
    public String getRoli(){
        return roli;
    }
}
