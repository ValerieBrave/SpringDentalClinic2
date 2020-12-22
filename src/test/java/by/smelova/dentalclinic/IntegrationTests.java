package by.smelova.dentalclinic;

import by.smelova.dentalclinic.models.Doctor;
import by.smelova.dentalclinic.models.Role;
import by.smelova.dentalclinic.repository.DoctorRepository;
import by.smelova.dentalclinic.security.jwt.JwtTokenProvider;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@RunWith(SpringJUnit4ClassRunner.class)
@RunWith(SpringRunner.class)
@SpringBootTest
public class IntegrationTests {
    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    JwtTokenProvider jwtTokenProvider;
    private MockMvc mockMvc;

    protected void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    @Test
    public void testSetJwtTokenProviderUSER() throws Exception {
        setUp();
        Role user_role = new Role();
        user_role.setRole("ROLE_USER");
        String token = jwtTokenProvider.createToken("Rusty89", user_role);
        Authentication authentication = jwtTokenProvider.getAuthentication(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/visit?id=1").header("Authorization", "Bearer_" + token))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void testSetJwtTokenProviderADMIN() throws Exception {
        setUp();
        Role user_role = new Role();
        user_role.setRole("ROLE_ADMIN");
        String token = jwtTokenProvider.createToken("JuDashxxx", user_role);
        Authentication authentication = jwtTokenProvider.getAuthentication(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/admin/doctor/bylogin?login=Rusty89").header("Authorization", "Bearer_" + token))
                .andExpect(status().is2xxSuccessful());
    }
}
