package guru.springframework.sfgpetclinic.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

import javax.xml.bind.annotation.XmlElement;
import java.util.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "vets")
public class Vet extends Person {

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "vet_specialities", joinColumns = @JoinColumn(name = "vet_id"),
            inverseJoinColumns = @JoinColumn(name = "speciality_id"))
    private Set<Speciality> specialities = new HashSet<>();

    protected Set<Speciality> getSpecialitiesInternal(){
        if(this.specialities == null) this.specialities = new HashSet<>();
        return this.specialities;
    }

    protected void setSpecialtiesInternal(Set<Speciality> specialities){
        this.specialities = specialities;
    }

    @XmlElement
    public List<Speciality> getSpecialities(){
        List<Speciality> sortedSpecs = new ArrayList<>(getSpecialitiesInternal());
        PropertyComparator.sort(sortedSpecs,
                new MutableSortDefinition("name", true, true));
        return Collections.unmodifiableList(sortedSpecs);
    }

    public int getSpecialtiesNum(){
        return getSpecialitiesInternal().size();
    }

    public void addSpecialty(Speciality speciality){
        getSpecialitiesInternal().add(speciality);
    }
}
