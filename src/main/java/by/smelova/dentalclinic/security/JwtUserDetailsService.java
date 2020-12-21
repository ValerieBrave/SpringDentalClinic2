package by.smelova.dentalclinic.security;

import by.smelova.dentalclinic.models.Doctor;
import by.smelova.dentalclinic.security.jwt.JwtUser;
import by.smelova.dentalclinic.security.jwt.JwtUserFactory;
import by.smelova.dentalclinic.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private DoctorService doctorService;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Doctor user = doctorService.getByLogin(s);
        if(user == null) {
            throw new UsernameNotFoundException("Doctor with username "+s+" not found");
        }
        JwtUser jwtUser = JwtUserFactory.create(user);
        return jwtUser;
    }
}
