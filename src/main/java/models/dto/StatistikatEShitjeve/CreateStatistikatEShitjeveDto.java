package models.dto.StatistikatEShitjeve;

public class CreateStatistikatEShitjeveDto {
    private String muaji;
    private Double fitimi;
    private Double shpenzimet;
    private Double totali_shitjeve;

    public CreateStatistikatEShitjeveDto( String muaji, Double fitimi, Double shpenzimet, Double totali_shitjeve) {
        this.muaji = muaji;
        this.fitimi = fitimi;
        this.shpenzimet = shpenzimet;
        this.totali_shitjeve = totali_shitjeve;
    }


    public String getMuaji(){ return muaji; }

    public Double getFitimi(){ return fitimi; }

    public Double getShpenzimet(){ return shpenzimet; }

    public Double getTotali_shitjeve(){ return totali_shitjeve; }


    public void setMuaji(String muaji){ this.muaji = muaji; }

    public void setFitimi(Double fitimi){ this.fitimi = fitimi; }

    public void setShpenzimet(Double shpenzimet){ this.shpenzimet = shpenzimet; }

    public void setTotali_shitjeve(Double totali_shitjeve){ this.totali_shitjeve = totali_shitjeve; }

}
