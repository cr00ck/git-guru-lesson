package JsonModelClasses;

import com.google.gson.annotations.SerializedName;

public class InnerClassDocument {

    @SerializedName("RepoLink")
    private Integer repoLink; // когда название с большой в JSON то нужна аннотация
    @SerializedName("URL")
    private String url; // когда название большими буквами в JSON, то нужна аннотация

    // делаем геттеры и сеттеры

    public Integer getRepoLink() {
        return repoLink;
    }

    public void setRepoLink(Integer repoLink) {
        this.repoLink = repoLink;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
//{
//        "additional_info": "ТЕСТ сведения о квалификации CR00CK",
//        "document": {
//        "repoLink": 123456,
//        "URL": "https://lk-files.ranepa.ru/public/lf75cb98ce33a6c2e3327df16269ad8f45384af38a03afa247202fb534e7a3530c8ac7a50746a5df8273e558aff652b844f36d1c00d6494b4a5067ab9cca999d0"
//        },
//        "clientVersion": 1,
//        "bitrix": "hr/candidate/profile/additional"
//        }