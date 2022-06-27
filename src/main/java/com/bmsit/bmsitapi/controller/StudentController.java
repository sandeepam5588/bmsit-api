package com.bmsit.bmsitapi.controller;

import com.bmsit.bmsitapi.dto.StudentDTO;
import com.bmsit.bmsitapi.exceptions.RecordNotFoundException;
import com.bmsit.bmsitapi.service.StudentService;
import com.github.fge.jsonpatch.JsonPatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;

@RestController
@RequestMapping(value = "/bmsit/api/v1")
public class StudentController {
    @Autowired
    private StudentService studentService;


    @PostMapping(value = "/student")
    public ResponseEntity<StudentDTO> addStudent(@RequestBody StudentDTO studentDTO){
        StudentDTO savedStudent = studentService.addStudent(studentDTO);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    @GetMapping(value = "/student/{id}")
    public ResponseEntity<StudentDTO> getStudent(@PathVariable(name = "id") int id) throws RecordNotFoundException {
        StudentDTO fetchedStudent = studentService.getStudent(id);
        return new ResponseEntity<>(fetchedStudent, HttpStatus.OK);
    }

    @PutMapping(value = "/student/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@RequestBody StudentDTO studentDTO, @PathVariable(name = "id") int id) throws RecordNotFoundException {
        StudentDTO updatedStudent = studentService.updateStudent(studentDTO, id);
        return new ResponseEntity<>(updatedStudent, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/student/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable(name = "id") int id) throws RecordNotFoundException {
        studentService.deleteStudent(id);
        return new ResponseEntity<>("Record deleted successfully", HttpStatus.NO_CONTENT);
    }

    @PatchMapping(value = "/student/{id}")
    public ResponseEntity<StudentDTO> updateStudentField(@PathVariable(name = "id") int id, @RequestBody JsonPatch patch) throws RecordNotFoundException {
        StudentDTO patchedStudent = studentService.patchStudent(id, patch);
        return new ResponseEntity<>(patchedStudent, HttpStatus.OK);
    }

}
