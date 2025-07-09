package br.ufsm.csi.trabalhopoow1spring.security;

import br.ufsm.csi.trabalhopoow1spring.model.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AutorizadorInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        String uri = request.getRequestURI();
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");

        if (usuario == null) {
            response.sendRedirect("/");
            return false;
        }

        // Somente administradores podem acessar usuários e tipos
        if (uri.startsWith("/usuarios") || uri.startsWith("/tipos") || uri.startsWith("/obras")) {
            if (!usuario.isAdmin()) {
                response.sendRedirect("/menu");
                return false;
            }
        }

        return true;
    }
}

