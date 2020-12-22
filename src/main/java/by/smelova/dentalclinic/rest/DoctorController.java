package by.smelova.dentalclinic.rest;

import by.smelova.dentalclinic.aspect.NeedToLog;
import by.smelova.dentalclinic.config.Mapper;
import by.smelova.dentalclinic.dto.DoctorDto;
import by.smelova.dentalclinic.models.Doctor;
import by.smelova.dentalclinic.repository.RoleRepository;
import by.smelova.dentalclinic.service.DoctorService;
import by.smelova.dentalclinic.service.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;

@RestController
@RequestMapping(value = "/api/admin/doctor/")
public class DoctorController {

    private DoctorService doctorService;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private MailSenderService mailSenderService;

    @Autowired
    public DoctorController(DoctorService doctorService, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.doctorService = doctorService;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailSenderService = new MailSenderService();
    }

    @GetMapping(value = "/all")
    public ResponseEntity GetDoctors() {return ResponseEntity.ok(doctorService.getAll());}
    @GetMapping(value = "/bylogin")
    public ResponseEntity GetDoctor(@RequestParam String login) {return ResponseEntity.ok(doctorService.getByLogin(login));}
    @GetMapping(value = "/bycreds")
    public ResponseEntity GetDoctor(@RequestParam String login, @RequestParam String password) {
        return ResponseEntity.ok(doctorService.getByLoginAndPassword(login, password));
    }

    @NeedToLog
    @PostMapping(value = "/add")
    public ResponseEntity AddDoctor(@RequestBody DoctorDto doctorDto) {
        Doctor doc = Mapper.map(doctorDto, Doctor.class);
        doc.setRole(roleRepository.getRoleByRole(doctorDto.getRole()));
        doc.setPassword(passwordEncoder.encode(doctorDto.getPassword()));
        doctorService.save(doc);
        mailSenderService.Send("registration", "You registered on DentalClinic with username "+doctorDto.getLogin(), doctorDto.getEmail());
        return ResponseEntity.ok(doc);
    }

    @PutMapping(value = "/edit")
    public ResponseEntity EditDoctor(@RequestBody DoctorDto doctorDto) {
        return ResponseEntity.ok(doctorService.EditDoctor(doctorDto));
    }

    @NeedToLog
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity DeleteDoctor(@PathVariable Long id) {
        Doctor rc = doctorService.getById(id);
        if(rc.getDoctor_visits().size() == 0) {
            doctorService.deleteById(Math.toIntExact(id));
            return ResponseEntity.ok(rc);
        } else return ResponseEntity.status(403).build();


    }
}
