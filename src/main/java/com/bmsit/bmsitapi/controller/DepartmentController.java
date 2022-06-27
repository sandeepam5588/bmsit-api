package com.bmsit.bmsitapi.controller;

import com.bmsit.bmsitapi.dto.DepartmentDTO;
import com.bmsit.bmsitapi.dto.StudentDTO;
import com.bmsit.bmsitapi.exceptions.RecordNotFoundException;
import com.bmsit.bmsitapi.service.DepartmentService;
import com.github.fge.jsonpatch.JsonPatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @PostMapping(value = "/dept", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DepartmentDTO> addDepartment(@RequestBody DepartmentDTO departmentDTO){
        DepartmentDTO savedDeptDTO = departmentService.addDepartment(departmentDTO);
        return new ResponseEntity<DepartmentDTO>(savedDeptDTO, HttpStatus.CREATED);
    }
    @GetMapping(value = "/dept/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DepartmentDTO> getDepartment(@PathVariable(name = "id") int id) throws RecordNotFoundException {
        DepartmentDTO fetchedDeptDTO = departmentService.getDepartment(id);
        return new ResponseEntity<DepartmentDTO>(fetchedDeptDTO, HttpStatus.OK);
    }
    @PutMapping(value = "/dept/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DepartmentDTO> updateDepartment(@RequestBody DepartmentDTO departmentDTO, @PathVariable(name = "id") int id) throws RecordNotFoundException {
        DepartmentDTO updatedDeptDTO = departmentService.updateDepartment(departmentDTO, id);
        return new ResponseEntity<DepartmentDTO>(updatedDeptDTO, HttpStatus.OK);
    }
    @DeleteMapping(value = "/dept/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable(name = "id") int id) throws RecordNotFoundException {
        departmentService.deleteDepartment(id);
        return new ResponseEntity<>("resource deleted successfully", HttpStatus.NO_CONTENT);
    }
    @PatchMapping(value = "/dept/{id}")
    public ResponseEntity<DepartmentDTO> updateDeptField(@PathVariable(name = "id") int id, @RequestBody JsonPatch patch) throws RecordNotFoundException {
        DepartmentDTO patchedDepartment = departmentService.patchDept(id, patch);
        return new ResponseEntity<>(patchedDepartment, HttpStatus.OK);
    }

}
