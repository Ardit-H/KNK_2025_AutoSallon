package repository;

import models.dto.TestDrives.CreateTestDrivesDto;
import models.dto.TestDrives.TestDrives;
import models.dto.TestDrives.UpdateTestDrivesDto;

import java.sql.*;
import java.util.ArrayList;

public class TestDrivesRepository extends BaseRepository<TestDrives, CreateTestDrivesDto, UpdateTestDrivesDto>{
    public TestDrivesRepository(){
        super("testDrives");
    }

    public TestDrives fromResultSet(ResultSet result) throws SQLException{
        return TestDrives.getInstance(result);
    }

    public TestDrives create(CreateTestDrivesDto testDrivesDto){
        String query ="""
                INSERT INTO TESTDRIVES(statusi,feedback,duration,location)
                 VALUES(?,?,?,?,?)
                """;
        try{
            PreparedStatement pstm=this.connection.prepareStatement(
                    query,Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1,testDrivesDto.getStatusi());
            pstm.setString(2,testDrivesDto.getFeedback());
            pstm.setString(3, testDrivesDto.getLocation());
            pstm.setInt(4,testDrivesDto.getDuration());
            pstm.execute();
            ResultSet resultSet=pstm.getGeneratedKeys();
            if(resultSet.next()){
                int id=resultSet.getInt(1);
                return this.getById(id);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public TestDrives update(UpdateTestDrivesDto testDrivesDto){
        StringBuilder query=new StringBuilder("UPDATE TESTDRIVES SET ");
        ArrayList<Object> params=new ArrayList<>();

        if(testDrivesDto.getStatusi() != null){
            query.append("STATUSI = ?, ");
            params.add(testDrivesDto.getStatusi());
        }
        if(testDrivesDto.getFeedback() != null){
            query.append("FEEDBACK = ?, ");
            params.add(testDrivesDto.getFeedback());
        }
        if(testDrivesDto.getLocation() != null){
            query.append("LOKACIONI = ?, ");
            params.add(testDrivesDto.getLocation());
        }
        if(params.isEmpty()){
            return getById(testDrivesDto.getTid());
        }

        query.setLength(query.length() - 2);
        query.append(" WHERE ID = ?");
        params.add(testDrivesDto.getTid());

        try{
            PreparedStatement pstm=this.connection.prepareStatement(query.toString());
            for(int i = 0; i < params.size(); i++){
                pstm.setObject(i + 1, params.get(i));
            }
            int updated=pstm.executeUpdate();
            if(updated == 1) {
                return this.getById(testDrivesDto.getTid());
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
