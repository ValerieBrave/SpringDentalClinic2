package by.smelova.dentalclinic.repository;

import by.smelova.dentalclinic.models.Patient;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PatientRepository extends CrudRepository<Patient,Integer> {
    @Query(value = "select * from Patient where name like CONCAT('%', ?1, '%')", nativeQuery = true)
    List<Patient> findByName(String name);
    Patient findByCardID(Long id);

    @Transactional
    @Modifying
    @Query(value = "update Patient set name = ?2, bday = ?3, address = ?4 where cardID = ?1", nativeQuery = true)
    void UpdatePatient(Long cardID, String name, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date bday, String address);
}
