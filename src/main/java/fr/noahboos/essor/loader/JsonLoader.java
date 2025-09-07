package fr.noahboos.essor.loader;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import fr.noahboos.essor.component.challenge.Challenge;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.Map;

public class JsonLoader {
    public static Map<String, Float> LoadExperienceData(String path) {
        try (InputStream stream = JsonLoader.class.getClassLoader().getResourceAsStream(path)) {
            if (stream == null) {
                throw new  RuntimeException("Failed to load experience data from " + path);
            }
            Reader reader = new InputStreamReader(stream);
            Gson gson = new Gson();
            Type type = new TypeToken<Map<String, Float>>(){}.getType();
            return gson.fromJson(reader, type);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public static Map<Integer, Map<String, Integer>> LoadRewardData(String path) {
        try (InputStream stream = JsonLoader.class.getClassLoader().getResourceAsStream(path)) {
            if (stream == null) {
                throw new  RuntimeException("Failed to load reward data from " + path);
            }
            Reader reader = new InputStreamReader(stream);
            Gson gson = new Gson();
            Type type = new TypeToken<Map<Integer, Map<String, Integer>>>(){}.getType();
            return gson.fromJson(reader, type);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public static Challenge LoadChallenge(String file) {
        String path = "data/essor/challenge/" + file;

        try (InputStream stream = JsonLoader.class.getClassLoader().getResourceAsStream(path)) {
            if (stream == null) {
                throw new  RuntimeException("Failed to load challenge data from " + path);
            }
            Reader reader = new InputStreamReader(stream);
            Gson gson = new Gson();
            Type type = new TypeToken<Challenge>(){}.getType();
            return gson.fromJson(reader, type);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }
}
