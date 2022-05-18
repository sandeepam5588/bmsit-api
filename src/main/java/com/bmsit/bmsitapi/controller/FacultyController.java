package com.bmsit.bmsitapi.controller;

import com.bmsit.bmsitapi.model.Faculty;
import com.bmsit.bmsitapi.model.FacultyResponseVO;
import com.bmsit.bmsitapi.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/bmsit/api/v1")
public class FacultyController {

    @Autowired
    private FacultyService facultyService;

    @GetMapping(value = "/faculty", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FacultyResponseVO> getAllFaculty(){
        List<FacultyResponseVO> facultiesList = facultyService.getAllFaculty();
        return new ResponseEntity(facultiesList, HttpStatus.OK);
    }

    @GetMapping(value = "/faculty/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FacultyResponseVO> getFaculty(@PathVariable(name = "id") int id){
        Faculty faculty = facultyService.getFaculty(id);
        FacultyResponseVO facultyResponseVO = FacultyResponseVO.builder()
                .id(faculty.getId())
                .facultyName(faculty.getFacultyName())
                .facultyId(faculty.getFacultyId())
                .build();
        return new ResponseEntity<>(facultyResponseVO, HttpStatus.OK);
    }

    @PostMapping(value = "/faculty", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FacultyResponseVO> createFaculty(@RequestBody Faculty faculty){
        Faculty savedFaculty = facultyService.createFaculty(faculty);
        FacultyResponseVO facultyResponseVO = FacultyResponseVO.builder()
                .id(savedFaculty.getId())
                .facultyName(savedFaculty.getFacultyName())
                .facultyId(savedFaculty.getFacultyId())
                .build();
        return new ResponseEntity<>(facultyResponseVO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/faculty/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FacultyResponseVO> updateFaculty(@PathVariable(name = "id") int id, @RequestBody Faculty faculty){
        Faculty savedFaculty = facultyService.updateFaculty(id, faculty);
        FacultyResponseVO facultyResponseVO = FacultyResponseVO.builder()
                .id(savedFaculty.getId())
                .facultyName(savedFaculty.getFacultyName())
                .facultyId(savedFaculty.getFacultyId())
                .build();
        return new ResponseEntity<>(facultyResponseVO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/faculty/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteFaculty(int id){
        facultyService.deleteFaculty(id);
        return new ResponseEntity<>("resource deleted successfully", HttpStatus.OK);
    }
}
