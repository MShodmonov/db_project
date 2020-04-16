package dreamteam.db_project.service;

import dreamteam.db_project.model.Roles;
import dreamteam.db_project.model.Users;
import dreamteam.db_project.payload.SignUpRequest;
import dreamteam.db_project.repository.RolesRepo;
import dreamteam.db_project.repository.UserRepo;
import dreamteam.db_project.security.MyAuthentication;
import dreamteam.db_project.security.MySecurityContext;
import dreamteam.db_project.security.UserPrincipal;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

    @Service
    public class AuthService implements UserDetailsService {

        private final UserRepo userRepository;
        private final RolesRepo rolesRepo;

        public AuthService(UserRepo userRepository, RolesRepo rolesRepo) {
            this.userRepository = userRepository;
            this.rolesRepo = rolesRepo;
        }


        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            Users user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
//        List<Role> roles = user.getRoles();
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        for (Role role : roles) {
//            authorities.add(new SimpleGrantedAuthority(role.getName().name()));
//        }

            return getUserPrincipal(user);

        }

        public UserDetails loadUserById(String userId) {
            Users user = userRepository.findById(UUID.fromString(userId)).orElseThrow(() -> new UsernameNotFoundException("User not found user id: " + userId));
            return getUserPrincipal(user);
        }

        public UserDetails getUserPrincipal(Users user) {
            return new UserPrincipal(user.getId().toString(),
                    user.getUsername(),
                    user.getPassword(),
                    user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList())
            );
        }
        public boolean isAuthenticate() {
            return (SecurityContextHolder.getContext().getAuthentication()
                    instanceof AnonymousAuthenticationToken);
        }

        public Users saveUser(SignUpRequest signUpRequest)
        {
            List<Roles> list=new LinkedList<>();
            list.add(rolesRepo.findByRole("ROLE_USER").get());
           return userRepository.save(new Users(signUpRequest.getUsername(),signUpRequest.getPassword(),signUpRequest.getFullName(),list));
        }
        public Authentication setSecurity(UserPrincipal user) {
            SecurityContext context = new MySecurityContext();
            MyAuthentication authentication = new MyAuthentication(user.getAuthorities());
            authentication.setPrincipal(user);
            authentication.setAuthenticated(true);

            context.setAuthentication(authentication);

            SecurityContextHolder.setContext(context);
            return authentication;
        }

    }
