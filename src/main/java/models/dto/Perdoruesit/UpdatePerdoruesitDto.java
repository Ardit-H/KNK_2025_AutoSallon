package models.dto.Perdoruesit;

public class UpdatePerdoruesitDto {
    private int id;
    private String email;
    private String fjalekalimi;
    private String roli;

    public UpdatePerdoruesitDto(){}

    public int getId(){
        return id;
    }
    public String getEmail(){
        return email;
    }
    public String getFjalekalimi(){
        return fjalekalimi;
    }

    public String getRoli(){
        return roli;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setFjalekalimi(String fjalekalimi){
        this.fjalekalimi = fjalekalimi;
    }

    public void setRoli(String roli){
        this.roli = roli;
    }
}
