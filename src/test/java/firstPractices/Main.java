package firstPractices;

public class Main {
    // public static void main(String[] args) {
     public  void math () {


        short c = 0;
        byte d = 127;
        c = (short) (d + 1);
        System.out.println(c);

        //операции с комбинированными типами данных
        int bInt = 9;
        double bDouble = 0.1;
        System.out.println(bInt+bDouble);

        System.out.println(bInt/bDouble);

        System.out.println(bInt%bDouble);
    }

}
