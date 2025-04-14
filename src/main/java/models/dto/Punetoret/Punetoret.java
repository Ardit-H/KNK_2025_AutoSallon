package models.dto.Punetoret;
import java.sql.ResultSet;
import java.sql.SQLException;
public class Punetoret {
    private int punetorId;
    private String emri;
    private String mbiemri;
    private String pozita;
    private String telefoni;
    private String email;
    private double paga;
    private String dataPunesimit;

    private Punetoret(int punetorId, String emri, String mbiemri, String pozita, String telefoni,String email, double paga, String dataPunesimit){
        this.punetorId = punetorId;
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.pozita = pozita;
        this.telefoni = telefoni;
        this.email = email;
        this.paga = paga;
        this.dataPunesimit = dataPunesimit;
    }

    public static Punetoret getInstance(ResultSet resultSet) throws SQLException{
        int punetorId = resultSet.getInt("punetor_id");
        String emri = resultSet.getString("emri");
        String mbiemri = resultSet.getString("mbiemri");
        String pozita = resultSet.getString("pozita");
        String telefoni = resultSet.getString("telefoni");
        String email = resultSet.getString("email");
        double paga = resultSet.getDouble("paga");
        String dataPunesimit = resultSet.getString("data_punesimit");
        return new Punetoret(punetorId, emri, mbiemri, pozita, telefoni, email, paga, dataPunesimit);

    }

    public int getPunetor_id(){
        return punetorId;
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

    public String getData_punesimit(){
        return dataPunesimit;
    }
}
