package com.udacity.jdnd.course3.critter.model;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDate date;

    @ManyToMany
    private List<Pet> pets;

    @ManyToMany
    private List<Employee> employees;

    @ElementCollection(targetClass = EmployeeSkill.class)
    private Set<EmployeeSkill> activities;

    public Schedule() {
    }

    public Schedule(long id, LocalDate date, List<Pet> pets, List<Employee> employees, Set<EmployeeSkill> activities) {
        this.id = id;
        this.date = date;
        this.pets = pets;
        this.employees = employees;
        this.activities = activities;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Set<EmployeeSkill> getActivities() {
        return activities;
    }

    public void setActivities(Set<EmployeeSkill> activities) {
        this.activities = activities;
    }
}
