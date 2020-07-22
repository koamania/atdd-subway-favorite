package nextstep.subway.auth.ui.interceptor.authentication;

import nextstep.subway.auth.application.AuthenticationProvider;
import nextstep.subway.auth.domain.Authentication;
import nextstep.subway.auth.infrastructure.SecurityContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static nextstep.subway.auth.infrastructure.SecurityContextHolder.SPRING_SECURITY_CONTEXT_KEY;

public class SessionAuthenticationInterceptor extends AbstractAuthenticationInterceptor {

    public SessionAuthenticationInterceptor(AuthenticationProvider authenticationProvider) {
        super(authenticationProvider, AuthenticationTokenExtractor.of(AuthenticationTokenExtractor.Type.FORM_LOGIN));
    }

    @Override
    public void applyAuthentication(Authentication authentication, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute(SPRING_SECURITY_CONTEXT_KEY, new SecurityContext(authentication));
    }
}
