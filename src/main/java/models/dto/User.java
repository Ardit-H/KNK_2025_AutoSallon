package models.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private int id;
    private String emri;
    private String email;
    private int age;
    private User(int id,String emri,String email,int age){
        this.id=id;
        this.emri=emri;
        this.email=email;
        this.age=age;
    }
public static User getInstance(ResultSet resultSet)throws SQLException{
        int id=resultSet.getInt("id");
        String emri=resultSet.getString("name");
        String email=resultSet.getString("email");
        int age=resultSet.getInt("age");
        return new User(id,emri,emri,age);
}

    public int getId() {
        return id;
    }

    public String getEmri() {
        return emri;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }
}
