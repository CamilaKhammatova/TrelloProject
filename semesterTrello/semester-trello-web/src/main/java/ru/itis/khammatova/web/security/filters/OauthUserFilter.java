package ru.itis.khammatova.web.security.filters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.itis.khammatova.api.dto.SignUpForm;
import ru.itis.khammatova.api.dto.UserDto;
import ru.itis.khammatova.api.services.UserService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Component
public class OauthUserFilter extends OncePerRequestFilter {

    @Autowired
    private UserService userService;

    @Override
    public void doFilterInternal(HttpServletRequest servletRequest, HttpServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        HttpSession session = servletRequest.getSession();

        if (authentication != null && session.getAttribute("user") == null && authentication instanceof OAuth2AuthenticationToken) {
            DefaultOidcUser user = (DefaultOidcUser) authentication.getPrincipal();
            Map<String, Object> attributes = user.getAttributes();

            SignUpForm signUpForm = SignUpForm.builder()
                    .email((String) attributes.get("email"))
                    .firstname((String) attributes.get("given_name"))
                    .build();

            Optional<UserDto> optionalUserDto = userService.userByEmail(signUpForm.getEmail());
            if (!optionalUserDto.isPresent()) {
                userService.signUp(signUpForm);
                session.setAttribute("user", userService.userByEmail(signUpForm.getEmail()).get());
            } else {
                session.setAttribute("user", optionalUserDto.get());
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
