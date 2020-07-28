/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuongntd.google;

import java.io.Serializable;

/**
 *
 * @author Yun
 */
public class GoogleConstants implements Serializable {

    public static String GOOGLE_CLIENT_ID = "918743542771-ki05lu38k1d2c0d0rh1ukhssr601f09n.apps.googleusercontent.com";
    public static String GOOGLE_CLIENT_SECRET = "JYvHifV6OnkD48HztGSgbPTt";
    public static String GOOGLE_REDIRECT_URI = "http://localhost:8084/ResourceSharing/loginWithGoogle";
    public static String GOOGLE_LINK_GET_TOKEN = "https://accounts.google.com/o/oauth2/token";
    public static String GOOGLE_LINK_GET_USER_INFO = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";
    public static String GOOGLE_GRANT_TYPE = "authorization_code";

}
