package in.stevemann.sfgpetclinic.services.springdatajpa;

import in.stevemann.sfgpetclinic.model.Owner;
import in.stevemann.sfgpetclinic.repositories.OwnerRepository;
import in.stevemann.sfgpetclinic.repositories.PetRepository;
import in.stevemann.sfgpetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

    @Mock
    private OwnerRepository ownerRepository;

    @Mock
    private PetTypeRepository petTypeRepository;

    @Mock
    private PetRepository petRepository;

    @InjectMocks
    private OwnerSDJpaService service;

    final Long id = 1L;
    Owner returnOwner;
    public static final String LAST_NAME = "Mann";

    @BeforeEach
    void setUp() {
        returnOwner = Owner.builder().id(id).lastName(LAST_NAME).build();
    }

    @Test
    void findByLastName() {
        when(ownerRepository.findByLastName(any())).thenReturn(returnOwner);
        Owner owner = service.findByLastName(LAST_NAME);

        assertEquals(LAST_NAME, owner.getLastName());
    }

    @Test
    void findAll() {
        Set<Owner> owners = new HashSet<>();
        owners.add(Owner.builder().id(1L).build());
        owners.add(Owner.builder().id(2L).build());

        when(ownerRepository.findAll()).thenReturn(owners);

        Set<Owner> ownerSet = service.findAll();

        assertNotNull(ownerSet);
        assertEquals(owners.size(), ownerSet.size());
    }

    @Test
    void findById() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(returnOwner));

        Owner owner = service.findById(id);

        assertNotNull(owner);
        assertEquals(id, owner.getId());
    }

    @Test
    void findByIdNotFound() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());

        Owner owner = service.findById(1L);

        assertNull(owner);
    }

    @Test
    void save() {
        Owner ownerToSave = Owner.builder().id(id).lastName(LAST_NAME).build();

        when(ownerRepository.save(any())).thenReturn(returnOwner);

        Owner owner = service.save(ownerToSave);

        assertNotNull(owner);
    }

    @Test
    void delete() {
        service.delete(returnOwner);

        verify(ownerRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(1L);

        verify(ownerRepository, times(1)).deleteById(anyLong());
    }
}