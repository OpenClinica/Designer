package org.akaza.openclinica.designer.core;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.util.SerializationUtils;
import org.springframework.security.oauth2.consumer.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.consumer.token.OAuth2ClientTokenServices;
import org.springframework.util.Assert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

public class JdbcOAuth2ClientTokenServices implements OAuth2ClientTokenServices {

    private final JdbcTemplate jdbcTemplate;
    private static final Map<String, Map<String, OAuth2AccessToken>> USER_TO_RESOURCE_TO_TOKEN =
        new ConcurrentHashMap<String, Map<String, OAuth2AccessToken>>();
    private static final String TOKEN_INSERT_STATEMENT = "insert into oauth_client_token (authentication_name,resource_map) values (?, ?)";
    private static final String TOKEN_SELECT_STATEMENT = "select authentication_name,resource_map from oauth_client_token where authentication_name = ?";
    private static final String TOKEN_DELETE_STATEMENT = "delete from oauth_client_token where authentication_name = ?";

    private final String insertTokenSql = TOKEN_INSERT_STATEMENT;
    private final String selectTokenSql = TOKEN_SELECT_STATEMENT;
    private final String deleteTokenSql = TOKEN_DELETE_STATEMENT;

    public JdbcOAuth2ClientTokenServices(DataSource dataSource) {
        Assert.notNull(dataSource, "DataSource required");
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public OAuth2AccessToken getToken(Authentication authentication, OAuth2ProtectedResourceDetails resource) {
        if (authentication != null && authentication.isAuthenticated()) {
            // Map<String, OAuth2AccessToken> resourceMap = USER_TO_RESOURCE_TO_TOKEN.get(authentication.getName());
            Map<String, OAuth2AccessToken> resourceMap = getResourceMap(authentication.getName());
            return resourceMap == null ? null : resourceMap.get(resource.getId());
        }

        return null;
    }

    @Override
    public void storeToken(Authentication authentication, OAuth2ProtectedResourceDetails resource, OAuth2AccessToken token) {
        if (authentication != null && authentication.isAuthenticated()) {
            Map<String, OAuth2AccessToken> resourceMap = getResourceMap(authentication.getName());
            // Map<String, OAuth2AccessToken> resourceMap = USER_TO_RESOURCE_TO_TOKEN.get(authentication.getName());
            if (resourceMap == null) {
                resourceMap = new ConcurrentHashMap<String, OAuth2AccessToken>();
                // USER_TO_RESOURCE_TO_TOKEN.put(authentication.getName(), resourceMap);
            } else {
                delete(authentication.getName());
            }
            resourceMap.put(resource.getId(), token);

            jdbcTemplate.update(insertTokenSql, new Object[] { authentication.getName(), new SqlLobValue(SerializationUtils.serialize(resourceMap)) },
                    new int[] { Types.VARCHAR, Types.BLOB });
        }
    }

    private Map<String, OAuth2AccessToken> getResourceMap(String authenticationName) {
        Map<String, OAuth2AccessToken> resourceMap = null;
        try {
            resourceMap = jdbcTemplate.queryForObject(selectTokenSql, new RowMapper<Map<String, OAuth2AccessToken>>() {
                @Override
                public Map<String, OAuth2AccessToken> mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return SerializationUtils.deserialize(rs.getBytes("resource_map"));
                }
            }, authenticationName);
        } catch (EmptyResultDataAccessException e) {
            return resourceMap;
        }
        return resourceMap;
    }

    private void delete(String authenticationName) {
        jdbcTemplate.update(deleteTokenSql, authenticationName);
    }

    @Override
    public void updateToken(Authentication authentication, OAuth2ProtectedResourceDetails resource, OAuth2AccessToken oldToken, OAuth2AccessToken replacement) {
        storeToken(authentication, resource, replacement);
    }

    @Override
    public void removeToken(Authentication authentication, OAuth2ProtectedResourceDetails resource) {
        if (authentication != null && authentication.isAuthenticated()) {
            // Map<String, OAuth2AccessToken> resourceMap = USER_TO_RESOURCE_TO_TOKEN.get(authentication.getName());
            Map<String, OAuth2AccessToken> resourceMap = getResourceMap(authentication.getName());
            if (resourceMap != null) {
                resourceMap.remove(resource.getId());
            }
        }
    }
}
