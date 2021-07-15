package platziPractic.fundamentos.caseuse;

import platziPractic.fundamentos.entity.User;
import platziPractic.fundamentos.service.UserService;

import java.util.List;

public class GetUserImplement implements GetUser {
    private UserService userService;

    public GetUserImplement(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<User> getAll() {
        return userService.getAllUsers();
    }
}
