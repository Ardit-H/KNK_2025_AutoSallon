package models.dto.Partneret;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Partneret {

    private int id;
    private String emriKompanise;
    private String llojiPartnerit;
    private String personKontakti;
    private String email;
    private String telefoni;
    private String adresa;
    private String dataBashkepunimit;

    private Partneret(int id, String emriKompanise, String llojiPartnerit, String personKontakti,
                      String email, String telefoni, String adresa, String dataBashkepunimit) {
        this.id = id;
        this.emriKompanise = emriKompanise;
        this.llojiPartnerit = llojiPartnerit;
        this.personKontakti = personKontakti;
        this.email = email;
        this.telefoni = telefoni;
        this.adresa = adresa;
        this.dataBashkepunimit = dataBashkepunimit;
    }

    public static Partneret getInstance(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String emriKompanise = resultSet.getString("emri_kompanise");
        String llojiPartnerit = resultSet.getString("lloji_partnerit");
        String personKontakti = resultSet.getString("person_kontakti");
        String email = resultSet.getString("email");
        String telefoni = resultSet.getString("telefoni");
        String adresa = resultSet.getString("adresa");
        String dataBashkepunimit = resultSet.getString("data_bashkepunimit");

        return new Partneret(id, emriKompanise, llojiPartnerit, personKontakti,
                email, telefoni, adresa, dataBashkepunimit);
    }

    public int getId() {
        return id;
    }

    public String getEmriKompanise() {
        return emriKompanise;
    }

    public String getLlojiPartnerit() {
        return llojiPartnerit;
    }

    public String getPersonKontakti() {
        return personKontakti;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefoni() {
        return telefoni;
    }

    public String getAdresa() {
        return adresa;
    }

    public String getDataBashkepunimit() {
        return dataBashkepunimit;
    }
}
