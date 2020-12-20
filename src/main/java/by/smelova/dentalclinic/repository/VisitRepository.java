package by.smelova.dentalclinic.repository;

import by.smelova.dentalclinic.dto.VisitDto;
import by.smelova.dentalclinic.models.Doctor;
import by.smelova.dentalclinic.models.Visit;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface VisitRepository extends CrudRepository<Visit,Integer> {
    Visit findByVisitID(Long id);
    List<Visit> findByDate(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date);
    @Query(value = "select * from Visit where doctorID = ?1", nativeQuery = true)
    List<Visit> findByDoctor(Long doctorID);
    @Modifying
    @Transactional
    @Query(value = "update Visit set doctorID = ?2, cardID = ?3, date = ?4, complains = ?5," +
                    " diagnosis = ?6, treatment = ?7 where visitID = ?1", nativeQuery = true)
    void UpdateVisit(Long visitID, Long doctorID, Long cardID, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
                    String complains, String diagnosis, String treatment);
}
