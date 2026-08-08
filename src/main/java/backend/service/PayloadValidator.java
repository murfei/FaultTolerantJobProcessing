package backend.service;

import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Component
public class PayloadValidator {

    private final ObjectMapper mapper = new ObjectMapper();

    public boolean isValid(String payload) {
        try {
            mapper.readTree(payload);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
