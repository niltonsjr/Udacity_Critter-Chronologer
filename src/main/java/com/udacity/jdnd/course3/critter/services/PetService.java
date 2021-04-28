package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.model.Customer;
import com.udacity.jdnd.course3.critter.model.Pet;
import com.udacity.jdnd.course3.critter.repositories.PetRepository;
import com.udacity.jdnd.course3.critter.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    @Autowired
    PetRepository petRepository;

    public Long save(Pet pet) {
        return petRepository.save(pet).getId();
    }

    public Pet findById(Long id) {
        Optional<Pet> obj = petRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException("Pet with ID " +id+ " not found."));
    }

    public List<Pet> findAll() {
        return petRepository.findAll();
    }

    public List<Pet> findAllByOwnerId(Long ownerId){
        return petRepository.findAllByOwnerId(ownerId);
    }

}
