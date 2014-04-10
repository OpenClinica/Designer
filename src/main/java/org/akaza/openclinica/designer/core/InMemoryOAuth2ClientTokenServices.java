package org.akaza.openclinica.designer.core;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.consumer.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.consumer.token.OAuth2ClientTokenServices;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Token services that simply stores any tokens in memory.
 * 
 * @author Ryan Heaton
 */
public class InMemoryOAuth2ClientTokenServices implements OAuth2ClientTokenServices {

    private static final Map<String, Map<String, OAuth2AccessToken>> USER_TO_RESOURCE_TO_TOKEN =
        new ConcurrentHashMap<String, Map<String, OAuth2AccessToken>>();

    @Override
    public OAuth2AccessToken getToken(Authentication authentication, OAuth2ProtectedResourceDetails resource) {
        if (authentication != null && authentication.isAuthenticated()) {
            String sessionId = ((WebAuthenticationDetails) authentication.getDetails()).getSessionId();
            Map<String, OAuth2AccessToken> resourceMap = USER_TO_RESOURCE_TO_TOKEN.get(authentication.getName() + sessionId);
            return resourceMap == null ? null : resourceMap.get(resource.getId());
        }

        return null;
    }

    @Override
    public void storeToken(Authentication authentication, OAuth2ProtectedResourceDetails resource, OAuth2AccessToken token) {
        if (authentication != null && authentication.isAuthenticated()) {
            String sessionId = ((WebAuthenticationDetails) authentication.getDetails()).getSessionId();
            Map<String, OAuth2AccessToken> resourceMap = USER_TO_RESOURCE_TO_TOKEN.get(authentication.getName() + sessionId);
            if (resourceMap == null) {
                resourceMap = new ConcurrentHashMap<String, OAuth2AccessToken>();
                USER_TO_RESOURCE_TO_TOKEN.put(authentication.getName() + sessionId, resourceMap);
            }
            resourceMap.put(resource.getId(), token);
        }
    }

    @Override
    public void updateToken(Authentication authentication, OAuth2ProtectedResourceDetails resource, OAuth2AccessToken oldToken, OAuth2AccessToken replacement) {
        storeToken(authentication, resource, replacement);
    }

    @Override
    public void removeToken(Authentication authentication, OAuth2ProtectedResourceDetails resource) {
        if (authentication != null && authentication.isAuthenticated()) {
            String sessionId = ((WebAuthenticationDetails) authentication.getDetails()).getSessionId();
            Map<String, OAuth2AccessToken> resourceMap = USER_TO_RESOURCE_TO_TOKEN.get(authentication.getName() + sessionId);
            if (resourceMap != null) {
                resourceMap.remove(resource.getId());
            }
        }
    }

}
