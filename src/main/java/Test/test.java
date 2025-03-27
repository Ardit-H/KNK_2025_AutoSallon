package Test;

import Database.DBConnector;

import java.sql.Connection;
import java.sql.SQLException;

public class test {
    public static void main(String[] args){
        try{
            Connection connection= DBConnector.getConnection();
            if(connection.isValid(100)){
                System.out.println("DB Connected!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
