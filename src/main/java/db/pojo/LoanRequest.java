package db.pojo;

public class LoanRequest {
    private int id;
    private int id_client;
    private Client client;
    private int sum;
    private int duration;
    private Boolean approved;
    private int id_manager;
    private Manager manager;

    public LoanRequest(int id, int id_client, int sum, int duration, Boolean approved
            , int id_manager) {
        this.id = id;
        this.id_client = id_client;
        this.sum = sum;
        this.duration = duration;
        this.approved = approved;
        this.id_manager = id_manager;
    }

    public LoanRequest(int id, Client client, int sum, int duration, Boolean approved, Manager manager) {
        this.id = id;
        this.client = client;
        this.sum = sum;
        this.duration = duration;
        this.approved = approved;
        this.manager = manager;
    }

    public int getId_manager() {
        return id_manager;
    }

    public void setId_manager(int id_manager) {
        this.id_manager = id_manager;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }
}
