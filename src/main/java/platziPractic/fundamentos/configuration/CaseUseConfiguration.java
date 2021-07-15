package platziPractic.fundamentos.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import platziPractic.fundamentos.caseuse.GetUser;
import platziPractic.fundamentos.caseuse.GetUserImplement;
import platziPractic.fundamentos.service.UserService;

@Configuration
public class CaseUseConfiguration {

    @Bean
    GetUser getUser(UserService userService){
        return new GetUserImplement(userService);
    }
}
