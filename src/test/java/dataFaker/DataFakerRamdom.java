package dataFaker;

import net.datafaker.Faker;

import java.security.SecureRandom;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public  class DataFakerRamdom {

    public static Faker faker = new Faker(new Locale("en", "US"));

    public static String fullNameFaker = faker.name().fullName(),
    // Было (одно значение на всю жизнь класса), а если вынести это в метод,
    // то будет каждый раз новое генерироваться
     firstNameFaker = faker.name().firstName(),
     lastNameFaker = faker.name().lastName(),
     emailFaker = faker.internet().emailAddress(),
     currentAddressFaker = faker.address().fullAddress(),
     permanentAddressFaker = faker.address().fullAddress(),
     phoneFaker = faker.phoneNumber().subscriberNumber(10), // "1234567890"
     addressFaker = faker.address().fullAddress(),// "123 Main St, City"
     stateFaker = getRandomState(); // Лучшие поместить в метод, чтобы выпадало всегда новое значени,
    // тк если в переменной будет всегда одно и тоже во всех тестах выпадать.
    public static String getRandomState() {
        return faker.options().option("NCR", "Uttar Pradesh","Haryana","Rajasthan");  // Случайно при КАЖДОМ вызове
    }
    public static String getRandomString(int length) {
        String ABC = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        SecureRandom srnd = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(ABC.charAt(srnd.nextInt(ABC.length())));
        }
        return sb.toString();
    }

    public static int getRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max+1);
    }
    public static String getRandomPhone10Num() {
        return faker.numerify("##########");
    }

    public static String getFullName() {
        return getRandomString(5)+ " " + getRandomString(8);
    }
    public static String getName() {
        return getRandomString(5);
    }
    public static String getLastName() {
        return getRandomString(8);
    }

    public static String getRandomEmail() {
            return getRandomString(10)+ "@mail.ru";
    }

    public static String getRandomAddress() {
            return "City :" + " " + getRandomString(8)+ " ,street :" + " " + getRandomString(10)+ " ,house :" + " " + getRandomInt(1,30);
    }
    public static String getRandomPhoneNum() {
        // +7 (234) 45 - 78
            return String.format("+%s (%s) %s - %s - %s", getRandomInt(1,9), getRandomInt(111,999),
                    getRandomInt(111, 999), getRandomInt(11,99), getRandomInt(11,99));
    }
    // два метода работают вместе
    public static String getRandomGender() {
        String[] genders = {"Male", "Female","Other"};
        return getRandomItemFromArray(genders);
    }
    public static String getRandomItemFromArray(String[] array) {
        int index = getRandomInt(0,array.length - 1); // длина массива 3, но индексы идут с 0, поэтому надо один убрать
        return array[index];
    }
    // два метода работают вместе
    public static String getRandomHobbyFromArray() {
        String[] hobbys = {"Sports", "Reading","Music"};
        return getRandomItemFromArray(hobbys);
    }
    // два метода работают вместе
    public static String getRandomSubjectFromArray() {
        String[] subject = {"Biology", "English","Chemistry","History","Maths","Hindi","Economics"};
        return getRandomItemFromArray(subject);
    }

    // два метода работают вместе
    public static String getRandomCityDependsOnState(String state) {
        if(state.equals("NCR")){
            return faker.options().option("Delhi","Gurgaon","Noida");
        } else if (state.equals("Uttar Pradesh")){
            return faker.options().option("Agra","Lucknow","Merrut");
        } else if (state.equals("Haryana")) {
            return faker.options().option("Karnal", "Panipat");
        }
        else return "Unknown";
    }

        public static String getRandomUUID() {
            return UUID.randomUUID().toString();
        }



}
