package platziPractic.fundamentos.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import platziPractic.fundamentos.dto.UserDto;
import platziPractic.fundamentos.entity.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRespository extends PagingAndSortingRepository<User, Long> {

    @Query("Select u from User u where u.email=?1")
    Optional<User> findByUserEmail(String email);

    @Query("Select u from User u where u.name like ?1%")
    List<User> findAndSort(String name, Sort sort);

    List<User> findByName(String name);

    Optional<User> findByEmailAndName(String email,String name);

    List<User> findByNameLike(String name);

    List<User> findByNameOrEmail(String name,String email);

    List<User> findByBirthDateBetween(LocalDate begin, LocalDate end);

    List<User> findByNameLikeOrderByIdDesc(String name);

    List<User> findAll();

//    @Query("SELECT new platziPractic.fundamentos.dto.UserDto(u.id, u.name, u.birth_date)"+
//            "FROM User u " +
//            "where u.birth_date=:parametroFecha" +
//            "and u.email=:parametroEmail")
//    Optional<UserDto> getAllByBirthDateAndEmail(@Param("parametroFecha") LocalDate date,
//                                                @Param("parametroEmail") String mail);

}
