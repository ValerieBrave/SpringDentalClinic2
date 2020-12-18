package by.smelova.dentalclinic.service;

import by.smelova.dentalclinic.models.Visit;
import by.smelova.dentalclinic.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitService extends AbstractService {
    @Autowired
    private VisitRepository visitRepository;

    public VisitService(VisitRepository repository) {super(repository);}

    public List<Visit> getAllVisits() { return (List<Visit>) visitRepository.findAll();}
}
