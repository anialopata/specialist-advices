package com.anialopata.registration.security.config;

import com.anialopata.registration.security.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * Created by Ania on 2018-11-15.
 */
@CrossOrigin(origins = "http://localhost:4200")
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    private static String REALM="REALM";
    private static final int TWENTY_DAYS = 60 * 60 * 24 * 20;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    private final TokenStore tokenStore;

    private final UserApprovalHandler userApprovalHandler;

    private final MyUserDetailService myUserDetailService;

    private final MyJwtCustomTokenConverter myJwtCustomTokenConverter;

    private final JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired
    public AuthorizationServerConfig(@Qualifier("authenticationManagerBean") AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, TokenStore tokenStore, UserApprovalHandler userApprovalHandler, MyUserDetailService myUserDetailService, JwtAccessTokenConverter jwtTokenEnhancer, MyJwtCustomTokenConverter myJwtCustomTokenConverter, JwtAccessTokenConverter jwtAccessTokenConverter) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.tokenStore = tokenStore;
        this.userApprovalHandler = userApprovalHandler;
        this.myUserDetailService = myUserDetailService;
        this.myJwtCustomTokenConverter = myJwtCustomTokenConverter;
        this.jwtAccessTokenConverter = jwtAccessTokenConverter;
    }


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("client")
                .secret(passwordEncoder.encode("client"))
                .authorizedGrantTypes("password", "refresh_token")
                .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
                .scopes("read", "write", "trust")
                .accessTokenValiditySeconds(600)
                .refreshTokenValiditySeconds(TWENTY_DAYS);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
                endpoints.tokenStore(tokenStore).tokenEnhancer(jwtAccessTokenConverter)
                        .userApprovalHandler(userApprovalHandler).authenticationManager(authenticationManager)
                .userDetailsService(myUserDetailService);
//        endpoints.tokenStore(tokenStore()).userApprovalHandler(userApprovalHandler)
//                .authenticationManager(authenticationManager)
//                .userDetailsService(myUserDetailsService);
    }
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.realm(REALM);
    }




}