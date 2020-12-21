package by.smelova.dentalclinic.security.jwt;

import by.smelova.dentalclinic.models.Doctor;
import by.smelova.dentalclinic.models.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public final class JwtUserFactory {
    public JwtUserFactory() {
    }

    public static JwtUser create(Doctor user) {
        return new JwtUser(user.getDoctorID(), user.getLogin(), user.getPassword(), mapToGrantedAuthority(user.getRole()));
    }

    private static List<GrantedAuthority> mapToGrantedAuthority(Role user_role) {
        List<GrantedAuthority> rc = new ArrayList<>();
        rc.add(new SimpleGrantedAuthority(user_role.getRole()));
        return rc;
    }
}
