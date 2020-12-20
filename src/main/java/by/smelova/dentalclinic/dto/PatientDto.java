package by.smelova.dentalclinic.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class PatientDto {
    private Long cardID;
    private String name;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date bday;
    private String address;

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

    public void setCardID(Long cardID) {
        this.cardID = cardID;
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
