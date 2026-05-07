package testingPractices.dogsPractice;

import lombok.Data;

import java.util.Random;
@Data
public class Cayot extends Dogs {
    Random random = new Random();
    private String color;
    private String tail;

    String breed = "Series";

    public void say() {
        System.out.println("hoohohooo");
    }

    public void sleep() {
        System.out.println("Medium");
    }

    public void eat() {
        System.out.println("A lot");
    }

    @Override
    public void randomAct() {
        int randAct = random.nextInt(3);
        switch (randAct){
            case 0-> say();
            case 1-> sleep();
            case 2-> eat();
        }
    }
}
