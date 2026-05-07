package testingPractices.dogsPractice;

import lombok.Data;

import java.util.Random;
@Data
public class HomeDog extends Dogs {
    Random random = new Random();
    private String color;
    private String tail;

    String breed = "Good";

    public void say() {
        System.out.println("Gav-Gav");
    }

    public void sleep() {
        System.out.println("Very good");
    }

    public void eat() {
        System.out.println("Very tasty");

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
