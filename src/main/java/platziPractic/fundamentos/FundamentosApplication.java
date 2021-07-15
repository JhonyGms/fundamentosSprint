package platziPractic.fundamentos;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;
import platziPractic.fundamentos.bean.MyBean;
import platziPractic.fundamentos.bean.MyBeanWithDependency;
import platziPractic.fundamentos.bean.MyBeanWithProperties;
import platziPractic.fundamentos.component.ComponentDependency;
import platziPractic.fundamentos.entity.User;
import platziPractic.fundamentos.pojo.UserPojo;
import platziPractic.fundamentos.repository.UserRespository;
import platziPractic.fundamentos.service.UserService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	private final Log LOGGER = LogFactory.getLog(FundamentosApplication.class);

	private ComponentDependency componentDependency;
	private MyBean miBean;
	private MyBeanWithDependency myBeanWithDependency;
	private MyBeanWithProperties myBeanWithProperties;
	private UserPojo userPojo;
	private UserRespository userRespository;
	private UserService userService;

	public FundamentosApplication(UserService userService,UserRespository userRespository,UserPojo userPojo,MyBeanWithProperties myBeanWithProperties,MyBeanWithDependency myBeanWithDependency,@Qualifier("componentTwoImplement") ComponentDependency componentDependency, MyBean miBean) {
		this.myBeanWithProperties = myBeanWithProperties;
		this.myBeanWithDependency = myBeanWithDependency;
		this.componentDependency = componentDependency;
		this.miBean = miBean;
		this.userPojo = userPojo;
		this.userRespository = userRespository;
		this.userService = userService;
	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		ejemplosAnteriores();
		saveUsersInDataBase();
		getInformationJpqlFromUser();
		saveWithErrorTransaccional();
	}

	private void saveWithErrorTransaccional(){
		User test1 = new User("TestTrancaccioanl1","TestTrancaccioanl1@domain.co",LocalDate.now());
		User test2 = new User("TestTrancaccioanl2","TestTrancaccioanl2@domain.co",LocalDate.now());
		User test3 = new User("TestTrancaccioanl3","TestTrancaccioanl1@domain.co",LocalDate.now());
		User test4 = new User("TestTrancaccioanl4","TestTrancaccioanl4@domain.co",LocalDate.now());

		List<User> users = Arrays.asList(test1,test2,test3,test4);

		try {
			userService.saveTransactional(users);
		}catch (Exception err){
			LOGGER.error("este es un error dentro del metiodi trancaccional" + err);
		}
		userService.getAllUsers()
				.stream()
				.forEach(user -> LOGGER.info("Usuario transacciobnal" + user));
	}

	private void getInformationJpqlFromUser(){
//		LOGGER.info("Usurious con el method findByUserEmail" +
//				userRespository.findByUserEmail("john@domain.co")
//					.orElseThrow(() -> new RuntimeException("NO se Encontre el usurious")));
//		userRespository.findAndSort("dani", Sort.by("id").descending())
//				.stream()
//				.forEach(user -> LOGGER.info("User with method sort" + user));
//		userRespository.findByName("Jonh")
//				.stream()
//				.forEach(user -> LOGGER.info("Usuario con query method " + user));
//
//		LOGGER.info("Usuario con QWuery method find name and mail" +
//				userRespository.findByEmailAndName("perez@domain.co","perez")
//						.orElseThrow(() -> new RuntimeException("Usuario no existe")));
//		userRespository.findByNameLike("%j%")
//				.stream()
//				.forEach(user -> LOGGER.info("Usuario for like query" + user));
//		userRespository.findByNameOrEmail(null, "john@domain.co")
//				.stream()
//				.forEach(user -> LOGGER.info("Usuario for like findByNameOrEmail" + user));
		userRespository.findByBirthDateBetween(LocalDate.of(2021,3,1), LocalDate.of(2021,07,22))
				.stream()
				.forEach(user -> LOGGER.info("Usuarios con findByBirthDateBetween " + user));
		userRespository.findByNameLikeOrderByIdDesc("%daniel%")
				.stream()
				.forEach(user -> LOGGER.info("usurios for findByNameLikeOrderByIdDesc " + user));
//		LOGGER.info("EL usuario apartir el name parametre "+
//				userRespository.getAllByBirthDateAndEmail(LocalDate.of(2021,03,20),
//						"john@domain.co")
//					.orElseThrow(() ->
//						new RuntimeException("NO se encontro el usuario a partir del named paramentro")));


	}

	private void saveUsersInDataBase(){
		User user1 = new User("Jonh", "john@domain.co", LocalDate.of(2021,03,20));
		User user2 = new User("Julietnh", "juliehn@domain.co", LocalDate.of(2021,04,20));
		User user3 = new User("daniel", "daniel@domain.co", LocalDate.of(2021,05,11));
		User user4 = new User("perez", "perez@domain.co", LocalDate.of(2021,06,14));
		User user5 = new User("ricardo", "ricardo@domain.co", LocalDate.of(2021,07,13));
		User user6 = new User("daniilo", "camilo@domain.co", LocalDate.of(2020,8,20));
		User user7 = new User("jose", "jose@domain.co", LocalDate.of(2020,9,20));
		User user8 = new User("josefina", "josefina@domain.co", LocalDate.of(2021,10,16));
		User user9 = new User("gerald", "gerald@domain.co", LocalDate.of(2021,03,3));
		User user10 = new User("pepe", "pepe@domain.co", LocalDate.of(2021,03,2));
		List<User> list = Arrays.asList(user1,user2,user3,user4,user5,user6,user7,user8,user9,user10);
		list.stream().forEach(userRespository::save);
	}

	private void ejemplosAnteriores(){
		componentDependency.saludar();
		miBean.print();
		myBeanWithDependency.printWhitDependecy();
		System.out.println(myBeanWithProperties.function());
		System.out.println(userPojo.getMail() + "-" + userPojo.getPassword() + "+" + userPojo.getAge());
		try {
			int value = 10/0;
			LOGGER.debug("MI valor :" + value);
		} catch ( Exception e){
			LOGGER.error("Este es un error al dividir por cero " + e.getMessage());

		}
	}
}
