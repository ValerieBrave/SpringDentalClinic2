package by.smelova.dentalclinic.models;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Collection;

@Entity(name = "Doctor")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctorID", nullable = false)
    private Long doctorID;
    @Column(name = "login", nullable = false, length = 50)
    private String login;
    @Column(name = "password", nullable = false, length = 50)
    private String password;
    @Column(name = "email", nullable = false, length = 100)
    private String email;
    @Column(name = "name", nullable = false, length = 255)
    private String name;
    @Column(name = "speciality",length = 255)
    private String speciality;

    @OneToMany(mappedBy = "doctor", fetch = FetchType.EAGER)
    @JsonBackReference
    private Collection<Visit> doctor_visits;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "role")
    @JsonManagedReference
    private Role role;
}
