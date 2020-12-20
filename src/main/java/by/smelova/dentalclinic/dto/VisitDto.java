package by.smelova.dentalclinic.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class VisitDto {
    private Long visitID;
    private Long doctorID;
    private Long cardID;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date date;
    private String complains;
    private String diagnosis;
    private String treatment;

    public Long getVisitID() {
        return visitID;
    }

    public Long getDoctorID() {
        return doctorID;
    }

    public Long getCardID() {
        return cardID;
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

    public void setVisitID(Long visitID) {
        this.visitID = visitID;
    }

    public void setDoctorID(Long doctorID) {
        this.doctorID = doctorID;
    }

    public void setCardID(Long cardID) {
        this.cardID = cardID;
    }

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
}
