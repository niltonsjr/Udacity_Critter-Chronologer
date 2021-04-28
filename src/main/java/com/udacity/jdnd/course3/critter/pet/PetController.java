package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.model.Customer;
import com.udacity.jdnd.course3.critter.model.Pet;
import com.udacity.jdnd.course3.critter.services.CustomerService;
import com.udacity.jdnd.course3.critter.services.PetService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    PetService petService;

    @Autowired
    CustomerService customerService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Pet pet = convertPetDTOToPet(petDTO);
        petService.save(pet);
        return convertPetToPetDTO(pet);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        Pet  pet = petService.findById(petId);
        return convertPetToPetDTO(pet);
    }

    @GetMapping
    public List<PetDTO> getPets(){
        List<Pet> petList = petService.findAll();
        return convertPetListToPetDTOList(petList);
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        Customer owner = customerService.findById(ownerId);
        List<Pet> petList = petService.findByOwner(owner);
        return convertPetListToPetDTOList(petList);
    }

    private PetDTO convertPetToPetDTO(Pet pet) {
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet, petDTO);
        petDTO.setOwnerId(pet.getOwner().getId());
        return petDTO;
    }

    private Pet convertPetDTOToPet(PetDTO petDTO) {
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO, pet);
        Customer owner = customerService.findById(petDTO.getOwnerId());
        pet.setOwner(owner);
        return pet;
    }

    private List<PetDTO> convertPetListToPetDTOList(List<Pet> petList) {
        return petList.stream().map(pet -> convertPetToPetDTO(pet)).collect(Collectors.toList());
    }
}
