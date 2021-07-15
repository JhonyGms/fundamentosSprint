package platziPractic.fundamentos.caseuse;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import platziPractic.fundamentos.service.UserService;

@Component
public class DeleteUser {
    private UserService userService;

    public DeleteUser(UserService userService) {
        this.userService = userService;
    }

    public void remove(Long id) {
        userService.delete(id);
    }
}
