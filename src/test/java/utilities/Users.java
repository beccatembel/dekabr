package utilities;

import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Users {

    /***
     * This method return email from Users.json
     * @param credentials
     * @throws IOException
     */
    public String usersEmail(String credentials) {
        File file = new File("src//test//resources//data//Users.json");
        String userJSON = null;
        try {
            userJSON = new String(FileUtils.readFileToByteArray(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();

        Map map = gson.fromJson(userJSON, Map.class);
        List<Map<String, String>> users = new ArrayList<>();
        users.add((Map<String, String>) map.get(credentials));

        return users.get(0).get("email");
    }

    /***
     * This method return password from Users.json
     * @param credentials
     * @return
     * @throws IOException
     */
    public String usersPassword(String credentials) {
        File file = new File("src/test/resources/data/Users.json");
        String userJSON = null;
        try {
            userJSON = new String(FileUtils.readFileToByteArray(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();

        Map map = gson.fromJson(userJSON, Map.class);
        List<Map<String, String>> users = new ArrayList<>();
        users.add((Map<String, String>) map.get(credentials));

        return users.get(0).get("password");
    }
}
