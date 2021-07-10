package platziPractic.fundamentos.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import platziPractic.fundamentos.bean.*;

@Configuration
public class MyConfigurationBean {
    @Bean
    public MyBean beeanOperation(){
        return new MyBean2Implement();
    }

    @Bean
    public NewOperation beeanOperationOperation(){
        return new MyOperationImplement();
    }

    @Bean
    public MyBeanWithDependency beeanOperationOperationDWithDependency( NewOperation newOperation ){
        return new MyBeanWithDependencyImplement(newOperation);
    }
}
