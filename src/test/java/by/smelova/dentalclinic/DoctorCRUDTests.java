package by.smelova.dentalclinic;

import by.smelova.dentalclinic.models.Doctor;
import by.smelova.dentalclinic.repository.DoctorRepository;
import by.smelova.dentalclinic.service.DoctorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.Assert.*;

//@RunWith(SpringJUnit4ClassRunner.class)

@RunWith(MockitoJUnitRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DoctorCRUDTests {
    @Mock
    DoctorRepository doctorRepository;
    @InjectMocks
    DoctorService doctorService = new DoctorService(doctorRepository);


    @Test
    public void TestGetByLogin() {

        Doctor to_add = new Doctor();
        to_add.setLogin("testing");
        doctorService.save(to_add);
        //Doctor find = (Doctor) doctorRepository.findDoctorByLogin("testing");
        //Doctor find = doctorService.getByLogin("testing").get(0);
        List<Doctor> all = doctorService.getAll();
        doctorService.deleteById(Math.toIntExact(to_add.getDoctorID()));
        //assertEquals(to_add, find);
    }
}
