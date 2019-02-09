package in.stevemann.sfgpetclinic.bootstrap;

import in.stevemann.sfgpetclinic.model.Owner;
import in.stevemann.sfgpetclinic.model.Vet;
import in.stevemann.sfgpetclinic.services.OwnerService;
import in.stevemann.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {

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
