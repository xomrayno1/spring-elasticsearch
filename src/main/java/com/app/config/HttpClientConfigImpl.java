package com.app.config;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpClientConfigImpl implements RestClientBuilder.HttpClientConfigCallback {

	@Override
	public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpAsyncClientBuilder) {
		try {
			UsernamePasswordCredentials usernamePasswordCredentials = new UsernamePasswordCredentials("elastic", "");
			
			final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
			credentialsProvider.setCredentials(AuthScope.ANY, usernamePasswordCredentials);
			httpAsyncClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
			
			//link chua cac thong tin ve keystore https
//			String trustStoreLocation = "";
//			File trustStoreLocationFile = new File(trustStoreLocation);
//			
//			SSLContextBuilder sslContextBuilder = SSLContexts.custom().loadTrustMaterial(trustStoreLocationFile, "password".toCharArray());
//			SSLContext sslContext = sslContextBuilder.build();
//			
//			httpAsyncClientBuilder.setSSLContext(sslContext);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return httpAsyncClientBuilder;
	}
	  
}
