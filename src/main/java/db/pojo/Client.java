package db.pojo;

public class Client {
    public int id;
    public int id_user_data;
    public UserData userData;
    public boolean vip;

    public Client(int id, int id_user, boolean vip) {
        this.id = id;
        this.id_user_data = id_user;
        this.vip = vip;
    }

    public Client(int id, UserData userData, boolean vip) {
        this.id = id;
        this.userData = userData;
        this.vip = vip;
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

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }
}
