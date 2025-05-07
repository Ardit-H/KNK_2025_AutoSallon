package models.dto.Perdoruesit;

public class CreatePerdoruesitDto {
    private String emri;
    private String email;
    private String fjalekalimi;


    public CreatePerdoruesitDto(String emri, String email, String fjalekalimi){
        this.emri = emri;
        this.email = email;
        this.fjalekalimi = fjalekalimi;
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

}
