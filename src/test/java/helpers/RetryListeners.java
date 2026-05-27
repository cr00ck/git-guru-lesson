package helpers;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

public class RetryListeners implements TestExecutionExceptionHandler, AfterTestExecutionCallback {
    // метод смотрит на все падение тестов и прогоняет их 3 раза, актуально для мобильных тестов и UI т.к они не стабильные
    // используется в виде аннотации над нужным классом @ExtendWith(RetryListeners.class)
    private static final int MAX_RETRIES = 3;
    private static final Set<String> failedTestsNames = new HashSet<>();


    @Override
    public void afterTestExecution(ExtensionContext extensionContext) throws Exception {
        Method getMethod = extensionContext.getRequiredTestMethod();
        String methodName = getMethod.getName();
        String className = extensionContext.getRequiredTestClass().getName();
        String testToWrite = String.format("--tests %s.%s", className,methodName);
        extensionContext.getExecutionException().ifPresent(e-> failedTestsNames.add(testToWrite));
    }

    @Override
    public void handleTestExecutionException(ExtensionContext extensionContext, Throwable throwable) throws Throwable {
        for (int i = 0; i < MAX_RETRIES; i++) {
            try {
                extensionContext.getRequiredTestMethod().invoke(extensionContext.getRequiredTestInstance());
                return; // тест прошёл — не пробрасываем исключение
            } catch (Throwable ex) {
                System.out.println("⚠️ Retry #" + (i + 1) + " failed, " + (MAX_RETRIES - i - 1) + " attempts left");
                if (i == MAX_RETRIES - 1) {
                    throw ex; // последняя попытка тоже упала — пробрасываем
                }
            }
        }
    }
    public static void safeFailedTests() throws IOException {
        File output = new File(System.getProperty("user.dir") + "/src/test/resources/FailedTests.txt");
        String result = String.join(" ", failedTestsNames);
        FileUtils.writeStringToFile(output, result, StandardCharsets.UTF_8);
    }

}
