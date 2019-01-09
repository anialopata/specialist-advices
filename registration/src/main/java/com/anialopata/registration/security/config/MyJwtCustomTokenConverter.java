package com.anialopata.registration.security.config;

import com.anialopata.registration.security.domain.MyUserDetails;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ania on 2019-01-02.
 */
public class MyJwtCustomTokenConverter extends JwtAccessTokenConverter {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken,
                                     OAuth2Authentication authentication) {

        MyUserDetails user = (MyUserDetails) authentication.getPrincipal();
        final Map<String, Object> additionalInfo = new HashMap<String, Object>();

        additionalInfo.put("userId", user.getId());
        additionalInfo.put("username", user.getUsername());
        additionalInfo.put("email", user.getEmail());
        additionalInfo.put("roles", user.getAuthorities());


        ((DefaultOAuth2AccessToken) accessToken)
                .setAdditionalInformation(additionalInfo);

        return super.enhance(accessToken, authentication);
    }
}
