package testingPractices.dogsPractice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Random;

@Data // @Setter @Getter доступно и можно не писать , аннотации lombok
@AllArgsConstructor //аннотации lombok
@NoArgsConstructor //аннотации lombok
public class Volf extends Dogs{
    Random random = new Random();

@Setter
public String breed = "Grey";
    public String color;
    public String tail;


    public void say(){
    System.out.println("Voooooo");
}
public void sleep(){
    System.out.println("Less");
}
public void eat(){
    System.out.println("Little");
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
