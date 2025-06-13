package bimatlaptrinh.com.service;

import bimatlaptrinh.com.dto.EmployeeDTO;
import bimatlaptrinh.com.entity.Employee;
import bimatlaptrinh.com.exception.ResourceNotFoundException;
import bimatlaptrinh.com.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository repository;

    private EmployeeDTO toDTO(Employee e) {
        return new EmployeeDTO(e.getName(), e.getEmail(), e.getPosition(), e.getSalary());
    }

    private Employee toEntity(EmployeeDTO dto) {
        return new Employee(null, dto.name(), dto.email(), dto.position(), dto.salary());
    }

    public List<EmployeeDTO> getAll() {
        return repository.findAll().stream().map(this::toDTO).toList();
    }

    public EmployeeDTO getById(Long id) {
        return repository.findById(id).map(this::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Not found employee id = " + id));
    }

    public EmployeeDTO create(EmployeeDTO dto) {
        return toDTO(repository.save(toEntity(dto)));
    }

    public EmployeeDTO update(Long id, EmployeeDTO dto) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Not found employee id = " + id);
        }
        Employee updated = new Employee(id, dto.name(), dto.email(), dto.position(), dto.salary());
        return toDTO(repository.save(updated));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Not found");
        }
        repository.deleteById(id);
    }
}