package platziPractic.fundamentos.caseuse;

import org.springframework.stereotype.Component;
import platziPractic.fundamentos.service.UserService;

@Component
public class CreateUser {
    private UserService userService;

    public CreateUser(UserService userService) {
        this.userService = userService;
    }
}
