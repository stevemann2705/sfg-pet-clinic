package in.stevemann.sfgpetclinic.bootstrap;

import in.stevemann.sfgpetclinic.model.Owner;
import in.stevemann.sfgpetclinic.model.PetType;
import in.stevemann.sfgpetclinic.model.Vet;
import in.stevemann.sfgpetclinic.services.OwnerService;
import in.stevemann.sfgpetclinic.services.PetTypeService;
import in.stevemann.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        dog.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("Steve");
        owner1.setLastName("Mann");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Himanshu");
        owner2.setLastName("Jeph");

        ownerService.save(owner2);

        System.out.println("Owners loaded...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Ritobroto");
        vet1.setLastName("Basu Roy");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Parush");
        vet2.setLastName("Verma");

        vetService.save(vet2);

        System.out.println("Vets loaded...");
    }
}
