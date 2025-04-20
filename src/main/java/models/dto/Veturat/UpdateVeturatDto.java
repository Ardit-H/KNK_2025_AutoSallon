package models.dto.Veturat;

public class UpdateVeturatDto {
    private int id;
    private String gjendja;

    public UpdateVeturatDto(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }

    public String getGjendja() {
        return gjendja;
    }

    public void setGjendja(String gjendja) {
        this.gjendja = gjendja;
    }
}

