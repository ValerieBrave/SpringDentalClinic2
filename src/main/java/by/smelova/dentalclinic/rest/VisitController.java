package by.smelova.dentalclinic.rest;

import by.smelova.dentalclinic.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VisitController {
    @Autowired
    private VisitService visitService;

    @GetMapping(value = "/visits")
    public ResponseEntity GetSVisits() {
        return ResponseEntity.ok(visitService.getAll());
    }

}
