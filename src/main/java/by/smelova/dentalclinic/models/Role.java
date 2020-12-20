package by.smelova.dentalclinic.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Collection;

@Entity(name = "Role")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @Column(name = "role", nullable = false)
    private String role;
    @OneToMany(mappedBy = "role", fetch = FetchType.EAGER)
    @JsonManagedReference
    private Collection<Doctor> doctors;


}
