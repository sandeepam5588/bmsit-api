package com.bmsit.bmsitapi.controller;

import com.bmsit.bmsitapi.constants.AuthoritiesConstants;
import com.bmsit.bmsitapi.exceptions.FacultyNotFoundException;
import com.bmsit.bmsitapi.model.Faculty;
import com.bmsit.bmsitapi.model.FacultyResponseVO;
import com.bmsit.bmsitapi.service.FacultyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/bmsit/api/v1")
public class FacultyController {

    @Autowired
    private FacultyService facultyService;

    /**
     * To register the JavaTime Module
     *
     * you can register like this
     *
     * ObjectMapper mapper = new ObjectMapper();
     * mapper.registerModule(new JavaTimeModule());
     *
     * or like this
     *
     * ObjectMapper objectMapper = JsonMapper.builder()
     *             .findAndAddModules()
     *             .build();
     */
    ObjectMapper objectMapper = JsonMapper.builder()
            .findAndAddModules()
            .build();

    @GetMapping(value = "/faculty", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FacultyResponseVO> getAllFaculty(){
        List<FacultyResponseVO> facultiesList = facultyService.getAllFaculty();
        return new ResponseEntity(facultiesList, HttpStatus.OK);
    }

    @GetMapping(value = "/faculty/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FacultyResponseVO> getFaculty(@PathVariable(name = "id") int id) throws FacultyNotFoundException {
        Faculty faculty = facultyService.getFaculty(id);
        FacultyResponseVO facultyResponseVO = FacultyResponseVO.builder()
                .id(faculty.getId())
                .facultyName(faculty.getFacultyName())
                .facultyId(faculty.getFacultyId())
                .build();
        return new ResponseEntity<>(facultyResponseVO, HttpStatus.OK);
    }

    //@RolesAllowed(AuthoritiesConstants.ROLE_ADMIN)
    @PostMapping(value = "/faculty", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FacultyResponseVO> createFaculty(@RequestBody @Valid Faculty faculty){
        Faculty savedFaculty = facultyService.createFaculty(faculty);
        FacultyResponseVO facultyResponseVO = FacultyResponseVO.builder()
                .id(savedFaculty.getId())
                .facultyName(savedFaculty.getFacultyName())
                .facultyId(savedFaculty.getFacultyId())
                .build();
        return new ResponseEntity<>(facultyResponseVO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/faculty/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FacultyResponseVO> updateFaculty(@PathVariable(name = "id") int id, @RequestBody @Valid Faculty faculty) throws FacultyNotFoundException {
        Faculty savedFaculty = facultyService.updateFaculty(id, faculty);
        FacultyResponseVO facultyResponseVO = FacultyResponseVO.builder()
                .id(savedFaculty.getId())
                .facultyName(savedFaculty.getFacultyName())
                .facultyId(savedFaculty.getFacultyId())
                .build();
        return new ResponseEntity<>(facultyResponseVO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/faculty/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteFaculty(@PathVariable(name = "id") int id){
        facultyService.deleteFaculty(id);
        return new ResponseEntity<>("resource deleted successfully", HttpStatus.OK);
    }

    @PatchMapping(value = "/faculty/{id}", consumes = "application/json-patch+json")
    public ResponseEntity<FacultyResponseVO> updateFacultyField(@PathVariable(name = "id") int id, @RequestBody JsonPatch patch){
        try {
            Faculty faculty = facultyService.getFaculty(id);
            Faculty facultyPatched = applyPatchToFaculty(patch, faculty);
            facultyService.updateFaculty(id, facultyPatched);
            FacultyResponseVO facultyResponseVO = FacultyResponseVO.builder()
                    .id(facultyPatched.getId())
                    .facultyName(facultyPatched.getFacultyName())
                    .facultyId(facultyPatched.getFacultyId())
                    .build();
            return new ResponseEntity<>(facultyResponseVO, HttpStatus.OK);
        }catch (JsonPatchException | JsonProcessingException | FacultyNotFoundException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private Faculty applyPatchToFaculty(JsonPatch patch, Faculty targetFaculty)throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetFaculty, JsonNode.class));
        return objectMapper.treeToValue(patched, Faculty.class);
    }
}
