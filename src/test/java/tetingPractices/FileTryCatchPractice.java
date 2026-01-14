package tetingPractices;

import static com.codeborne.selenide.Selenide.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pageObg.BaseConfigs;
import pageObg.BaseConfigsDemoqa;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileTryCatchPractice extends BaseConfigs {
    @Test
    void downloadFile() throws IOException {
        open("https://releases.aspose.com/html/net/?_gl=1*tc052k*_ga*MjcyNTE2MDc0LjE3NjgwMzE4NjA.*_ga_W0DG8XJWKL*czE3NjgwMzE4NjAkbzEkZzEkdDE3NjgwMzE4NjEkajYwJGwwJGgxNjk4MDg4NDEz*_gcl_au*MTUzOTM0MzQ0MC4xNzY4MDMxODYy");

        File downloaded = $(".downloadandnotes a[title='Download']").download(); // обязательно наличие href в кнопке чтобы скачать файл
        try(InputStream is = new FileInputStream(downloaded)) { // InputStream дискриптор файла, его надо обязательно закрывать, тк используется один поток
            byte[] data = is.readAllBytes();
            String dataAsString = new String(data, StandardCharsets.UTF_8);

            Assertions.assertFalse(dataAsString.isEmpty());
        }
    }
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
}

