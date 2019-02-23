package in.stevemann.sfgpetclinic.services.map;

import in.stevemann.sfgpetclinic.model.Owner;
import in.stevemann.sfgpetclinic.services.PetService;
import in.stevemann.sfgpetclinic.services.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OwnerMapServiceTest {

    private OwnerMapService ownerMapService;

    @Mock
    private PetService petService;

    @Mock
    private PetTypeService petTypeService;

    final Long ownerId = 1L;

    final String lastName = "Mann";

    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(petTypeService, petService);
        ownerMapService.save(Owner.builder().id(ownerId).lastName(lastName).build());
    }

    @Test
    void findAll() {
        Set<Owner> owners = ownerMapService.findAll();

        assertEquals(1, owners.size());
    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(ownerId);

        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void delete() {
        ownerMapService.delete(ownerMapService.findById(ownerId));

        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void saveExistingId() {
        final Long id = 2L;
        Owner owner2 = Owner.builder().id(id).build();
        Owner savedOwner = ownerMapService.save(owner2);

        assertEquals(id, savedOwner.getId());
    }

    @Test
    void saveNoId(){
        Owner owner = ownerMapService.save(Owner.builder().build());

        assertNotNull(owner);
        assertNotNull(owner.getId());
    }

    @Test
    void findById() {
        Owner owner = ownerMapService.findById(ownerId);
        assertEquals(ownerId, owner.getId());
    }

    @Test
    void findByLastName() {
        Owner owner = ownerMapService.findByLastName(lastName);
        assertEquals(lastName, owner.getLastName());
    }
}