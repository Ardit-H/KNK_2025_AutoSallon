package services;

import models.dto.TestDrives.TestDrives;
import models.dto.TestDrives.CreateTestDrivesDto;
import models.dto.TestDrives.UpdateTestDrivesDto;
import repository.TestDrivesRepository;

import java.util.List;
import java.util.regex.Pattern;

public class TestDrivesService {
    private TestDrivesRepository testDrivesRepository;

    public TestDrivesService() {
        this.testDrivesRepository = new TestDrivesRepository();
    }

    public List<TestDrives> getAll() {
        return testDrivesRepository.getAll();
    }

    public TestDrives getById(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID duhet të jetë pozitive!");
        }
        TestDrives td = testDrivesRepository.getById(id);
        if (td == null) {
            throw new Exception("Test Drive me ID: " + id + " nuk ekziston!");
        }
        return td;
    }

    public TestDrives create(CreateTestDrivesDto dto) {
        validateCreateDto(dto);
        return testDrivesRepository.create(dto);
    }

    private void validateCreateDto(CreateTestDrivesDto dto) {
        if (isNullOrShort(dto.getStatusi(), 3)) {
            throw new IllegalArgumentException("Statusi duhet të ketë të paktën 3 karaktere!");
        }
        if (dto.getDuration() <= 0) {
            throw new IllegalArgumentException("Kohëzgjatja duhet të jetë pozitive!");
        }
        if (isNullOrShort(dto.getLocation(), 3)) {
            throw new IllegalArgumentException("Lokacioni duhet të ketë të paktën 3 karaktere!");
        }
    }

    public TestDrives update(UpdateTestDrivesDto dto) throws Exception {
        if (dto.getId() <= 0) {
            throw new Exception("ID është e pavlefshme!");
        }

        TestDrives existing = testDrivesRepository.getById(dto.getId());
        if (existing == null) {
            throw new Exception("Test Drive me ID: " + dto.getId() + " nuk ekziston.");
        }

        boolean hasChanges = false;

        if (dto.getStatusi() != null) {
            if (dto.getStatusi().trim().length() < 3) {
                throw new IllegalArgumentException("Statusi duhet të ketë të paktën 3 karaktere.");
            }
            hasChanges = true;
        }

        if (dto.getFeedback() != null) {
            if (dto.getFeedback().trim().length() < 3) {
                throw new IllegalArgumentException("Feedback-u duhet të ketë të paktën 3 karaktere.");
            }
            hasChanges = true;
        }

        if (dto.getLocation() != null) {
            if (dto.getLocation().trim().length() < 3) {
                throw new IllegalArgumentException("Lokacioni duhet të ketë të paktën 3 karaktere.");
            }
            hasChanges = true;
        }

        if (dto.getDuration() > 0) {
            hasChanges = true;
        }

        if (!hasChanges) {
            throw new IllegalArgumentException("Duhet të përditësohet të paktën një fushë.");
        }

        TestDrives updated = testDrivesRepository.update(dto);
        if (updated == null) {
            throw new Exception("Përditësimi dështoi.");
        }

        return updated;
    }

    public boolean delete(int id) throws Exception {
        if (id <= 0) {
            throw new IllegalArgumentException("ID është e pavlefshme.");
        }

        TestDrives td = testDrivesRepository.getById(id);
        if (td == null) {
            throw new Exception("Test Drive nuk ekziston.");
        }

        return testDrivesRepository.delete(id);
    }

    private boolean isNullOrShort(String s, int minLength) {
        return s == null || s.trim().length() < minLength;
    }
}
