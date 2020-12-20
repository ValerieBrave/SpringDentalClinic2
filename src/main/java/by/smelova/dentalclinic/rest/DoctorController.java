package by.smelova.dentalclinic.rest;

import by.smelova.dentalclinic.config.Mapper;
import by.smelova.dentalclinic.dto.DoctorDto;
import by.smelova.dentalclinic.models.Doctor;
import by.smelova.dentalclinic.repository.RoleRepository;
import by.smelova.dentalclinic.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;

@RestController
public class DoctorController {
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping(value = "/doctors")
    public ResponseEntity GetDoctors() {return ResponseEntity.ok(doctorService.getAll());}
    @GetMapping(value = "/doctor/bylogin")
    public ResponseEntity GetDoctor(@RequestParam String login) {return ResponseEntity.ok(doctorService.getByLogin(login));}
    @GetMapping(value = "/doctor/bycreds")
    public ResponseEntity GetDoctor(@RequestParam String login, @RequestParam String password) {
        return ResponseEntity.ok(doctorService.getByLoginAndPassword(login, password));
    }
    @PostMapping(value = "/doctor/add")
    public ResponseEntity AddDoctor(@RequestBody DoctorDto doctorDto) {
        Doctor doc = Mapper.map(doctorDto, Doctor.class);
        doc.setRole(roleRepository.getRoleByRole(doctorDto.getRole()));
        doc.setPassword(passwordEncoder.encode(doctorDto.getPassword()));
        doctorService.save(doc);
        return ResponseEntity.ok(doc);
    }

    @PutMapping(value = "/doctor/edit")
    public ResponseEntity EditDoctor(@RequestBody DoctorDto doctorDto) {
        return ResponseEntity.ok(doctorService.EditDoctor(doctorDto));
    }

    @DeleteMapping(value = "/doctor/delete/{id}")
    public ResponseEntity DeleteDoctor(@PathVariable Long id) {
        Doctor rc = doctorService.getById(id);
        if(rc.getDoctor_visits().size() == 0) {
            doctorService.deleteById(Math.toIntExact(id));
            return ResponseEntity.ok(rc);
        } else return ResponseEntity.status(403).build();


    }
}
