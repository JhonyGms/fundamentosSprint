package platziPractic.fundamentos.component;

import org.springframework.stereotype.Component;

@Component("componentTwoImplement")
public class ComponentTwoImplement implements ComponentDependency {

    @Override
    public void saludar() {
        System.out.println("Hola mundo desde  dos");
    }
}
