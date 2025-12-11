package pages.components;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {
    // тут будут лежать все елементы, которые встречаются на разных страницах \
    // несколько раз

    public static SelenideElement monthLocator = $(".react-datepicker__month-select");
    public static SelenideElement yearLocator =  $(".react-datepicker__year-select");

    public void setCalendar (String month, String year){
//      тут еще вставлять локатор где календарь.click
        monthLocator.selectOption(month);
        yearLocator.selectOption(year);
        $(".react-datepicker__day--017:not(.react-datepicker__day--outside-month)").click();// не содержит класс .react-datepicker__day--outside-month

    }
}
