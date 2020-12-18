package by.smelova.dentalclinic.repository;

import by.smelova.dentalclinic.models.Patient;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PatientRepository extends CrudRepository<Patient,Integer> {
    Optional<Patient> findByName(String name);
}
