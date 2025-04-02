package models;
import java.sql.ResultSet;
import java.sql.SQLException;
public class Punetoret {
    private int punetor_id;
    private String emri;
    private String mbiemri;
    private String pozita;
    private String telefoni;
    private String email;
    private double paga;
    private String data_punesimit;

    private Punetoret(int punetor_id, String emri, String mbiemri, String pozita, String telefoni,String email, double paga, String data_punesimit){
        this.punetor_id = punetor_id;
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.pozita = pozita;
        this.telefoni = telefoni;
        this.email = email;
        this.paga = paga;
        this.data_punesimit = data_punesimit;
    }

    public static Punetoret getInstance(ResultSet resultSet) throws SQLException{
        int punetor_id = resultSet.getInt("punetor_id");
        String emri = resultSet.getString("emri");
        String mbiemri = resultSet.getString("mbiemri");
        String pozita = resultSet.getString("pozita");
        String telefoni = resultSet.getString("telefoni");
        String email = resultSet.getString("email");
        double paga = resultSet.getDouble("paga");
        String data_punesimit = resultSet.getString("data_punesimit");
        return new Punetoret(punetor_id, emri, mbiemri, pozita, telefoni, email, paga, data_punesimit);

    }

    public int getPunetor_id(){
        return punetor_id;
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
        return data_punesimit;
    }
}
