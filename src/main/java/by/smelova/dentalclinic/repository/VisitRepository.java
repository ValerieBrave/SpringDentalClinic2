package by.smelova.dentalclinic.repository;

import by.smelova.dentalclinic.models.Doctor;
import by.smelova.dentalclinic.models.Visit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Optional;

public interface VisitRepository extends CrudRepository<Visit,Integer> {
    Optional<Visit> findByDate(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date);
    Optional<Visit> findByDoctor(Doctor doc);
}
