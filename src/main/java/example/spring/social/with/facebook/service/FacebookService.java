package example.spring.social.with.facebook.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Service;

@Service
public class FacebookService {

    @Value("${social.facebook.id}")
    private String facebookSocialId;

    @Value("${social.facebook.secret}")
    private String facebookSocialSecret;

    private String facebookRedirectUrl = "http://localhost:8080/login/facebook/confirmed";

    //1) we connect to facebook and we will receive from facebook login url
    //2) when user login facebook, facebook will send our app redirect url which was written (http://localhost:8080/login/facebook/confirmed)
    public String createRedirectUrl(){
        FacebookConnectionFactory facebookConnectionFactory = new FacebookConnectionFactory(facebookSocialId,facebookSocialSecret);
        OAuth2Operations oAuthOperations = facebookConnectionFactory.getOAuthOperations();
        OAuth2Parameters parameters = new OAuth2Parameters();
        parameters.setRedirectUri(facebookRedirectUrl);
        parameters.setScope("public_profile,email");
        return oAuthOperations.buildAuthenticateUrl(parameters);
    }

    //1)here when we will receive our mentioned redirect url with parameter code from facebook we will use code and will get access token
    //2) then when we have already got access token we can get info for user
    public User getCurrentUserByCode(String code){
        FacebookConnectionFactory facebookConnectionFactory = new FacebookConnectionFactory(facebookSocialId,facebookSocialSecret);
        String accessToken = facebookConnectionFactory
                .getOAuthOperations()
                .exchangeForAccess(code, facebookRedirectUrl, null)
                .getAccessToken();
        Facebook facebook = new FacebookTemplate(accessToken);
        String[] fields = {"id","first_name","last_name","cover","email"};
        return facebook.fetchObject("me", User.class, fields);
    }
}
