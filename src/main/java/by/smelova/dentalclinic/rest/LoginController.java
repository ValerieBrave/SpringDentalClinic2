package by.smelova.dentalclinic.rest;

import by.smelova.dentalclinic.dto.AuthRequestDto;
import by.smelova.dentalclinic.dto.DoctorDto;
import by.smelova.dentalclinic.models.Doctor;
import by.smelova.dentalclinic.security.jwt.JwtTokenProvider;
import by.smelova.dentalclinic.service.DoctorService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController

public class LoginController {

    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    private DoctorService doctorService;
    @Autowired
    public LoginController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, DoctorService doctorService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.doctorService = doctorService;
    }

    @PostMapping(value = "/auth/login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "403")})
    public ResponseEntity login(@RequestBody AuthRequestDto authRequestDto) {
        try {
            String login = authRequestDto.getLogin();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, authRequestDto.getPassword()));
            Doctor user = doctorService.getByLogin(login);
            if(user == null) throw new UsernameNotFoundException("User with login "+login+" not found");
            String token = jwtTokenProvider.createToken(login, user.getRole());
            Map<Object, Object> response = new HashMap<>();
            response.put("login", login);
            response.put("token", token);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
