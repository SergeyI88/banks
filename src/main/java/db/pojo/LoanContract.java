package db.pojo;

public class LoanContract {
    private int id;
    private int id_request;
    private LoanRequest loanRequest;
    private int manager_id;
    private Manager manager;
    private int status;
    private String statusObject;
    private String date_start;
    private String date_end;
    private int sum;
    private int id_client;
    private Client client;

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

    public int getManager_id() {
        return manager_id;
    }

    public void setManager_id(int manager_id) {
        this.manager_id = manager_id;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public LoanContract(int id, LoanRequest request, String status
            , Manager manager, String date_start, String date_end, int sum, Client client) {
        this.id = id;
        this.loanRequest = request;
        this.statusObject = status;
        this.date_start = date_start;
        this.date_end = date_end;
        this.sum = sum;
        this.manager = manager;
        this.client = client;
    }

    public LoanContract(int id, int id_request, String status
            , Manager manager, String date_start, String date_end, int sum, Client client) {
        this.id = id;
        this.id_request = id_request;
        this.manager = manager;
        this.statusObject = status;
        this.date_start = date_start;
        this.date_end = date_end;
        this.sum = sum;
        this.client = client;
    }

    public LoanContract(int manager_id, int id, int id_request, int status, String date_start, String date_end, int sum, int client) {

        this.id = id;
        this.id_request = id_request;
        this.status = status;
        this.date_start = date_start;
        this.date_end = date_end;
        this.sum = sum;
        this.manager_id = manager_id;
        this.id_client = client;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_request() {
        return id_request;
    }

    public void setId_request(int id_request) {
        this.id_request = id_request;
    }

    public LoanRequest getLoanRequest() {
        return loanRequest;
    }

    public void setLoanRequest(LoanRequest loanRequest) {
        this.loanRequest = loanRequest;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusObject() {
        return statusObject;
    }

    public void setStatusObject(String statusObject) {
        this.statusObject = statusObject;
    }

    public String getDate_start() {
        return date_start;
    }

    public void setDate_start(String date_start) {
        this.date_start = date_start;
    }

    public String getDate_end() {
        return date_end;
    }

    public void setDate_end(String date_end) {
        this.date_end = date_end;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
