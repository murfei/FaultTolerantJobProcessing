package backend.infrastructure;

import org.springframework.stereotype.Component;

@Component
public class NoOpTestHook implements TestHook{
    @Override
    public void afterStatusChange() {
    }
}
