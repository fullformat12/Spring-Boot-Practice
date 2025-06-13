package bimatlaptrinh.com.repository;

import bimatlaptrinh.com.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {}