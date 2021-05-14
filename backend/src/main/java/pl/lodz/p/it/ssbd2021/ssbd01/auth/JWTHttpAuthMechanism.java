package pl.lodz.p.it.ssbd2021.ssbd01.auth;

import pl.lodz.p.it.ssbd2021.ssbd01.auth.ejb.managers.AuthViewEntityManager;
import pl.lodz.p.it.ssbd2021.ssbd01.entities.AccessLevel;
import pl.lodz.p.it.ssbd2021.ssbd01.entities.Account;
import pl.lodz.p.it.ssbd2021.ssbd01.entities.AuthViewEntity;
import pl.lodz.p.it.ssbd2021.ssbd01.security.JwtLoginUtils;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import javax.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequestScoped
@Default
public class JWTHttpAuthMechanism implements HttpAuthenticationMechanism {

    @Inject
    private JwtLoginUtils jwtLoginUtils;

    @Inject
    private AuthViewEntityManager authViewEntityManager;

    @Override
    public AuthenticationStatus validateRequest(HttpServletRequest req, HttpServletResponse res, HttpMessageContext msgContext) {
        try {
            String jwt = parseJwt(req);
            if (jwt != null && jwtLoginUtils.validateJwtToken(jwt)) {
                String username = jwtLoginUtils.getUserNameFromJwtToken(jwt);
                List<AuthViewEntity> authViewEntities = authViewEntityManager.findByLogin(username);
                if (authViewEntities.size() != 0) {
                    Set<String> levels = new HashSet<>();
                    for (var x : authViewEntities) {
                        levels.add(x.getLevel());
                    }
                    return msgContext.notifyContainerAboutLogin(username, levels);
                }
            }
            if (!msgContext.isProtected()) {
                return msgContext.doNothing();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msgContext.responseUnauthorized();
    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (headerAuth != null && headerAuth.length() > 0 && !headerAuth.isBlank() && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }

        return null;
    }
}