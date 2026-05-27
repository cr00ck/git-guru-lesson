package selenide;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import pageObg.BaseConfigsDemoqa;
import pageObg.TextBoxPage;

import static dataFaker.DataFakerRamdom.*;
import static dataFaker.DataFakerRamdom.getRandomAddress;

public class AnnotationsExamples  extends BaseConfigsDemoqa {


        // ctrl+shift+L жмем после каждого готового теста, чтобы оптимизировать и удалить все лишнее

        // используем кастомные методы для генерации данных
        String fullName = getFullName();
        String email = getRandomEmail();
        String currentAddress = getRandomAddress();
        String permanentAddress = getRandomAddress();
        @Test
        @CsvSource(value = { // Работает с несколькими аргументами, подставляются по очереди один в первый второй во второй аргумент на вход в методе
                "Антон Пупкин, anton@mail.ru",
                "Валерий Бышмаков, valera@mail.ru",
                "Роман Вальтер, roman@mail.ru"
        }, delimiter = '|') // если надо чтото вставить с запятой,например так:
//        "Антон Пупкин, anton@mail.ru,com" |
//                "Валерий Бышмаков, valera@mail.ru",
        // то делиметр становится разделителем
        @CsvFileSource(resources = "/testData/textBoxForm.csv") // еще вариант для тестовых даных. Путь к файлу с тестовыми данными
        @ValueSource(strings = { // внутри нотации есть классы,нужный вставляем в тест. Рабтает только с одним аргументом!!!
                "Антон Пупкин",
                "Валерий Бышмаков",
                "Роман Вальтер"
        }
        )
        //MethodSource наборы
//        static Stream<Arguments> textBoxForm(){ // название должно совпадать с классом, если другое то надо прописывать в @MethodSource ("Другое название ")
//                Stream.of(   //надо тупо запомнить эту форму
//                        Arguments.of(RanepaChanels.VK, List.of("кнопка 1","кнопка 2","кнопка 2")); // если три набора, то три Arguments.of
//                        Arguments.of(RanepaChanels.TELEGRAM, List.of("кнопка 1","кнопка 2","кнопка 2");// надо еще в методе второй аргумент указывать List<String>
//                        Arguments.of(RanepaChanels.RUUTUBE, List.of("кнопка 1","кнопка 2","кнопка 2");
//                )
//        }
        @MethodSource // позволяет написать любую аннотацию через него
        @ParameterizedTest(name = "Тест с введением ФИО {0} и мэйл {1} и других данных в карточке")     // нужна для использования @CsvSource и @ValueSource, в {0} вставляется из @ValueSource(strings значения или @CsvSource(value = где есть {0}{1}
        @Tags({                 // будет запускать и с тем и с другим тэгом
                @Tag("HTML"),
                @Tag("PRACTICE")
        })
       // @DisplayName("Тест с введением ФИО и других данных в карточке")
        @Disabled("тут надо писать тикет бага в ТМС, тест будет виден как неактивный")
        void textBoxForm(String nameSurname, String emailValue) { // входные данные strings из @ValueSource. В @ParameterizedTest будет {0} т.к один аргумент на вход, а так нужно использовать @CsvSource
            TextBoxPage textbopage = new TextBoxPage();
            // Установки для теста
            BaseConfigsDemoqa.setUp();
            textbopage.openPage()
                    .setFullName(nameSurname) // сюда будут подставляться из @ValueSource значения или @CsvSource если несколько аргументов на вход
                    .setEmail(emailValue) // сюда будут подставляться из @ValueSource значения или @CsvSource если несколько аргументов на вход
                    .setCurrentAddress(currentAddress)
                    .setPermanentAddress(permanentAddress)
                    .clickSubmit()
                    // Проверки в таблице по пунктам
                    .assertInsideTable(fullName)
                    .assertInsideTable(email)
                    .assertInsideTable(currentAddress)
                    .assertInsideTable(permanentAddress);

        }
    }

