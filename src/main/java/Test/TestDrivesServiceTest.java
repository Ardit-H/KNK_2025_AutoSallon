package Test;

import models.dto.TestDrives.TestDrives;
import services.TestDrivesService;

public class TestDrivesServiceTest {
    public static void main(String[] args) {
        TestDrivesService testDrivesService = new TestDrivesService();

        try {
            TestDrives testDrive = testDrivesService.getById(1);
            System.out.println("Statusi: " + testDrive.getStatus());
            System.out.println("Feedback: " + testDrive.getFeedback());
            System.out.println("Kohezgjatja: " + testDrive.getDuration());
            System.out.println("-----------------------");

//            List<TestDrives> testDrivesList = testDrivesService.getAll();
//            for (TestDrives td : testDrivesList) {
//                System.out.println("ID: " + td.getId());
//                System.out.println("Statusi: " + td.getStatus());
//                System.out.println("Feedback: " + td.getFeedback());
//                System.out.println("Kohezgjatja: " + td.getDuration());
//                System.out.println("-----------------------");
//            }

//            CreateTestDrivesDto createDto = new CreateTestDrivesDto();
//            createDto.setStatus("Ne pritje");
//            createDto.setFeedback("Testimi ishte ne rregull.");
//            createDto.setDuration(30);
//            testDrivesService.create(createDto);

//            UpdateTestDrivesDto updateDto = new UpdateTestDrivesDto();
//            updateDto.setId(1);
//            updateDto.setStatus("Kompletuar");
//            updateDto.setFeedback("Testimi shkoi shumë mirë");
//            updateDto.setDuration(35);
//            testDrivesService.update(updateDto);

            testDrivesService.delete(5);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
