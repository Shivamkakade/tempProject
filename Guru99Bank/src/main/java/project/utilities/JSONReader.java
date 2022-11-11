package project.utilities;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class JSONReader {

    public static List<HashMap<String, String>> getData(final String jsonFilePath) {
        List<HashMap<String, String>> data;
        try {
            //read JSON to String
            String fileContent = FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8);

            //Convert String to HashMap
            ObjectMapper mapper = new ObjectMapper();
            data = mapper.readValue(fileContent, new TypeReference<List<HashMap<String, String>>>() {
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return data;
    }
}
