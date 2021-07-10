package platziPractic.fundamentos.bean;

public class MyBeanWithDependencyImplement implements MyBeanWithDependency {

    private NewOperation myOperation;

    public MyBeanWithDependencyImplement(NewOperation myOperation) {
        this.myOperation = myOperation;
    }

    @Override
    public void printWhitDependecy() {
        int numero = 1;
        System.out.println(myOperation.sum(numero));
        System.out.println("hola desde la implemetacion de un bean con dependecia");
    }
}
