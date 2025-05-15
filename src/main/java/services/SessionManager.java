package services;

import models.dto.Perdoruesit.Perdoruesit;

public class SessionManager {
    private static SessionManager instance;
    private Perdoruesit user;
    private int tempVeturaId;
    private int tempUserId;

    public static SessionManager getInstance(){
        if(instance==null){
            instance=new SessionManager();
        }
        return instance;
    }
    public void loginUser(Perdoruesit user){
        this.user=user;
    }
    public Perdoruesit getcurrentUser(){
        return this.user;
    }
    public void logout(){
        this.user=null;
    }
    public boolean isLoggedIn(){
        return this.user !=null;
    }
    public void setTempVeturaId(int id){
        this.tempVeturaId = id;
    }
    public int getTempVeturaId(){
        return tempVeturaId;
    }

    public void setTempUserId(int id){
        this.tempUserId = id;
    }
    public int getTempUserId(){
        return tempUserId;
    }
}
