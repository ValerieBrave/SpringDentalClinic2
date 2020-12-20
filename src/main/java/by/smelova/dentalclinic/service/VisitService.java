package by.smelova.dentalclinic.service;

import by.smelova.dentalclinic.config.Mapper;
import by.smelova.dentalclinic.dto.VisitDto;
import by.smelova.dentalclinic.models.Doctor;
import by.smelova.dentalclinic.models.Visit;
import by.smelova.dentalclinic.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class VisitService extends AbstractService {
    @Autowired
    private VisitRepository visitRepository;

    public VisitService(VisitRepository repository) {super(repository);}

    public List<Visit> getAllVisits() { return (List<Visit>) visitRepository.findAll();}

    public Visit getById(Long id) {return visitRepository.findByVisitID(id);    }
    public List<Visit> getByDate(Date date) {return visitRepository.findByDate(date);}
    public List<Visit> getByDoctor(Doctor d) {return visitRepository.findByDoctor(d.getDoctorID());}
    public Visit EditVisit(VisitDto vis) {
        visitRepository.UpdateVisit(vis.getVisitID(), vis.getDoctorID(), vis.getCardID(),
                vis.getDate(), vis.getComplains(), vis.getDiagnosis(), vis.getTreatment());
        Visit rc = Mapper.map(vis, Visit.class);
        return rc;
    }
}
