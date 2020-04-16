package dreamteam.db_project.controllers;

import dreamteam.db_project.model.Users;
import dreamteam.db_project.payload.JwtAuthenticationResponse;
import dreamteam.db_project.payload.SignInRequest;
import dreamteam.db_project.payload.SignUpRequest;
import dreamteam.db_project.security.JwtTokenProvider;
import dreamteam.db_project.security.UserPrincipal;
import dreamteam.db_project.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController()
@RequestMapping("/api/auth")
public class AuthController {
   private final
    JwtTokenProvider tokenProvider;
   private final AuthService authService;

   private final
    AuthenticationManager authenticationManager;


    public AuthController(JwtTokenProvider tokenProvider, AuthService authService, AuthenticationManager authenticationManager) {
        this.tokenProvider = tokenProvider;
        this.authService = authService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody SignInRequest signInRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword())
        );
        //new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword())

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));

    }
    @PostMapping("/signup")
    public ResponseEntity<?>registerUser(@Valid @RequestBody SignUpRequest signUpRequest)
    {
        if(authService.isAuthenticate())
        {
            Users saveUser = authService.saveUser(signUpRequest);
            UserDetails userPrincipal = authService.getUserPrincipal(saveUser);
            Authentication authentication = authService.setSecurity((UserPrincipal) userPrincipal);
            String jwt = tokenProvider.generateToken(authentication);
            return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
        }



return null;
    }
}
