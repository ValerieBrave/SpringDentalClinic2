package by.smelova.dentalclinic.models;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "Visit")
public class Visit extends BasicModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "visitID", nullable = false)
    private Long visitID;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "doctorID")
    @JsonBackReference(value = "visit->doctor")
    private Doctor doctor;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "cardID")
    @JsonBackReference(value = "visit->patient")
    private Patient patient;

    @Column(name = "date", nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date date;
    @Column(name = "complains", length = 500)
    private String complains;
    @Column(name = "diagnosis", length = 500)
    private String diagnosis;
    @Column(name = "treatment", length = 500)
    private String treatment;


    public Visit() {
    }



    public Long getVisitID() {
        return visitID;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public Date getDate() {
        return date;
    }

    public String getComplains() {
        return complains;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }


    public void setVisitID(Long visitID) { this.visitID = visitID; }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setComplains(String complains) {
        this.complains = complains;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
