package tetingPractices;
import org.junit.jupiter.api.Test;
import pageObg.BaseConfigsDemoqa;
import pageObg.RegistrationPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static dataFaker.DataFakerRamdom.*;

public class DemoqaPracticeForm_PO extends BaseConfigsDemoqa {
    // ctrl+shift+L жмем после кажного готового теста, чтобы оптимизировать и удалить все лишнее



    @Test
    void demoga_practice() {

        // Установки для теста
        BaseConfigsDemoqa.setUp();
        RegistrationPage registrationPage = new RegistrationPage();

        registrationPage.openPage();
        registrationPage
        // Заполняем поля с ожиданиями
                        .setFirstName(firstNameFaker)
                        .setLastName(lastNameFaker)
                        .setUserEmail(emailFaker)
                        .setGender(getRandomGender())
                        .setNumber(phoneFaker)
                        .setDateOfBirth("July","1999");
        registrationPage.setSubject(getRandomSubjectFromArray())
                        .setHobbies(getRandomHobbyFromArray())
                        .setFile()
                        .setAddress(addressFaker)
                        .setState(stateFaker)
                        .setCity(getRandomCityDependsOnState(stateFaker))
                        .clickSubmit()
        // Ждем появления модального окна

                                 .assertModalTable("Student Name","Kirill Skotings")
                                .assertModalTable("Student Email","ggg@mail.ru")
                                .assertModalTable("Gender","Male")
                                .assertModalTable("Mobile","1234567890")
                                .assertModalTable("Date of Birth","17 July,1999")
                                .assertModalTable("Subjects","Biology")
                                .assertModalTable("Hobbies","Reading")
                                .assertModalTable("Hobbies","Reading")
                                .assertModalTable("Picture", "img/Medal Star (1).png")
                                .assertModalTable("Address","Current Address")
                                .assertModalTable("State and City","Haryana Panipat");

    }}