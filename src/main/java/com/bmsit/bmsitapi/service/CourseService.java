package com.bmsit.bmsitapi.service;

import com.bmsit.bmsitapi.dto.CourseDTO;
import com.bmsit.bmsitapi.exceptions.CourseRecordNotFoundException;
import com.bmsit.bmsitapi.exceptions.JsonConversionError;
import com.bmsit.bmsitapi.model.Course;
import com.bmsit.bmsitapi.model.Student;
import com.bmsit.bmsitapi.repository.CourseRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ObjectMapper objectMapper;

    public CourseDTO addCourse(CourseDTO courseDTO) {
        Course course = modelMapper.map(courseDTO, Course.class);
        courseRepository.save(course);
        return modelMapper.map(course, CourseDTO.class);
    }

    public CourseDTO getCourse(String name) {
        Course course = courseRepository.findByCourseName(name).orElseThrow(()-> new CourseRecordNotFoundException(name));
        return modelMapper.map(course, CourseDTO.class);
    }

    public List<CourseDTO> getALlCourse() {
        return courseRepository.findAll()
                .stream()
                .map(course -> modelMapper.map(course, CourseDTO.class)).collect(Collectors.toList());
    }

    public CourseDTO update(CourseDTO courseDTO, String name) {
        Course course = courseRepository.findByCourseName(name).orElseThrow(()-> new CourseRecordNotFoundException(name));
        Course updatedCourse = modelMapper.map(courseDTO, Course.class);
        Course savedCourse = courseRepository.save(updatedCourse);
        return modelMapper.map(savedCourse, CourseDTO.class);
    }

    public void deleteCourse(String name) {
        Course course = courseRepository.findByCourseName(name).orElseThrow(()-> new CourseRecordNotFoundException(name));
        courseRepository.deleteByCourseName(name);
    }

    public CourseDTO updateCourseField(String name, JsonPatch jsonPatch) {
        Course course = courseRepository.findByCourseName(name).orElseThrow(()-> new CourseRecordNotFoundException(name));
        Course patchedCourse = applyPatchToCourse(jsonPatch, course);
        Course savedCourse = courseRepository.save(patchedCourse);
        return modelMapper.map(savedCourse, CourseDTO.class);
    }

    private Course applyPatchToCourse(JsonPatch patch, Course targetCourse){
        JsonNode patched = null;
        try{
            patched = patch.apply(objectMapper.convertValue(targetCourse, JsonNode.class));
            return objectMapper.treeToValue(patched, Course.class);
        } catch (JsonPatchException | JsonProcessingException e) {
            throw new JsonConversionError(e.getMessage());
        }
    }
}
