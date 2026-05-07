package helpers;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:auth.properties") // просто пишем classpath, без полного пути. Можно и джейсон и ямал и текстовые файлы
public interface OwnerProperty extends Config { // Config owner берем
@Key("url") // надо навешивать
String url();
@Key("login")
String login();
@Key("pass")
String pass();
@Key("is_prod")
Boolean is_prod();

}
