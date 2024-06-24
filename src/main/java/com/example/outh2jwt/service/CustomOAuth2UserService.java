package com.example.outh2jwt.service;

import com.example.outh2jwt.dto.CustomOAuth2User;
import com.example.outh2jwt.dto.GoogleResponse;
import com.example.outh2jwt.dto.OAuth2Response;
import com.example.outh2jwt.dto.UserDTO;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);

        System.out.println(oAuth2User);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = null;
        if (registrationId.equals("naver")) {

            System.out.println("Naver");
        }
        else if (registrationId.equals("google")) {

            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
        }
        else {

            return null;
        }

        String username = oAuth2Response.getProvider() + "" + oAuth2Response.getProvider();


        UserDTO userDTO = new UserDTO();

        userDTO.setRole("ROLE_USER");
        userDTO.setName(oAuth2Response.getName());
        userDTO.setUsername(username);

        return new CustomOAuth2User(userDTO);
    }


}
