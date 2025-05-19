package repository;

import models.dto.TestDrives.CreateTestDrivesDto;
import models.dto.TestDrives.TestDrives;
import models.dto.TestDrives.UpdateTestDrivesDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestDrivesRepository extends BaseRepository<TestDrives, CreateTestDrivesDto, UpdateTestDrivesDto>{
    public TestDrivesRepository(){
        super("testDrives");
    }

    public TestDrives fromResultSet(ResultSet result) throws SQLException{
        return TestDrives.getInstance(result);
    }

    public TestDrives create(CreateTestDrivesDto testDrivesDto){
        String query ="""
                INSERT INTO TESTDRIVES(kid,vid,status,feedback,duration)
                 VALUES(?,?,?,?,?)
                """;
        try{
            PreparedStatement pstm=this.connection.prepareStatement(
                    query,Statement.RETURN_GENERATED_KEYS);
            pstm.setInt(1,testDrivesDto.getKid());
            pstm.setInt(2,testDrivesDto.getVid());
            pstm.setString(3,testDrivesDto.getStatus());
            pstm.setString(4,testDrivesDto.getFeedback());
            pstm.setInt(5,testDrivesDto.getDuration());
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

        if(testDrivesDto.getStatus() != null){
            query.append("STATUS = ?, ");
            params.add(testDrivesDto.getStatus());
        }
        if(testDrivesDto.getFeedback() != null){
            query.append("FEEDBACK = ?, ");
            params.add(testDrivesDto.getFeedback());
        }

        if (testDrivesDto.getDuration() > 0) {
            query.append("DURATION = ?, ");
            params.add(testDrivesDto.getDuration());
        }

        if(params.isEmpty()){
            return getById(testDrivesDto.getId());
        }

        query.setLength(query.length() - 2);
        query.append(" WHERE ID = ?");
        params.add(testDrivesDto.getId());

        try{
            PreparedStatement pstm=this.connection.prepareStatement(query.toString());
            for(int i = 0; i < params.size(); i++){
                pstm.setObject(i + 1, params.get(i));
            }
            int updated=pstm.executeUpdate();
            if(updated == 1) {
                return this.getById(testDrivesDto.getId());
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<TestDrives> kerkoSipasStatusit(String statusi) {
        List<TestDrives> rezultatet = new ArrayList<>();
        String sql = "SELECT * FROM testdrives WHERE LOWER(status) LIKE ?";

        try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + statusi.toLowerCase() + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                TestDrives td = new TestDrives(
                        rs.getInt("id"),
                        rs.getInt("kid"),
                        rs.getInt("vid"),
                        rs.getString("status"),
                        rs.getString("feedback"),
                        rs.getInt("duration")
                );
                rezultatet.add(td);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rezultatet;
    }

}
