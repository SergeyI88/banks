package db.pojo;

public class XmlParser {
    public static void main(String[] args) {
        UserPersonal userPersonal = new UserPersonal(1, "name1"
                , "name2", "name3"
                , "birthday", "man", "java" );
        UserData userData = new UserData(1, userPersonal, "login"
                , "pass", "date_reg" );
        Client client = new Client(1, userData, false);
        createXml(client.getClass());
    }

    private static void createXml(Class<? extends Client> aClass) {
    }
}
