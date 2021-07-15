package platziPractic.fundamentos.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import platziPractic.fundamentos.caseuse.CreateUser;
import platziPractic.fundamentos.caseuse.DeleteUser;
import platziPractic.fundamentos.caseuse.GetUser;
import platziPractic.fundamentos.caseuse.UpdateUser;
import platziPractic.fundamentos.entity.User;
import platziPractic.fundamentos.repository.UserRespository;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private GetUser getUser;
    private CreateUser createUser;
    private DeleteUser deleteUser;
    private UpdateUser updateUser;
    private UserRespository userRespository;

    public UserRestController(UserRespository userRespository,UpdateUser updateUser,DeleteUser deleteUser,CreateUser createUser,GetUser getUser) {
        this.getUser = getUser;
        this.createUser = createUser;
        this.deleteUser = deleteUser;
        this.updateUser = updateUser;
        this.userRespository = userRespository;
    }
    @GetMapping("/")
    List<User> get(){
        return getUser.getAll();
    }

    @PostMapping("/")
    ResponseEntity<User> newUser(@RequestBody User newUser){
        return new ResponseEntity<>(createUser.save(newUser), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    ResponseEntity deleteUser(@PathVariable Long id){
        deleteUser.remove(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    ResponseEntity<User> replaceUser(@RequestBody User newUser,@PathVariable Long id){
        return new ResponseEntity<>(updateUser.update(newUser, id), HttpStatus.OK) ;
    }

    @GetMapping("/pageable")
    List<User> getUserPageable(@RequestParam int page, @RequestParam int size){
        return userRespository.findAll(PageRequest.of(page, size)).getContent();
    }
}
