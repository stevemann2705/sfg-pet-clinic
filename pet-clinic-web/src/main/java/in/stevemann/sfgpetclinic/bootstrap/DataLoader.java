package in.stevemann.sfgpetclinic.bootstrap;

import in.stevemann.sfgpetclinic.model.Owner;
import in.stevemann.sfgpetclinic.model.Vet;
import in.stevemann.sfgpetclinic.services.OwnerService;
import in.stevemann.sfgpetclinic.services.VetService;
import in.stevemann.sfgpetclinic.services.map.OwnerServiceMap;
import in.stevemann.sfgpetclinic.services.map.VetServiceMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader() {
        ownerService = new OwnerServiceMap();
        vetService = new VetServiceMap();
    }

    @Override
    public void run(String... args) throws Exception {

        Owner owner1 = new Owner();
        owner1.setFirstName("Steve");
        owner1.setLastName("Mann");
        owner1.setId(1L);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Himanshu");
        owner2.setLastName("Jeph");
        owner2.setId(2L);

        ownerService.save(owner2);

        System.out.println("Owners loaded...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Ritobroto");
        vet1.setLastName("Basu Roy");
        vet1.setId(1L);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Parush");
        vet2.setLastName("Verma");
        vet2.setId(2L);

        vetService.save(vet2);

        System.out.println("Vets loaded...");
    }
}
