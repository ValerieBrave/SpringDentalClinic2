package by.smelova.dentalclinic.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class DoctorDto {
    private Long doctorID;
    private String role;
    private String name;
    private String speciality;
    @NotBlank(message = "Enter the username")
    @Size(min = 5, max = 50, message = "Username must be between 5 and 50 symbols")
    private String login;
    @Size(min = 8, max = 20, message = "Password must be between 8 and 50 symbols")
    @NotBlank(message = "Enter the password")
    private String password;
    @Size(min = 8, max = 100, message = "Email must be between 8 and 20 symbols")
    @NotBlank(message = "Enter the email")
    private String email;

    public DoctorDto(@NotBlank(message = "Enter the username")
                     @Size(min = 5, max = 50, message = "Username must be between 5 and 50 symbols")
                             String login,
                     @Size(min = 8, max = 20, message = "Password must be between 8 and 50 symbols")
                     @NotBlank(message = "Enter the password")
                             String password,
                     @Size(min = 8, max = 100, message = "Email must be between 8 and 20 symbols")
                     @NotBlank(message = "Enter the email")
                             String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public DoctorDto() {
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

    public Long getDoctorID() {
        return doctorID;
    }

    public String getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public String getSpeciality() {
        return speciality;
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

    public void setDoctorID(Long doctorID) {
        this.doctorID = doctorID;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
}
