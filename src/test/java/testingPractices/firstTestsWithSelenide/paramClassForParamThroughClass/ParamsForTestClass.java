package testingPractices.firstTestsWithSelenide.paramClassForParamThroughClass;



public class ParamsForTestClass {
    private String name;
    private String email;

    public ParamsForTestClass(String name, String email) {
        this.name = name;
        this.email = email;
    }
    // геттеры
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
    // сеттеры
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override // нужно это сделать чтобы в RanepaSiteAnnotationPractice выводились норм значения в тесте loginThroughParamClass
    public String toString() {
        return "ParamsForTestClass{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}