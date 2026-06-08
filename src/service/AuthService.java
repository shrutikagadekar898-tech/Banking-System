package service;

import dao.UserDAO;

public class AuthService {

    private UserDAO dao = new UserDAO();

    public boolean register(String u,String p,String r){
        return dao.registerUser(u,p,r);
    }

    public boolean login(String u,String p,String r){
        return dao.loginUser(u,p,r);
    }
}