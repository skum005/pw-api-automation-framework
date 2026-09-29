package utils.parsers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONParser {

    public String serializeToJSON(Object object) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (JsonProcessingException processingException) {
            throw new RuntimeException(processingException);
        } catch (Exception exception) {
            throw new RuntimeException("Failed to convert the given object to JSON string" + exception.getMessage());
        }
    }

    public <T> T deserializeJSONToJavaObject(String jsonString, Class<T> type) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(jsonString, type);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new RuntimeException("Failed to convert the given JSON string to Java Object of type " + type + ". " + exception.getMessage());
        }
    }

}
