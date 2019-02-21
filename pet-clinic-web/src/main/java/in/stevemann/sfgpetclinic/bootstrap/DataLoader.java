package in.stevemann.sfgpetclinic.bootstrap;

import in.stevemann.sfgpetclinic.model.*;
import in.stevemann.sfgpetclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService,
                      SpecialityService specialityService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();

        if (count == 0) {
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        dog.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology = specialityService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSurgery = specialityService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        Speciality savedDentistry = specialityService.save(dentistry);

        Owner owner1 = new Owner();
        owner1.setFirstName("Steve");
        owner1.setLastName("Mann");
        owner1.setAddress("T8-002, Ansal Green Escape");
        owner1.setCity("Sonipat");
        owner1.setTelephone("1234123123");

        Pet stevesPet = new Pet();
        stevesPet.setPetType(savedDogPetType);
        stevesPet.setOwner(owner1);
        stevesPet.setBirthDate(LocalDate.now());
        stevesPet.setName("Rosco");
        owner1.getPets().add(stevesPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Himanshu");
        owner2.setLastName("Jeph");
        owner1.setAddress("T8-502, Ansal Green Escape");
        owner1.setCity("Sonipat");
        owner1.setTelephone("1234123456");

        Pet himanshusPet = new Pet();
        himanshusPet.setPetType(savedCatPetType);
        himanshusPet.setOwner(owner2);
        himanshusPet.setBirthDate(LocalDate.now());
        himanshusPet.setName("Catty");
        owner2.getPets().add(himanshusPet);

        ownerService.save(owner2);

        Visit catVisit = new Visit();
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("Sneezy Kitty");
        catVisit.setPet(himanshusPet);

        visitService.save(catVisit);

        System.out.println("Owners loaded...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Ritobroto");
        vet1.setLastName("Basu Roy");
        vet1.getSpecialities().add(savedRadiology);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Parush");
        vet2.setLastName("Verma");
        vet2.getSpecialities().add(savedSurgery);

        vetService.save(vet2);

        System.out.println("Vets loaded...");
    }
}
