package platziPractic.fundamentos.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import platziPractic.fundamentos.entity.User;
import platziPractic.fundamentos.repository.UserRespository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {
    private final Log LOG = LogFactory.getLog(UserService.class);
    private UserRespository userRespository;

    public UserService(UserRespository userRespository) {
        this.userRespository = userRespository;
    }

    @Transactional
    public void saveTransactional(List<User> users){
        users
        .stream()
        .peek(user -> LOG.info("Insert: " + user))
        .forEach(userRespository::save);
    }

    public List<User> getAllUsers(){
        return  userRespository.findAll();
    }

    public User save(User newUser) {
        return userRespository.save(newUser);
    }

    public void delete(Long id) {
        userRespository.delete(new User(id));
    }

    public User update(User newUser, Long id) {
        return userRespository.findById(id)
                .map(
                        user -> {
                            user.setEmail(newUser.getEmail());
                            user.setName(newUser.getName());
                            user.setBirthDate(newUser.getBirthDate());
                            return userRespository.save(user);
                        }
                ).get();
    }
}
