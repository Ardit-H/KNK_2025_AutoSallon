package models.dto.Veturat;

public class UpdateVeturatDto {
    private int id;
    private String gjendja;
    private String ngjyra;
    private int kilometrazha;

    public UpdateVeturatDto(int selectedId){
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

    public String getNgjyra() {
        return ngjyra;
    }

    public void setNgjyra(String ngjyra) {
        this.ngjyra = ngjyra;
    }

    public int getKilometrazha() {
        return kilometrazha;
    }

    public void setKilometrazha(int kilometrazha) {
        this.kilometrazha = kilometrazha;
    }
}

