package Visionire;

import Visionire.Utility.Log;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author nickbabenko
 */
public class Config {

    public static JsonObject get(String ref) {
        String file = ref;
        String ref_split[] = null;

        if (ref.indexOf('.') > -1) {
            ref_split = ref.split("\\.");
            file = ref_split[0];
        }

        StringBuilder sb = new StringBuilder();
        String file_path = System.getProperty("user.dir") + "\\config\\" + file + ".json";

        try {
            File f = new File(file_path);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);

            String eachLine = br.readLine();

            while (eachLine != null) {
                sb.append(eachLine);
                sb.append("\n");

                eachLine = br.readLine();
            }
        }

        catch (IOException e) {
            Log.out(Log.ERROR, "Unable to load config file at: " + file_path, Config.class.getName());
            e.printStackTrace();
        }

        JsonObject response = new JsonParser().parse(sb.toString()).getAsJsonObject();

        if (ref_split != null) {
            for (int i = 1; i < ref_split.length; i++) response.get(ref_split[i]);
        }

        return response;

    }
}
