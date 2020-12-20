package by.smelova.dentalclinic.rest;

import by.smelova.dentalclinic.dto.PatientDto;
import by.smelova.dentalclinic.models.Patient;
import by.smelova.dentalclinic.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PatientController {
    @Autowired
    private PatientService patientService;

    @GetMapping(value = "/patients")
    public ResponseEntity GetPatients() {return ResponseEntity.ok(patientService.GetAllPatients());}
    @GetMapping(value = "/patient/byname")
    public ResponseEntity GetPatient(@RequestParam  String name) {return ResponseEntity.ok(patientService.FindPatientByName(name));}
    @GetMapping(value = "/patient/byid")
    public ResponseEntity GetPatient(@RequestParam int id) {return ResponseEntity.ok(patientService.FindPatientById((long) id));}
    @PostMapping(value = "/patients/add")
    public ResponseEntity AddPatient(@RequestBody Patient patient) {
        patientService.save(patient);
        return ResponseEntity.ok(patient);
    }

    @PutMapping(value = "/patient/edit")
    public ResponseEntity EditPatient(@RequestBody PatientDto patientDto) {
        return ResponseEntity.ok(patientService.EditPatient(patientDto));
    }

    @DeleteMapping(value = "/patient/delete/{id}")
    public  ResponseEntity DeletePatient(@PathVariable Long id) {
        Patient rc = patientService.FindPatientById(id);
        if(rc.getPatient_visits().size()==0) {
            patientService.deleteById(Math.toIntExact(id));
            return ResponseEntity.ok(rc);
        } else return ResponseEntity.status(403).build();
    }
}
