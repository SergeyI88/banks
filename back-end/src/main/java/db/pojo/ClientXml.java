package db.pojo;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.lang.reflect.Field;


public class ClientXml {
    static StringBuilder stringBuilder = new StringBuilder();
    private  static DocumentBuilderFactory factory;
    private  static DocumentBuilder builder;

    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException {
        UserPersonal userPersonal = new UserPersonal(1, "name1"
                , "name2", "name3"
                , "birthday", "man", "java" );
        UserData userData = new UserData(1, userPersonal, "login"
                , "pass", "date_reg" );
        Client client = new Client(1, userData, false);
        createXml(client.getClass(), client);
        System.out.println(stringBuilder.toString());

    }
    public static void createXml(Class<?> classSome, Object object) throws IllegalAccessException, NoSuchFieldException {
        Field[] fields =  classSome.getDeclaredFields();
        for (Field f : fields) {
            if (f.getType() == UserPersonal.class || f.getType() == UserData.class) {
                createXml(f.getType(), f.get(object));
            }
            stringBuilder.append("<");
            stringBuilder.append(f.getName().toString());
            stringBuilder.append(">");
            stringBuilder.append("\n");
            if (String.class == f.getType()) {
                String fieldValue = (String) f.get(object);
                stringBuilder.append("    ");
                stringBuilder.append(fieldValue);
            } else  {
                stringBuilder.append("    ");
                stringBuilder.append(f.get(object));
            }
            stringBuilder.append("\n");
            stringBuilder.append("<");
            stringBuilder.append(f.getName().toString());
            stringBuilder.append(">");
            stringBuilder.append("\n");
        }
    }
}
