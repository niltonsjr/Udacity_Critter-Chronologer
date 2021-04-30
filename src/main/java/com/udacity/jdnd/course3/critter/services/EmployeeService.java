package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.model.Employee;
import com.udacity.jdnd.course3.critter.repositories.EmployeeRepository;
import com.udacity.jdnd.course3.critter.services.exceptions.ResourceNotFoundException;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee findById(Long id) {
        Optional<Employee> obj = employeeRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException("Employee with ID " +id+ " not found."));
    }

    public void setAvailability(Set<DayOfWeek> daysAvailable,long employeeId) {
        Optional<Employee> obj = employeeRepository.findById(employeeId);
        Employee employee = obj.orElseThrow(() -> new ResourceNotFoundException("Employee with ID " +employeeId+ " not found."));
        employee.setDaysAvailable(daysAvailable);
    }

    public Set<Employee> findAvailableEmployees(Set<EmployeeSkill> skills, LocalDate dayAvailable) {
        Set<Employee> availableEmployees = employeeRepository.findAllByDaysAvailable(dayAvailable.getDayOfWeek());
        Set<Employee> availableEmployeesWithSkills = availableEmployees.stream().filter(employee -> employee.getSkills().containsAll(skills)).collect(Collectors.toSet());
        return availableEmployeesWithSkills;
    }
}
