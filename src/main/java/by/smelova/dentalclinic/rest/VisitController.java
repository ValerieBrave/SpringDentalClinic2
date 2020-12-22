package by.smelova.dentalclinic.rest;

import by.smelova.dentalclinic.config.Mapper;
import by.smelova.dentalclinic.dto.VisitDto;
import by.smelova.dentalclinic.models.Visit;
import by.smelova.dentalclinic.service.DoctorService;
import by.smelova.dentalclinic.service.PatientService;
import by.smelova.dentalclinic.service.VisitService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api/user/visit")
public class VisitController {
    @Autowired
    private VisitService visitService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private DoctorService doctorService;

    @GetMapping(value = "/all")
    public ResponseEntity GetVisits() {
        return ResponseEntity.ok(visitService.getAllVisits());
    }
    @GetMapping(value = "/byid")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404")})
    public ResponseEntity GetVisit(@RequestParam int id) {
        Visit rc = visitService.getById((long) id);
        if(rc != null)
        return ResponseEntity.ok(rc);
        else return ResponseEntity.status(404).build();
    }
    @GetMapping(value = "/bydate")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404")})
    public ResponseEntity GetVisitByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
        List<Visit> rc = visitService.getByDate(date);
        if(rc.size() !=0) return ResponseEntity.ok(rc);
        else return ResponseEntity.status(404).build();
    }

    @PostMapping(value = "/add")
    public ResponseEntity AddVisit(@RequestBody VisitDto visitdto) {
        Visit visit = Mapper.map(visitdto, Visit.class);
        visit.setDoctor(doctorService.getById(visitdto.getDoctorID()));
        visit.setPatient(patientService.FindPatientById(visitdto.getCardID()));
        visitService.save(visit);
        return ResponseEntity.ok(visit);
    }

    @PutMapping(value = "/edit")
    public ResponseEntity EditVisit(@RequestBody VisitDto visitDto) {
        return ResponseEntity.ok(visitService.EditVisit(visitDto));
    }

    @DeleteMapping(value = "/delete/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404")})
    public ResponseEntity DeleteVisit(@PathVariable Long id) {
        Visit rc = visitService.getById(id);
        if(rc != null) {
            visitService.deleteById(Math.toIntExact(id));
            return ResponseEntity.ok(rc);
        } else return ResponseEntity.status(404).build();

    }

}
