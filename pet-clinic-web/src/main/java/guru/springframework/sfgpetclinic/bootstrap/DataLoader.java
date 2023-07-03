package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {
    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

   // @Autowired
    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Loading PetTypes");
        PetType dog = new PetType();
        dog.setName("dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("cat");
        PetType savedCatPetType = petTypeService.save(cat);

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
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Henry");
        vet2.setLastName("Li");
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

    }
}
