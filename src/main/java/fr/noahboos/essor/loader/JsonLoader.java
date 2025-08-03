package fr.noahboos.essor.loader;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import fr.noahboos.essor.component.ExperienceRange;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.Map;

public class JsonLoader {
    public static Map<String, ExperienceRange> LoadExperienceData(String path) {
        try (FileReader reader = new FileReader(path)) {
            Gson gson = new Gson();
            Type type = new TypeToken<Map<String, ExperienceRange>>() {
            }.getType();
            return gson.fromJson(path, type);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }
}
