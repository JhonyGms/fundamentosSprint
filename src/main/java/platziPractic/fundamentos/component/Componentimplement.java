package platziPractic.fundamentos.component;

import org.springframework.stereotype.Component;

@Component
public class Componentimplement implements ComponentDependency {

    @Override
    public void saludar() {
        System.out.println("Hola mundo desde micomponente");
    }
}
