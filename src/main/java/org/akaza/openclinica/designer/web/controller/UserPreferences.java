package org.akaza.openclinica.designer.web.controller;

import org.springframework.security.oauth2.consumer.OAuth2RestTemplate;
import org.springframework.security.oauth2.consumer.webserver.WebServerProfileResourceDetails;

public class UserPreferences {

    private String host;
    private String appName;
    private String path;
    private String user;
    private String studyOid;
    private WebServerProfileResourceDetails oauthResource;
    private OAuth2RestTemplate restTemplate;
    private Boolean initialized;
    private Boolean editMode;

    // To access this variable in jsp use ${sessionScope['scopedTarget.userPreferences'].host}

    public void initRestTemplate() {

        if (restTemplate != null) {
            return;
        }
        WebServerProfileResourceDetails oauthResource = new WebServerProfileResourceDetails();
        oauthResource.setId("ocInstance");
        oauthResource.setGrantType("authorization_code");
        oauthResource.setClientId("designer");
        oauthResource.setAccessTokenUri(getAppURL() + "oauth/authorize");
        oauthResource.setUserAuthorizationUri(getAppURL() + "oauth/user/authorize");
        restTemplate = new OAuth2RestTemplate(oauthResource);
        editMode = false;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getStudyOid() {
        return studyOid;
    }

    public void setStudyOid(String studyOid) {
        this.studyOid = studyOid;
    }

    public WebServerProfileResourceDetails getOauthResource() {
        return oauthResource;
    }

    public void setOauthResource(WebServerProfileResourceDetails oauthResource) {
        this.oauthResource = oauthResource;
    }

    public OAuth2RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(OAuth2RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Boolean getInitialized() {
        return initialized;
    }

    public void setInitialized(Boolean initialized) {
        this.initialized = initialized;
    }

    private String getURLPrefix() {
        return "";
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Boolean getEditMode() {
        return editMode;
    }

    public void setEditMode(Boolean editMode) {
        this.editMode = editMode;
    }

    public void turnOnEditMode() {
        setEditMode(true);
    }

    public void turnOffEditMode() {
        setEditMode(false);
    }

    public String getAppURL() {
        return getURLPrefix() + host + "/" + appName + "/";
    }

    public String getExitURL() {
        return getAppURL() + (getPath() == null ? "" : getPath());
    }

    public String getConnectURL() {
        return getAppURL() + "pages/rule/studies/" + getStudyOid() + "/connect";
    }

    public String getMetadataURL() {
        return getAppURL() + "pages/rule/studies/" + getStudyOid() + "/metadata";
    }

    public String getValidateAndTestRuleURL() {
        return getAppURL() + "pages/rule/studies/" + getStudyOid() + "/validateAndTestRule";
    }

    public String getValidateRuleURL() {
        return getAppURL() + "pages/rule/studies/" + getStudyOid() + "/validateRule";
    }

    public String getValidateAndSaveRuleURL() {
        return getAppURL() + "pages/rule/studies/" + getStudyOid() + "/validateAndSaveRule?ignoreDuplicates=false";
    }

    public String getValidateAndSaveOrUpdateRuleURL() {
        return getAppURL() + "pages/rule/studies/" + getStudyOid() + "/validateAndSaveRule?ignoreDuplicates=true";
    }

    public String getEditRulesOnOcInstance() {
        return getAppURL() + "ViewRuleAssignment?designer=true";
    }

}
