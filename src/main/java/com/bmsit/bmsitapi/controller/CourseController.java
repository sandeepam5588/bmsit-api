package com.bmsit.bmsitapi.controller;

import com.bmsit.bmsitapi.dto.CourseDTO;
import com.bmsit.bmsitapi.model.Course;
import com.bmsit.bmsitapi.service.CourseService;
import com.github.fge.jsonpatch.JsonPatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping(value = "/course", consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CourseDTO> addCourse(@RequestBody CourseDTO courseDTO){
        CourseDTO savedDTO = courseService.addCourse(courseDTO);
        return new ResponseEntity<>(savedDTO, HttpStatus.CREATED);
    }
    @GetMapping(value = "/course/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CourseDTO> getCourse(@PathVariable(name = "name") String name){
        CourseDTO fetchedDTO = courseService.getCourse(name);
        return new ResponseEntity<>(fetchedDTO, HttpStatus.OK);
    }
    @GetMapping(value = "/course", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CourseDTO>> getAllCourse(){
        List<CourseDTO> fetchedDTOs = courseService.getALlCourse();
        return new ResponseEntity<>(fetchedDTOs, HttpStatus.OK);
    }
    @PutMapping(value = "/course/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CourseDTO> updateCourse(@RequestBody CourseDTO courseDTO, @PathVariable(name = "id") String name){
        CourseDTO upatedCourseDTO = courseService.update(courseDTO, name);
        return new ResponseEntity<>(upatedCourseDTO, HttpStatus.OK);
    }
    @DeleteMapping(value = "/course/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable(name = "id") String name){
        courseService.deleteCourse(name);
        return new ResponseEntity<>("Record deleted successfully", HttpStatus.NO_CONTENT);
    }
    @PatchMapping(value = "/course/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CourseDTO> updateCourseField(@PathVariable(name = "name") String name, @RequestBody JsonPatch jsonPatch){
        CourseDTO patchedCourseDTO = courseService.updateCourseField(name, jsonPatch);
        return new ResponseEntity<>(patchedCourseDTO, HttpStatus.OK);
    }
}
