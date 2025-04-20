package Test;

import models.dto.TestDrives.CreateTestDrivesDto;
import models.dto.TestDrives.UpdateTestDrivesDto;
import models.dto.TestDrives.TestDrives;
import services.TestDrivesService;

import java.util.List;

public class TestDrivesServiceTest {
    public static void main(String[] args) {
        TestDrivesService testDrivesService = new TestDrivesService();

        try {
            TestDrives testDrive = testDrivesService.getById(1);
            System.out.println("Statusi: " + testDrive.getStatusi());
            System.out.println("Feedback: " + testDrive.getFeedback());
            System.out.println("Kohezgjatja: " + testDrive.getDuration());
            System.out.println("Lokacioni: " + testDrive.getLocation());
            System.out.println("-----------------------");

//            List<TestDrives> testDrivesList = testDrivesService.getAll();
//            for (TestDrives td : testDrivesList) {
//                System.out.println("ID: " + td.getId());
//                System.out.println("Statusi: " + td.getStatusi());
//                System.out.println("Feedback: " + td.getFeedback());
//                System.out.println("Kohezgjatja: " + td.getDuration());
//                System.out.println("Lokacioni: " + td.getLocation());
//                System.out.println("-----------------------");
//            }

//            CreateTestDrivesDto createDto = new CreateTestDrivesDto();
//            createDto.setStatusi("Ne pritje");
//            createDto.setFeedback("Testimi ishte ne rregull.");
//            createDto.setDuration(30);
//            createDto.setLocation("Prishtinë");
//            testDrivesService.create(createDto);

//            UpdateTestDrivesDto updateDto = new UpdateTestDrivesDto();
//            updateDto.setId(1);
//            updateDto.setStatusi("Kompletuar");
//            updateDto.setFeedback("Testimi shkoi shumë mirë");
//            updateDto.setDuration(35);
//            updateDto.setLocation("Prizren");
//            testDrivesService.update(updateDto);

            testDrivesService.delete(5);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
