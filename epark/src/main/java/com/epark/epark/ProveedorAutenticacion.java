package com.epark.epark;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

import com.epark.epark.Servicios.ClienteServicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class ProveedorAutenticacion implements AuthenticationProvider {
    @Autowired
    ClienteServicios servicioCliente;

    @Override
    public Authentication authenticate(Authentication authentication) {
        String usuario = authentication.getName();
        String clave = authentication.getCredentials().toString();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        if (servicioCliente.validar(usuario, clave, request.getSession())) {
            return new UsernamePasswordAuthenticationToken(usuario + clave, clave, new ArrayList<>());
        } else {
            throw new BadCredentialsException("Usuario o contraseña incorrectos");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
