package by.smelova.dentalclinic.repository;

import by.smelova.dentalclinic.models.Doctor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends CrudRepository<Doctor,Integer> {
    Optional<Doctor> findDoctorByLogin(String login);
    Doctor findByLoginAndPassword(String login, String password);
}
