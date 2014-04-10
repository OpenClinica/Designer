package org.akaza.openclinica.designer.core;

import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This filter resets an active session when the user & host params in the request do not match those in the session.
 * @author Krikor Krumlian
 * 
 */
public class RequestFilter extends OncePerRequestFilter {

    private static final String SESSION_KEY_PROVIDER_USER = "providerUser";
    private static final String SESSION_KEY_PROVIDER_HOST = "providerHost";
    private static final String REQUEST_KEY_PROVIDER_USER = "provider_user";
    private static final String REQUEST_KEY_PROVIDER_HOST = "host";

    /*
     * @see org.springframework.web.filter.OncePerRequestFilter#doFilterInternal(javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        HttpSession session = request.getSession();
        if (doesSessionHaveUserAndHost(session) && doesRequestHaveUserAndHost(request) && !areUserAndHostInRequestSameAsUserAndHostInSession(session, request)) {

            session.invalidate();
            session = request.getSession(true);
        }
        filterChain.doFilter(request, response);
    }

    private Boolean doesSessionHaveUserAndHost(HttpSession session) {
        String providerUserInSession = (String) session.getAttribute(SESSION_KEY_PROVIDER_USER);
        String providerHostInSession = (String) session.getAttribute(SESSION_KEY_PROVIDER_HOST);

        return (providerUserInSession != null && providerHostInSession != null);
    }

    private Boolean doesRequestHaveUserAndHost(HttpServletRequest request) {
        String providerUserInRequest = request.getParameter(REQUEST_KEY_PROVIDER_USER);
        String providerHostInRequest = request.getParameter(REQUEST_KEY_PROVIDER_HOST);

        return (providerUserInRequest != null && providerHostInRequest != null);
    }

    private Boolean areUserAndHostInRequestSameAsUserAndHostInSession(HttpSession session, HttpServletRequest request) {
        String providerUserInURL = request.getParameter(REQUEST_KEY_PROVIDER_USER);
        String providerUserInSession = (String) session.getAttribute(SESSION_KEY_PROVIDER_USER);
        String providerHostInURL = request.getParameter(REQUEST_KEY_PROVIDER_HOST);
        String providerHostInSession = (String) session.getAttribute(SESSION_KEY_PROVIDER_HOST);
        Boolean isSameUser = (providerUserInSession != null && providerUserInURL != null && providerUserInURL.equals(providerUserInSession));
        Boolean isSameHost = (providerHostInSession != null && providerHostInURL != null && providerHostInURL.equals(providerHostInSession));

        return isSameUser && isSameHost;
    }
}
