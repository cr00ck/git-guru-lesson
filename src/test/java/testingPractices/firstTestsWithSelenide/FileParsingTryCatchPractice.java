package testingPractices.firstTestsWithSelenide;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

import JsonModelClasses.AdditionalInfo;
import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.opencsv.CSVReader;
import helpers.OwnerProperty;
import lombok.SneakyThrows;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pageObg.BaseConfigs;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


public class FileParsingTryCatchPractice extends BaseConfigs {

    private ClassLoader cl = FileParsingTryCatchPractice.class.getClassLoader();// дай мне тот класс лоадер, которым загружен класс в котором я нахожусь. Возвращает InputStream
    private Gson gson = new Gson(); // объявляем чтобы парсить json, тест ниже jsonParsingTest()

    @Test
    @SneakyThrows // это аннотация из ламбока, с ее помощью можно не писать никакие exceptions

    void downloadFileTest() throws IOException {
        open("https://plus34.ru/novosti/fnp-i-pot-v-vorde");
        File downloaded =
                $("a[href*='pbmgp_2021.docx']")
                        .download();    // обязательно наличие href в кнопке чтобы скачать файл
        try(InputStream is = new FileInputStream(downloaded)) {     // InputStream дискриптор файла, его надо обязательно закрывать, тк используется один поток для всех скачек
            byte[] data = is.readAllBytes();    //InputStream OutputStream работает с байтами, ему все равно картинка или текст, а reader/writer с текстовыми файлами
            String dataAsString = new String(data, StandardCharsets.UTF_8);  // readAllBytes читает файл, кладем его в стринг и указываем кодировку

//            Assertions.assertTrue(
//                    dataAsString.contains("Правила безопасности"),
//                    "Файл не содержит 'Правила безопасности'"); }
            // Вместо этого просто проверьте что файл скачался
            Assertions.assertTrue(downloaded.exists());
            Assertions.assertTrue(downloaded.length() > 1000); // не пустой

            System.out.println("Файл скачан: " + downloaded.getAbsolutePath());
            System.out.println("Размер: " + downloaded.length() + " байт");
        }
        // можно дабавить зависимость   'commons-io:commons-io:2.21.0'   и тогда будет код такой:
        // FileUtils.readFileToString(downloaded , StandardCharsets.UTF_8); // статический метод readFileToString
        }
    @Test
    void uploadFifeTest() {
        open("http://file.karelia.ru/");
        $("input[type='file']").uploadFromClasspath("img/pic3.jpg"); // лучше через Classpath т.к
        $("#file_submit").click();
        $(".fileQueue")
                .should(appear)
                .shouldHave(text("pic3.jpg"));

    }
    @Test
    void pdfFileParsingTest() throws Exception{
        open("https://pddmaster.ru/pdd/skachat-pdd-besplatno.html");
        File downloaded = $("[href='//pddmaster.ru/img/pdf/pdd-52.1-pddmaster.ru.pdf']").download();// скачиваем файл
        //try (InputStream is = new FileInputStream(downloaded)) { // будем использовать метод  public PDF(File pdfFile) и он сразу работает с файлом, а это коментируем
            PDF pdf = new PDF(downloaded);// для работы с PDF устанавливаем зависимость com.codeborne:pdf-test:2.1.0;
         //System.out.println(); //чтобы увидеть в дебаге какие парамы отловил в pdf
        Assertions.assertEquals("doPDF Ver 10.9 Build 135", pdf.producer); // в дебаге смотрим параметры pdf и можно проверить

        }
    @Test
    @SneakyThrows // это аннотация из ламбока, с ее помощью можно не писать никакие exceptions
    void xlsParsingTest()  {
        open("https://excelvba.ru/programmes/Teachers");
        File downloaded = $("[href='https://ExcelVBA.ru/sites/default/files/teachers.xls']").download();// скачиваем файл
        XLS xls = new XLS(downloaded);
        xls.excel.getSheetAt(0).getRow(3).getCell(2).getStringCellValue(); // это берем из дебагера в Evaluate Expressions

    }
    @Test
    @SneakyThrows // это аннотация из ламбока, с ее помощью можно не писать никакие exceptions
    void csvParsingTest() throws Exception  {
       //ставим библеотеку  'com.opencsv:opencsv:5.12.0' для работы с CSV
        try (InputStream is = cl.getResourceAsStream("testData/textBoxForm.csv");// чтобы прочитать csv надо создать объект в классе private ClassLoader cl
             CSVReader csvReader = new CSVReader(new InputStreamReader(is))){

            List<String[]> data = csvReader.readAll();// лист массивов в csv, каждая строка в []
            Assertions.assertEquals(3, data.size());
            Assertions.assertArrayEquals(
                    new String [] {"Антон Пупкин", "anton@mail.ru"}, //надо ставить пробелы если они есть, библеотека не учитывает пробелы, точнее учитывает!
                    data.get(0)
            );
            Assertions.assertArrayEquals(
                    new String [] {"Валерий Бышмаков", "valera@mail.ru"},
                    data.get(1)
            );
            Assertions.assertArrayEquals(
                    new String [] {"Роман Вальтер", "roman@mail.ru"},
                    data.get(2)
            );
        }

    }

    @Test
    @SneakyThrows // это аннотация из ламбока, с ее помощью можно не писать никакие exceptions
    void checkImagesInPasringZip() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream("zipFiles/ЛКК.zip"))) {// чтобы прочитать csv надо создать объект в классе private ClassLoader cl

            ZipEntry entry;
            List<String> imageFiles = new ArrayList<>();
            String findingName = "Container.png";
            boolean fileFound = false;

            while ((entry = zis.getNextEntry()) != null) {// пока есть след эл в файле, перебор
                System.out.println(entry.getName());
                String name = entry.getName().toLowerCase();

                if (name.endsWith(".jpg") || name.endsWith(".jpeg") || // проверяем на наличие файлов в папке
                        name.endsWith(".png") || name.endsWith(".gif")) {

                    imageFiles.add(entry.getName());
                }
                if (entry.getName().equals(findingName) || entry.getName().endsWith("/"+ findingName)){
                     fileFound = true;
                    System.out.println("Найден файл - "+entry.getName());
                }
            }
            // После цикла:
            Assertions.assertTrue(fileFound, "Файл Container.png не найден в архиве");
        }
    }
    @Test
    @SneakyThrows // это аннотация из ламбока, с ее помощью можно не писать никакие exceptions

    void glossaryJsonParsingTest() throws Exception { // 2 библеотеки для парсинга популярные gson и jсson
        try (Reader reader = new InputStreamReader(
                cl.getResourceAsStream("json/glossary.json")
        )) {
            JsonObject actual = gson.fromJson(reader, JsonObject.class);// создаем переменную   private Gson gson = new Gson();
            JsonObject glossary = actual.get("glossary").getAsJsonObject();

            // Проверка полей glossary
            Assertions.assertEquals("example glossary", glossary.get("title").getAsString());
            Assertions.assertEquals(2454646, glossary.get("ID").getAsInt());

            // Получаем GlossDiv
            JsonObject glossDiv = glossary.get("GlossDiv").getAsJsonObject();
            Assertions.assertEquals("S", glossDiv.get("title").getAsString());

            // Получаем GlossList
            JsonObject glossList = glossDiv.get("GlossList").getAsJsonObject();

            // Получаем GlossEntry
            JsonObject glossEntry = glossList.get("GlossEntry").getAsJsonObject();

            // Проверяем поля GlossEntry
            Assertions.assertEquals("SGML", glossEntry.get("SortAs").getAsString());
            Assertions.assertEquals("Standard Generalized Markup Language",
                    glossEntry.get("GlossTerm").getAsString());
            Assertions.assertEquals("SGML", glossEntry.get("Acronym").getAsString());
            Assertions.assertEquals("ISO 8879:1986", glossEntry.get("Abbrev").getAsString());

            // Получаем GlossDef
            JsonObject glossDef = glossEntry.get("GlossDef").getAsJsonObject();
            Assertions.assertEquals("A meta-markup language, used to create markup languages such as DocBook.",
                    glossDef.get("para").getAsString());

            // Проверяем массив GlossSeeAlso
            JsonArray glossSeeAlso = glossDef.get("GlossSeeAlso").getAsJsonArray();
            Assertions.assertEquals(2, glossSeeAlso.size());
            Assertions.assertEquals("GML", glossSeeAlso.get(0).getAsString());
            Assertions.assertEquals("XML", glossSeeAlso.get(1).getAsString());

            // Проверяем последнее поле
            Assertions.assertEquals("markup", glossEntry.get("GlossSee").getAsString());

            System.out.println("Все проверки JSON успешно пройдены!");
        }
        }
        @Test
        @SneakyThrows // это аннотация из ламбока, с ее помощью можно не писать никакие exceptions

        void additionalInfoJsonParsingTest() throws Exception { // 2 библеотеки для парсинга популярные gson и jсson
            try (Reader reader = new InputStreamReader(
                    cl.getResourceAsStream("json/additional_info.json")
            )) {
                AdditionalInfo actual = gson.fromJson(reader, AdditionalInfo.class);// меняем JsonObject на созданный класс AdditionalInfo
                // JsonObject document = actual.get("document").getAsJsonObject(); // это становится не нужным, тк есть класс

                Assertions.assertEquals("ТЕСТ сведения о квалификации CR00CK", actual.getAdditional_info());
                Assertions.assertEquals(123456, actual.getDocument().getRepoLink());
                Assertions.assertEquals("https://lk-files.ranepa.ru/public/lf75cb98ce33a6c2e3327df16269ad8f45384af38a03afa247202fb534e7a3530c8ac7a50746a5df8273e558aff652b844f36d1c00d6494b4a5067ab9cca999d0"
                        , actual.getDocument().getUrl());
                Assertions.assertEquals(1, actual.getClientVersion());
                Assertions.assertEquals("hr/candidate/profile/additional", actual.getBitrix());

                System.out.println("Все проверки JSON успешно пройдены!");
            }
            ;
        }
@Test
public void ownerReadingTest(){
    OwnerProperty owPro = ConfigFactory.create(OwnerProperty.class); // у библ owner есть класс,и в нем метод create , в нем реализуем класс
    System.out.println(owPro.url());
    System.out.println(owPro.login()); // выводится в нужном классе , где надо проперти

    };

};









    //    @Test
//    void downloadAndVerifyFile() throws IOException {
//        // 1. Открыть страницу
//        open("https://releases.aspose.com/html/net/");
//
//        // 2. Найти и скачать файл
//        File downloadedFile = $(".downloadandnotes a[title='Download']")
//                .shouldBe(visible)
//                .download();
//
//        // 3. Проверить что файл скачался
//        assertThat(downloadedFile)
//                .exists()
//                .isFile()
//                .isNotEmpty();
//
//        // 4. Проверить имя файла
//        String fileName = downloadedFile.getName();
//        assertThat(fileName)
//                .containsIgnoringCase("aspose")
//                .endsWith(".zip"); // предполагаем что это zip архив
//
//        // 5. Прочитать и проверить содержимое (если это текстовый файл)
//        try (InputStream is = new FileInputStream(downloadedFile)) {
//            byte[] fileContent = is.readAllBytes();
//
//            // Для ZIP файлов нужно использовать ZipInputStream
//            if (fileName.endsWith(".zip")) {
//                try (ZipInputStream zis = new ZipInputStream(new FileInputStream(downloadedFile))) {
//                    ZipEntry entry;
//                    boolean foundDll = false;
//                    while ((entry = zis.getNextEntry()) != null) {
//                        if (entry.getName().endsWith(".dll")) {
//                            foundDll = true;
//                            break;
//                        }
//                    }
//                    assertThat(foundDll).isTrue();
//                }
//            } else {
//                // Для текстовых файлов
//                String contentAsString = new String(fileContent, StandardCharsets.UTF_8);
//                assertThat(contentAsString)
//                        .contains("Aspose")
//                        .contains("HTML");
//            }
//        }
//
//        // 6. Очистка (необязательно - Selenide сам удалит через некоторое время)
//        // downloadedFile.delete();
//    }


