package models.dto.Punetoret;

public class UpdatePunetoretEmailDto {
    private int id;
    private String email;

    public UpdatePunetoretEmailDto(int id, String email){
        this.id = id;
        this.email = email;
    }

    public int getId(){
        return id;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }
}
