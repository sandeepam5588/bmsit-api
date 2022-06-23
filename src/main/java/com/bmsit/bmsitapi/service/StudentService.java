package com.bmsit.bmsitapi.service;

import com.bmsit.bmsitapi.dto.StudentDTO;
import com.bmsit.bmsitapi.exceptions.JsonConversionError;
import com.bmsit.bmsitapi.exceptions.StudentRecordNotFoundException;
import com.bmsit.bmsitapi.model.Student;
import com.bmsit.bmsitapi.repository.StudentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ObjectMapper objectMapper;

    public StudentDTO addStudent(StudentDTO studentDTO) {
        Student student = modelMapper.map(studentDTO, Student.class);
        Student savedStudent = studentRepository.save(student);
        return modelMapper.map(savedStudent, StudentDTO.class);
    }

    public StudentDTO getStudent(int id) {
        Student fetchedStudent = studentRepository.findById(id).orElseThrow(()-> new StudentRecordNotFoundException(id));
        return modelMapper.map(fetchedStudent, StudentDTO.class);
    }

    public StudentDTO updateStudent(StudentDTO studentDTO, int id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentRecordNotFoundException(id));
        Student updatedStudent = modelMapper.map(studentDTO, Student.class);
        updatedStudent.setId(id);
        studentRepository.save(updatedStudent);
        return modelMapper.map(updatedStudent, StudentDTO.class);
    }

    public void deleteStudent(int id) {
        Student student = studentRepository.findById(id).orElseThrow(()->new StudentRecordNotFoundException(id));
        studentRepository.deleteById(id);
    }

    public StudentDTO patchStudent(int id, JsonPatch patch)  {
            Student student = studentRepository.findById(id).orElseThrow(()-> new StudentRecordNotFoundException(id));
            Student studentPatched = applyPatchToStudent(patch, student);
            studentRepository.save(studentPatched);
            return modelMapper.map(studentPatched, StudentDTO.class);

    }
    private Student applyPatchToStudent(JsonPatch patch, Student targetStudent)  {
        JsonNode patched = null;
        try {
            patched = patch.apply(objectMapper.convertValue(targetStudent, JsonNode.class));
            return objectMapper.treeToValue(patched, Student.class);
        } catch (JsonPatchException | JsonProcessingException e) {
            throw new JsonConversionError(e.getMessage());
        }
    }
}
