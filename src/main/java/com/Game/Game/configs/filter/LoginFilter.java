package com.Game.Game.configs.filter;

import com.Game.Game.models.Role;
import com.Game.Game.models.User;
import com.Game.Game.service.UserService;
import com.Game.Game.service.auth.AuthenticationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xml.internal.ws.encoding.ContentType;
import org.json.JSONException;
import org.json.JSONObject;
import org.omg.IOP.Encoding;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;

public class LoginFilter extends AbstractAuthenticationProcessingFilter {
    private final UserService userDetailsService;

    public LoginFilter(String url, AuthenticationManager authManager, UserService userDetailsService) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
        this.userDetailsService=userDetailsService;
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        User user=new ObjectMapper().readValue(httpServletRequest.getInputStream(),User.class);
        Authentication authentication=getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword(),
                        Collections.emptyList()
                )
        );
        if(authentication.isAuthenticated()){
            String userName=user.getUsername();
            user=userDetailsService.findByUserName(userName);
            JSONObject jsonObject=new JSONObject();
            try {
                OptionalInt max=user.getRoles()
                        .stream().map(Role::getValue)
                        .collect(Collectors.toList())
                        .stream().mapToInt(Integer::intValue)
                        .max();
                jsonObject.put("role",max.getAsInt());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            PrintWriter out=httpServletResponse.getWriter();
            httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
            httpServletResponse.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
            out.print(jsonObject);
        }
        return authentication;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        AuthenticationService.addJWTToken(response, authResult.getName());
    }
}
