package models.dto.Veturat;

public class UpdateVeturatDto {
    private int veturaId;
    private String gjendja;

    public UpdateVeturatDto(int veturaId){
        this.veturaId = veturaId;
    }
    public int getVeturaid(){
        return veturaId;
    }

    public String getGjendja() {
        return gjendja;
    }

    public void setGjendja(String gjendja) {
        this.gjendja = gjendja;
    }
}

