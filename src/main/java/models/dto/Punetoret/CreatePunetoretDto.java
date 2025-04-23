package models.dto.Punetoret;

public class CreatePunetoretDto {
    private String emri;
    private String mbiemri;
    private String pozita;
    private String telefoni;
    private String email;
    private double paga;
    private String dataPunesimit;



    public CreatePunetoretDto (String emri,String mbiemri,String pozita, String telefoni, String email, double paga, String dataPunesimit){
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.pozita = pozita;
        this.telefoni = telefoni;
        this.email = email;
        this.paga = paga;
        this.dataPunesimit = dataPunesimit;
    }


    public String getEmri(){
        return emri;
    }


    public String getMbiemri(){
        return mbiemri;
    }

    public String getPozita(){
        return pozita;
    }

    public String getTelefoni(){
        return telefoni;
    }
    public String getEmail(){
        return email;
    }

    public double getPaga(){
        return paga;
    }

    public String getDataPunesimit(){
        return dataPunesimit;
    }

    public void setEmri(){
        this.emri = emri;
    }

    public void setMbiemri(){
        this.mbiemri = mbiemri;
    }

    public void setPozita(){
        this.pozita = pozita;
    }

    public void setTelefoni(){
        this.telefoni = telefoni;
    }

    public void setEmail(){
        this.email = email;
    }

    public void setPaga(){
        this.paga = paga;
    }

    public void setData_punesimit(){
        this.dataPunesimit = dataPunesimit;
    }
}


