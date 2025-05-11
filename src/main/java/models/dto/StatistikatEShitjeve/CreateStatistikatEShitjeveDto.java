package models.dto.StatistikatEShitjeve;

public class CreateStatistikatEShitjeveDto {
    private int statistika_id;
    private String muaji;
    private Double fitimi;
    private Double shpenzimet;
    private Double totali_shitjeve;

    public CreateStatistikatEShitjeveDto(int statistika_id, String muaji, Double fitimi, Double shpenzimet, Double totali_shitjeve) {
        this.statistika_id = statistika_id;
        this.muaji = muaji;
        this.fitimi = fitimi;
        this.shpenzimet = shpenzimet;
        this.totali_shitjeve = totali_shitjeve;
    }

    public int getStatistika_id(){ return statistika_id; }

    public String getMuaji(){ return muaji; }

    public Double getFitimi(){ return fitimi; }

    public Double getShpenzimet(){ return shpenzimet; }

    public Double getTotali_shitjeve(){ return totali_shitjeve; }

    public void setStatistika_id(int statistika_id){ this.statistika_id = statistika_id; }

    public void setMuaji(String muaji){ this.muaji = muaji; }

    public void setFitimi(Double fitimi){ this.fitimi = fitimi; }

    public void setShpenzimet(Double shpenzimet){ this.shpenzimet = shpenzimet; }

    public void setTotali_shitjeve(Double totali_shitjeve){ this.totali_shitjeve = totali_shitjeve; }

}
