package by.smelova.dentalclinic.models;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Collection;

@Entity(name = "Doctor")
public class Doctor extends BasicModel {
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
    @JsonManagedReference(value =  "visit->doctor")
    private Collection<Visit> doctor_visits;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "role")
    @JsonBackReference
    private Role role;

    public Long getDoctorID() {
        return doctorID;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getSpeciality() {
        return speciality;
    }

    public Collection<Visit> getDoctor_visits() {
        return doctor_visits;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public void setDoctor_visits(Collection<Visit> doctor_visits) {
        this.doctor_visits = doctor_visits;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
