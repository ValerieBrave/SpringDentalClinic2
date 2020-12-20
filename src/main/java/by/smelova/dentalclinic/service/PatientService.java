package by.smelova.dentalclinic.service;

import by.smelova.dentalclinic.config.Mapper;
import by.smelova.dentalclinic.dto.PatientDto;
import by.smelova.dentalclinic.models.Patient;
import by.smelova.dentalclinic.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService  extends AbstractService{
    @Autowired
    private PatientRepository patientRepository;

    public PatientService(PatientRepository repository) {super(repository);}

    public List<Patient> GetAllPatients() {return (List<Patient>) patientRepository.findAll();}
    public List<Patient> FindPatientByName(String name) {return patientRepository.findByName(name);}
    public Patient FindPatientById(Long id) {return patientRepository.findByCardID(id);}
    public Patient EditPatient(PatientDto patientDto) {
        patientRepository.UpdatePatient(patientDto.getCardID(), patientDto.getName(), patientDto.getBday(), patientDto.getAddress());
        Patient rc = Mapper.map(patientDto, Patient.class);
        return rc;
    }
}
