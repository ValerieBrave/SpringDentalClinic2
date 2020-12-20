package by.smelova.dentalclinic.service;

import by.smelova.dentalclinic.models.BasicModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public class AbstractService {
    public AbstractService(CrudRepository repository) {
        this.repository = repository;
    }

    public CrudRepository<BasicModel, Long> repository;

    public List getAll() {
        return (List) repository.findAll();
    }

//    public BasicModel getById(int id) {
//        return repository.findById((long) id).get();
//    }

    public BasicModel save(BasicModel basicEntity) {
        return repository.save(basicEntity);
    }

    public void delete(BasicModel basicEntity) {
        repository.delete(basicEntity);
    }

    public void deleteById(int id) {
        repository.deleteById((long) id);
    }
}
