package backend.infrastructure;

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
            System.out.println("PayloadValidator: Payload ist ungültig");
            return false;
        }
    }
}
