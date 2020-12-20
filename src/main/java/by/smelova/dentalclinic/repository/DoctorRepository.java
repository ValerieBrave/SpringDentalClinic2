package by.smelova.dentalclinic.repository;

import by.smelova.dentalclinic.models.Doctor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends CrudRepository<Doctor,Integer> {
    Doctor findByDoctorID(Long id);
    List<Doctor> findDoctorByLogin(String login);
    Doctor findByLoginAndPassword(String login, String password);
    @Query(value = "select * from Doctor where name like concat('%', ?1, '%'); ", nativeQuery = true)
    List<Doctor> findByName(String name);

    @Transactional
    @Modifying
    @Query(value = "update Doctor set role = ?2, name = ?3, speciality = ?4, login = ?5, password = ?6, " +
            "email = ?7 where doctorID = ?1", nativeQuery = true)
    void UpdateDoctor(Long doctorID, String role, String name, String speciality,
                      String login, String password, String email);
}
