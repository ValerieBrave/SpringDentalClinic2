package by.smelova.dentalclinic.rest;

import by.smelova.dentalclinic.config.Mapper;
import by.smelova.dentalclinic.dto.VisitDto;
import by.smelova.dentalclinic.models.Visit;
import by.smelova.dentalclinic.service.DoctorService;
import by.smelova.dentalclinic.service.PatientService;
import by.smelova.dentalclinic.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class VisitController {
    @Autowired
    private VisitService visitService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private DoctorService doctorService;

    @GetMapping(value = "/visits")
    public ResponseEntity GetVisits() {
        return ResponseEntity.ok(visitService.getAllVisits());
    }
    @GetMapping(value = "/visit")
    public ResponseEntity GetVisit(@RequestParam int id) {return ResponseEntity.ok(visitService.getById((long) id));  }

    @PostMapping(value = "/visit/add")
    public ResponseEntity AddVisit(@RequestBody VisitDto visitdto) {
        Visit visit = Mapper.map(visitdto, Visit.class);
        visit.setDoctor(doctorService.getById(visitdto.getDoctorID()));
        visit.setPatient(patientService.FindPatientById(visitdto.getCardID()));
        visitService.save(visit);
        return ResponseEntity.ok(visit);
    }

    @PutMapping(value = "/visit/edit")
    public ResponseEntity EditVisit(@RequestBody VisitDto visitDto) {
        return ResponseEntity.ok(visitService.EditVisit(visitDto));
    }

    @DeleteMapping(value = "/visit/delete/{id}")
    public ResponseEntity DeleteVisit(@PathVariable Long id) {
        Visit rc = visitService.getById(id);
        visitService.deleteById(Math.toIntExact(id));
        return ResponseEntity.ok(rc);
    }

}
