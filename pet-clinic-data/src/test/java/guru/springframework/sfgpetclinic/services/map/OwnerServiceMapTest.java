package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OwnerServiceMapTest {

    OwnerServiceMap ownerServiceMap;
    Long ownerID;
    String lastName = "Weasley";

    @BeforeEach
    void setUp() {
        ownerID = 1L;
        ownerServiceMap = new OwnerServiceMap(new PetTypeServiceMap(), new PetServiceMap());
        ownerServiceMap.save(Owner.builder().id(ownerID).lastName(lastName).build()); // build an owner object
    }

    @Test
    void findAll() {
        Set<Owner> set = ownerServiceMap.findAll();
        assertEquals(1, set.size());
    }

    @Test
    void findById() {
        Owner owner = ownerServiceMap.findById(ownerID);
        assertEquals(ownerID, owner.getId());
    }

    @Test
    void saveExistedID() {
        Long id = 2L;
        Owner owner1 = Owner.builder().id(id).build();
        Owner saved = ownerServiceMap.save(owner1);
        assertEquals(id, saved.getId());
    }

    @Test
    void saveNoID() {
        Owner test = Owner.builder().build();
        ownerServiceMap.save(test);
        assertNotNull(test);
        assertNotNull(test.getId());
    }

    @Test
    void delete() {
        ownerServiceMap.delete(ownerServiceMap.findById(ownerID));

        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void deleteById() {
        ownerServiceMap.deleteById(ownerID);
        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void findByLastName() {
        Owner owner2 = ownerServiceMap.findByLastName(lastName);
        assertNotNull(owner2);
        assertEquals(lastName, owner2.getLastName());
        assertEquals(1L, owner2.getId());
    }
}