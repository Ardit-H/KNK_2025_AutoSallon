package Test;

import Database.DBConnector;
import models.dto.TestDrives.TestDrives;
import repository.TestDrivesRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDrivesRepositoryTest {
    public static void main(String[] args){
        Connection connection = DBConnector.getConnection();
        try {
            Statement stm=connection.createStatement();
            String query="SELECT * FROM TESTDRIVES ORDER BY ID DESC LIMIT 1";
            ResultSet result=stm.executeQuery(query);
            if(result.next()){
                TestDrives testdrives=TestDrives.getInstance(result);
                 System.out.println("id: "+testdrives.getId());
                System.out.println("Statusi: "+testdrives.getStatusi());
                System.out.println("Feedback: "+testdrives.getFeedback());
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        TestDrivesRepository testDrivesRepository= new TestDrivesRepository();
        TestDrives testDrives = testDrivesRepository.getById(12);
        if(testDrives!=null){
            System.out.println("id "+testDrives.getId());
        }
//        testDrivesRepository.delete(3);
    }
}
