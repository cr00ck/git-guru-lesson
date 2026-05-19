package testingPractices.dogsPractice;

//Реализовать семейство псов, где будет 3 типа собаки
//Изначально в семействе 30 собак
//Нужно сделать так, чтобы в течении 1 года, выбиралась случайная собака и выполняла случайное действие.
//Каждый 30 день, в семействе должна пополняться 1 собака
//Нужно вывести на экран сколько псов было до и сколько стало после
//Использовать класс Random, Switch Case, If Else

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainForDogs {
static Random random= new Random();
    static List<Dogs> dogs = new ArrayList<>();

    public static void main(String[] args) {

        Volf volf1 = new Volf();
         volf1.setTail("Grey"); // доступно, тк в классе есть Volf аннотации lombok @Data
        String volf1BreedTail = volf1.getTail();
        volf1.setBreed("Grey"); // доступно, тк в классе Volf есть аннотации lombok @Data
        String volf1Breed = volf1.getBreed();// доступно, тк в классе Volf есть аннотации lombok @Data
        volf1Breed.equals(volf1BreedTail);// доступно, тк в классе Volf есть аннотации lombok @Data

        System.out.println(volf1Breed.equals(volf1BreedTail)); //true


        addDogs(30);
        System.out.println("Количество собак - "+ dogs.size());
        System.out.println("------------------------------------");

        for (int i = 1; i <= 365; i++) {
            randomDogDoRandomAct();
            System.out.println("День - "+ i+"  Количество собак - "+ dogs.size());
            if (i % 30 == 0){
                addDogs(1);
                System.out.println(">>> День " + i + ": Добавлена новая собака! Теперь их: " + dogs.size() + " <<<");
            }
        }
        System.out.println("Количество собак - "+ dogs.size());
    }

    public static void randomDogDoRandomAct() { // получение рандом индекса собак и рандом действия
            int randIndex = random.nextInt(dogs.size());
            Dogs dog = dogs.get(randIndex);
            dog.randomAct();
    }

        public static Dogs createRandomDog() { // заполнение собаки
                int rand = random.nextInt(3);
                return switch (rand) {
                    case 0 -> new Volf();
                    case 1 -> new Cayot();
                    case 2 -> new HomeDog();
                    default -> new HomeDog();
                };
            }
        public static void addDogs(int numberDogs){ //метод по количеству собак заполняет их в список
            for (int i = 0; i < numberDogs; i++) {
            dogs.add(createRandomDog());
            }
       }

}
