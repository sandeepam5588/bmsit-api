package com.bmsit.bmsitapi.service;

import com.bmsit.bmsitapi.dto.CourseDTO;
import com.bmsit.bmsitapi.dto.DepartmentDTO;
import com.bmsit.bmsitapi.exceptions.JsonConversionError;
import com.bmsit.bmsitapi.exceptions.RecordNotFoundException;
import com.bmsit.bmsitapi.model.Department;
import com.bmsit.bmsitapi.repository.DepartmentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ObjectMapper objectMapper;

    public DepartmentDTO addDepartment(@RequestBody DepartmentDTO departmentDTO){
        Department department = modelMapper.map(departmentDTO, Department.class);
        departmentRepository.save(department);
        return modelMapper.map(department, DepartmentDTO.class);
    }

    public DepartmentDTO getDepartment(int id) throws RecordNotFoundException {
        Department department = departmentRepository.findById(id).orElseThrow(()->new RecordNotFoundException(id));
        return modelMapper.map(department, DepartmentDTO.class);
    }

    public List<DepartmentDTO> getALlDept() {
        return departmentRepository.findAll()
                .stream()
                .map(department -> modelMapper.map(department, DepartmentDTO.class)).collect(Collectors.toList());
    }

    public DepartmentDTO updateDepartment(DepartmentDTO departmentDTO, int id) throws RecordNotFoundException {
        Department department = departmentRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
        Department updatedDept = modelMapper.map(departmentDTO, Department.class);
        updatedDept.setDepartmentId(id);
        departmentRepository.save(updatedDept);
        return modelMapper.map(updatedDept, DepartmentDTO.class);
    }

    public void deleteDepartment(int id) throws RecordNotFoundException {
        Department department = departmentRepository.findById(id).orElseThrow(()->new RecordNotFoundException(id));
        departmentRepository.deleteById(id);
    }
    public DepartmentDTO patchDept(int id, JsonPatch patch) throws RecordNotFoundException {
        Department department = departmentRepository.findById(id).orElseThrow(()-> new RecordNotFoundException(id));
        Department departmentPatched = applyPatchToDepartment(patch, department);
        departmentRepository.save(departmentPatched);
        return modelMapper.map(departmentPatched, DepartmentDTO.class);

    }
    private Department applyPatchToDepartment(JsonPatch patch, Department targetDept)  {
        JsonNode patched = null;
        try {
            patched = patch.apply(objectMapper.convertValue(targetDept, JsonNode.class));
            return objectMapper.treeToValue(patched, Department.class);
        } catch (JsonPatchException | JsonProcessingException e) {
            throw new JsonConversionError(e.getMessage());
        }
    }
}
