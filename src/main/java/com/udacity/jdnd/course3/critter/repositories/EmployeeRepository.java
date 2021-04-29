package com.udacity.jdnd.course3.critter.repositories;

import com.udacity.jdnd.course3.critter.model.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findAllBySkillsInAndDaysAvailableIn(Set<EmployeeSkill> skills, Set<DayOfWeek> daysAvailable);
}
