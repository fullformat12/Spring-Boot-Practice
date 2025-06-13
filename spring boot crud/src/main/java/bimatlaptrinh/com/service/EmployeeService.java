package bimatlaptrinh.com.service;

import bimatlaptrinh.com.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDTO> getAll();
    EmployeeDTO getById(Long id);
    EmployeeDTO create(EmployeeDTO dto);
    EmployeeDTO update(Long id, EmployeeDTO dto);
    void delete(Long id);
}