package platziPractic.fundamentos;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import platziPractic.fundamentos.bean.MyBean;
import platziPractic.fundamentos.bean.MyBeanWithDependency;
import platziPractic.fundamentos.bean.MyBeanWithProperties;
import platziPractic.fundamentos.component.ComponentDependency;
import platziPractic.fundamentos.pojo.UserPojo;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	private ComponentDependency componentDependency;
	private MyBean miBean;
	private MyBeanWithDependency myBeanWithDependency;
	private MyBeanWithProperties myBeanWithProperties;
	private UserPojo userPojo;

	public FundamentosApplication(UserPojo userPojo,MyBeanWithProperties myBeanWithProperties,MyBeanWithDependency myBeanWithDependency,@Qualifier("componentTwoImplement") ComponentDependency componentDependency, MyBean miBean) {
		this.myBeanWithProperties = myBeanWithProperties;
		this.myBeanWithDependency = myBeanWithDependency;
		this.componentDependency = componentDependency;
		this.miBean = miBean;
		this.userPojo = userPojo;
	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		componentDependency.saludar();
		miBean.print();
		myBeanWithDependency.printWhitDependecy();
		System.out.println(myBeanWithProperties.function());
		System.out.println(userPojo.getMail() + "-" + userPojo.getPassword() + "+" + userPojo.getAge());
	}
}
