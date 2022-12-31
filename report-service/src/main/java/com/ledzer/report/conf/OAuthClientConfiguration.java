package com.ledzer.report.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;

/**
 * Configuration that sets up the OAuth2 client operation for making calls to
 * the comments-webservice.
 * 
 * @author anilallewar
 *
 */
@Configuration
public class OAuthClientConfiguration {

	@Value("${security.oauth2.client.user-authorization-uri}")
	private String authorizeUrl;

	@Value("${security.oauth2.resource.token-info-uri}")
	private String tokenUrl;

	@Value("${security.oauth2.client.client-id}")
	private String clientId;

	@Value("${security.oauth2.client.client-secret}")
	private String secret;

	/**
	 * RestTempate that relays the OAuth2 token passed to the task webservice.
	 * 
	 * @param oauth2ClientContext
	 * @return
	 */
	@Bean
	public OAuth2RestOperations restTemplate(OAuth2ClientContext oauth2ClientContext) {
		return new OAuth2RestTemplate(resource(), oauth2ClientContext);
	}

	/**
	 * Setup details where the OAuth2 server is.
	 * 
	 * @return
	 */
	@Bean
	protected OAuth2ProtectedResourceDetails resource() {
		AuthorizationCodeResourceDetails resource = new AuthorizationCodeResourceDetails();
		resource.setAccessTokenUri(tokenUrl);
		resource.setUserAuthorizationUri(authorizeUrl);
		resource.setClientId(clientId);
		resource.setClientSecret(secret);
		return resource;
	}
}