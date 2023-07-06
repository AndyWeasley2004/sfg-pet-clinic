package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.SpecialityService;
import guru.springframework.sfgpetclinic.services.VetService;
import guru.springframework.sfgpetclinic.services.springdatajpa.VisitService;
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

   // @Autowired
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
        if(count == 0) loadData();

    }

    private void loadData() {
        System.out.println("Loading PetTypes");
        PetType dog = new PetType();
        dog.setName("dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("cat");
        PetType savedCatPetType = petTypeService.save(cat);

        System.out.println("Loading Specialties");

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology = specialityService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSurgery = specialityService.save(surgery);

        Speciality dentist = new Speciality();
        dentist.setDescription("Dentist");
        Speciality savedDentist = specialityService.save(dentist);

        System.out.println("Loading owners...");

        Owner owner1 = new Owner();
        owner1.setFirstName("Arthur");
        owner1.setLastName("Wong");
        owner1.setAddress("9450 Gilman Drive");
        owner1.setCity("La Jolla");
        owner1.setTelephone("13866180134");
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Aviva");
        owner2.setLastName("Xu");
        owner1.setAddress("9450 Gilman Drive");
        owner1.setCity("La Jolla");
        owner1.setTelephone("8583051264");
        ownerService.save(owner2);

        System.out.println("Loading Vets...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Angelina");
        vet1.setLastName("Potter");
        vet1.getSpecialities().add(savedRadiology);
        vet1.getSpecialities().add(savedSurgery);
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Henry");
        vet2.setLastName("Li");
        vet2.getSpecialities().add(savedDentist);
        vetService.save(vet2);


        System.out.println("Loading Pets");

        Pet dogOne = new Pet();
        dogOne.setPetType(savedDogPetType);
        dogOne.setOwner(owner1);
        dogOne.setName("Tom");
        dogOne.setBirthDate(LocalDate.now());
        owner1.getPets().add(dogOne);

        Pet catOne = new Pet();
        catOne.setPetType(savedCatPetType);
        catOne.setOwner(owner2);
        catOne.setName("Zero");
        catOne.setBirthDate(LocalDate.now());
        owner2.getPets().add(catOne);

        Visit catVisit = new Visit();
        catVisit.setPet(catOne);
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("Bring a new toy for the cat");
    }
}
