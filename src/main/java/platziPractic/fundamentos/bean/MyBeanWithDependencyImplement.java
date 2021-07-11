package platziPractic.fundamentos.bean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MyBeanWithDependencyImplement implements MyBeanWithDependency {

    private final Log LOGGER = LogFactory.getLog(MyBeanWithDependencyImplement.class);

    private NewOperation myOperation;

    public MyBeanWithDependencyImplement(NewOperation myOperation) {
        this.myOperation = myOperation;
    }

    @Override
    public void printWhitDependecy() {
        LOGGER.info("Hemos ingresdado al metodo MyBeanWithDependencyImplement");
        int numero = 1;
        LOGGER.debug("El numero enviadop como parametro a la dependecina a la operacion es :" + numero);
        System.out.println(myOperation.sum(numero));
        System.out.println("hola desde la implemetacion de un bean con dependecia");
    }
}
