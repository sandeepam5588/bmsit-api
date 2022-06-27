package com.bmsit.bmsitapi.service;

import com.bmsit.bmsitapi.exceptions.RecordNotFoundException;
import com.bmsit.bmsitapi.model.Faculty;
import com.bmsit.bmsitapi.model.FacultyResponseVO;
import com.bmsit.bmsitapi.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FacultyService {
    @Autowired
    private FacultyRepository facultyRepository;

    @PreAuthorize("hasRole('ROLE_VIEWER') or hasRole('ROLE_EDITOR')")
    public List<FacultyResponseVO> getAllFaculty() {
        List<FacultyResponseVO> facultyResponseVOList = new ArrayList<>();

        for (Faculty faculty : facultyRepository.findAll()) {
            FacultyResponseVO f = FacultyResponseVO.builder()
                    .id(faculty.getId())
                    .facultyName(faculty.getFacultyName())
                    .facultyId(faculty.getFacultyId())
                    .build();
            facultyResponseVOList.add(f);
        }
        return facultyResponseVOList;
    }

    @PreAuthorize("hasRole('ROLE_VIEWER') or hasRole('ROLE_EDITOR')")
    public Faculty getFaculty(int id) throws RecordNotFoundException {
        return facultyRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_EDITOR')")
    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_EDITOR')")
    public Faculty updateFaculty(int id, Faculty faculty) throws RecordNotFoundException {
        Faculty retrievedFaculty = facultyRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));

        retrievedFaculty.setFacultyName(faculty.getFacultyName());
        retrievedFaculty.setFacultyId(faculty.getFacultyId());
        retrievedFaculty.setEmail(faculty.getEmail());
        retrievedFaculty.setDateOfBirth(faculty.getDateOfBirth());
        retrievedFaculty.setQualification(faculty.getQualification());
        retrievedFaculty.setDateOfJoin(faculty.getDateOfJoin());
        retrievedFaculty.setDateOfResignation(faculty.getDateOfResignation());
        retrievedFaculty.setMobileNumber(faculty.getMobileNumber());
        retrievedFaculty.setGender(faculty.getGender());
        retrievedFaculty.setAddress(faculty.getAddress());

        return facultyRepository.save(retrievedFaculty);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteFaculty(int id) {
        facultyRepository.deleteById(id);
    }
}
