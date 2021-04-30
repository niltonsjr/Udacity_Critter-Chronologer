package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.model.Customer;
import com.udacity.jdnd.course3.critter.model.Employee;
import com.udacity.jdnd.course3.critter.model.Pet;
import com.udacity.jdnd.course3.critter.model.Schedule;
import com.udacity.jdnd.course3.critter.repositories.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;

    public Schedule save(Schedule schedule) {
       return scheduleRepository.save(schedule);
    }

    public List<Schedule> findAll() {
        return scheduleRepository.findAll();
    }

    public List<Schedule> findScheduleByPet(Pet pet){
        return scheduleRepository.findAllByPets(pet);
    }

    public List<Schedule> findScheduleByEmployee(Employee employee) {
        return scheduleRepository.findAllByEmployees(employee);
    }

    public List<Schedule> findScheduleByCostumer(Customer customer) {
        List<Pet> customerPetsList = customer.getPets();
        List<Schedule> scheduleList = customerPetsList.stream().map(pet -> scheduleRepository.findAllByPets(pet)).collect(Collectors.toList()).stream().flatMap(Collection::stream).collect(Collectors.toList());
        return scheduleList;
    }


}
