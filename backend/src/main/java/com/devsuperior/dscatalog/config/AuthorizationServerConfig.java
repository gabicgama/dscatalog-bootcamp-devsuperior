package com.devsuperior.dscatalog.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import com.devsuperior.dscatalog.components.JwtTokenEnhancer;
import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

@Configuration
@EnableAuthorizationServer // notação para dizer que a classe representa o authorization server do OAuth
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Value("${security.oauth2.client.client-id}")
	private String clientId;
	
	@Value("${security.oauth2.client.client-secret}")
	private String clientSecret;
	
	@Value("${jwt.duration}")
	private Integer jwtDuration;
	
	@Autowired
	BCryptPasswordEncoder passwaordEncoder;

	@Autowired
	private JwtAccessTokenConverter accessTokenConverter;

	@Autowired
	private JwtTokenStore tokenStore;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenEnhancer tokenEnhancer;

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated");
		super.configure(security);
	}

	/*
	 * Com o obj clients, será definico como será a authenticação e dos dados do
	 * cliente. Nesse método será configurado as credenciais da aplicação e outros
	 * detalhes.
	 */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient(clientId)
		.secret(passwaordEncoder.encode(clientSecret))
		.scopes("read", "write")
		.authorizedGrantTypes("password")
		.accessTokenValiditySeconds(jwtDuration);
	}

	/* Aqui iremos falar quem que vai authorizar e qual vai ser o formato do token */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		TokenEnhancerChain chain = new TokenEnhancerChain();
		chain.setTokenEnhancers(Arrays.asList(tokenEnhancer, accessTokenConverter));	
		
		endpoints.authenticationManager(authenticationManager)
		.tokenStore(tokenStore)
		.accessTokenConverter(accessTokenConverter)
		.tokenEnhancer(chain);		
	}

}
