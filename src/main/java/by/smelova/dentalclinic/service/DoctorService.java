package by.smelova.dentalclinic.service;

import by.smelova.dentalclinic.config.Mapper;
import by.smelova.dentalclinic.dto.DoctorDto;
import by.smelova.dentalclinic.models.Doctor;
import by.smelova.dentalclinic.repository.DoctorRepository;
import by.smelova.dentalclinic.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService extends AbstractService{
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public DoctorService(DoctorRepository repository) {
        super(repository);
    }
    public Doctor getById(Long id) {return doctorRepository.findByDoctorID(id);}
    public Doctor getByLogin(String login) {return doctorRepository.findDoctorByLogin(login);}
    public Doctor getByLoginAndPassword(String login, String password) {return doctorRepository.findByLoginAndPassword(login, password);}
    public Doctor EditDoctor(DoctorDto doctorDto) {
        doctorRepository.UpdateDoctor(doctorDto.getDoctorID(), doctorDto.getRole(), doctorDto.getName(), doctorDto.getSpeciality(),
                doctorDto.getLogin(), passwordEncoder.encode(doctorDto.getPassword()), doctorDto.getEmail());
        Doctor rc = Mapper.map(doctorDto, Doctor.class);
        return rc;
    }
}
