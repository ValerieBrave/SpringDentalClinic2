package by.smelova.dentalclinic.rest;

import by.smelova.dentalclinic.dto.PatientDto;
import by.smelova.dentalclinic.models.Patient;
import by.smelova.dentalclinic.service.PatientService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/patient/")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @GetMapping(value = "/all")
    public ResponseEntity GetPatients() {return ResponseEntity.ok(patientService.GetAllPatients());}
    @GetMapping(value = "/byname")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404")})
    public ResponseEntity GetPatient(@RequestParam  String name) {
        List<Patient> rc = patientService.FindPatientByName(name);
        if(rc.size() != 0) return ResponseEntity.ok(rc);
        else return ResponseEntity.status(404).build();
    }
    @GetMapping(value = "/byid")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404")})
    public ResponseEntity GetPatient(@RequestParam int id) {
        Patient rc = patientService.FindPatientById((long) id);
        if(rc != null)  return ResponseEntity.ok(rc);
        else return ResponseEntity.status(404).build();
    }
    @PostMapping(value = "/add")
    public ResponseEntity AddPatient(@RequestBody Patient patient) {
        patientService.save(patient);
        return ResponseEntity.ok(patient);
    }

    @PutMapping(value = "/edit")
    public ResponseEntity EditPatient(@RequestBody PatientDto patientDto) {
        return ResponseEntity.ok(patientService.EditPatient(patientDto));
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "403"),
            @ApiResponse(responseCode = "404")})
    @DeleteMapping(value = "/delete/{id}")
    public  ResponseEntity DeletePatient(@PathVariable Long id) {
        Patient rc = patientService.FindPatientById(id);
        if(rc != null) {
            if(rc.getPatient_visits().size()==0) {
                patientService.deleteById(Math.toIntExact(id));
                return ResponseEntity.ok(rc);
            } else return ResponseEntity.status(403).build();
        } else return ResponseEntity.status(404).build();

    }
}
