/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modle;

import pojo.User;

/**
 *
 * @author suhada
 */
public class AuthUser {
    
    private static pojo.User user;

    public static User getUser() {
        return AuthUser.user;
    }

    public static  void setUser(User user) {
        AuthUser.user = user;
    }
    public static boolean isLogin(){
        if(AuthUser.user!=null)
            return true;
        else
            return false;
    }
}
