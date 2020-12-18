package by.smelova.dentalclinic.models;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity(name = "Patient")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cardID", nullable = false)
    private Long cardID;
    @Column(name = "name", nullable = false, length = 255)
    private String name;
    @Column(name = "bday")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date bday;
    @Column(name = "address", nullable = false, length = 255)
    private String address;

    @OneToMany(mappedBy = "patient", fetch = FetchType.EAGER)
    @JsonBackReference
    private Collection<Visit> patient_visits;

    public Patient() {
    }

    public Long getCardID() {
        return cardID;
    }

    public String getName() {
        return name;
    }

    public Date getBday() {
        return bday;
    }

    public String getAddress() {
        return address;
    }

    public Collection<Visit> getPatient_visits() {
        return patient_visits;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBday(Date bday) {
        this.bday = bday;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
