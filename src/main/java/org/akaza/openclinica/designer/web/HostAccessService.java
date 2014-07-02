package org.akaza.openclinica.designer.web;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.CopyOnWriteArrayList;
import java.net.URL;
import java.net.MalformedURLException;

@Service
public class HostAccessService {

    private static final String HTTP_HTTPS_REGEX = "HTTP://|HTTPS://";

    CopyOnWriteArrayList<String> enterpriseHostList;
    CopyOnWriteArrayList<String> allowHostList;
    Properties resources;

    @Autowired
    public HostAccessService(Properties resources) {
        enterpriseHostList = new CopyOnWriteArrayList<String>();
        this.resources = resources;
        loadAllowList();

    }

    private void getEnterpriseHosts() {
        final String url = Strings.nullToEmpty(resources.getProperty("hostlist.url"));
        String host = "";
        if (! Strings.isNullOrEmpty(url)) {
            try {
                URL hostListURL = new URL(url);
                host = hostListURL.getHost();
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        }
        final Integer port = Integer.parseInt(Strings.nullToEmpty(resources.getProperty("hostlist.port")));
        final String username = Strings.nullToEmpty(resources.getProperty("hostlist.username"));
        final String password = Strings.nullToEmpty(resources.getProperty("hostlist.password"));
        boolean result = false;
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpParams params = new BasicHttpParams();
        params.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 3000);
        httpclient.setParams(params);
        try {
            System.out.println(new Date());
            httpclient.getCredentialsProvider().setCredentials(new AuthScope(host, port), new UsernamePasswordCredentials(username, password));

            HttpGet httpget = new HttpGet(url);
            HttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            System.out.println(new Date());

            String responseAsString = EntityUtils.toString(entity);
            EntityUtils.consume(entity);

            String[] enterpriseHosts = Strings.nullToEmpty(responseAsString).split(",");
            for (int i = 0; i < enterpriseHosts.length; i++) {
                enterpriseHosts[i] = stripHttpOrHttpsAndToUpperCase(enterpriseHosts[i]);
            }
            enterpriseHostList = new CopyOnWriteArrayList<String>(enterpriseHosts);

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }  finally {
            // When HttpClient instance is no longer needed,
            // shut down the connection manager to ensure
            // immediate deallocation of all system resources
            httpclient.getConnectionManager().shutdown();
        }

    }

    public boolean isHostAllowedAccess(String hostProvidedAsParam) {
        String hostProvidedAsParamUpperCase = stripHttpOrHttpsAndToUpperCase(hostProvidedAsParam);
        if (allowHostList.contains("*")) {
            return true;
        }
        if (allowHostList.contains(hostProvidedAsParamUpperCase) || enterpriseHostList.contains(hostProvidedAsParamUpperCase)) {
            return true;
        }
        getEnterpriseHosts();
        if (enterpriseHostList.contains(hostProvidedAsParamUpperCase)) {
            return true;
        }
        return false;
    }

    private void loadAllowList() {
        String[] allowHosts = Strings.nullToEmpty(resources.getProperty("allowHosts")).split(",");
        for (int i = 0; i < allowHosts.length; i++) {
            allowHosts[i] = stripHttpOrHttpsAndToUpperCase(allowHosts[i]);
        }
        allowHostList = new CopyOnWriteArrayList<String>(allowHosts);
    }

    private String stripHttpOrHttpsAndToUpperCase(String url) {
        return url.trim().toUpperCase().replaceAll(HTTP_HTTPS_REGEX, "");
    }

}
