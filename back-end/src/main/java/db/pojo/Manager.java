package db.pojo;

import java.io.Serializable;

public class Manager implements Serializable{
    private int id;
    private int id_user_data;
    private UserData userData;

    public Manager(int id, int id_user_data) {
        this.id = id;
        this.id_user_data = id_user_data;
    }

    public Manager(int id, UserData userData) {
        this.id = id;
        this.userData = userData;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user_data() {
        return id_user_data;
    }

    public void setId_user_data(int id_user_data) {
        this.id_user_data = id_user_data;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }
}
