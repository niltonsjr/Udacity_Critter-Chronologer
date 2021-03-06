package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.model.Customer;
import com.udacity.jdnd.course3.critter.model.Pet;
import com.udacity.jdnd.course3.critter.repositories.PetRepository;
import com.udacity.jdnd.course3.critter.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PetService {

    @Autowired
    PetRepository petRepository;

    public Pet save(Pet pet) {
        Pet savedPet = petRepository.save(pet);
        Customer customer = savedPet.getOwner();
        customer.getPets().add(savedPet);
        return savedPet;
    }

    public Pet findById(Long id) {
        Optional<Pet> obj = petRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException("Pet with ID " + id + " not found."));
    }

    public List<Pet> findAll() {
        return petRepository.findAll();
    }

    public List<Pet> findAllByOwnerId(Long ownerId) {
        return petRepository.findAllByOwnerId(ownerId);
    }

}
