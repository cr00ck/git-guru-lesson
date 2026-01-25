package JsonModelClasses;

public class AdditionalInfo {

    private String additional_info;
    private Integer clientVersion;
    private String bitrix;

    private InnerClassDocument document; // так как это вложенный элемент, создаем для него класс, и все что вложено размещаем в нем

    // делаем геттеры и сеттеры

    public String getAdditional_info() {
        return additional_info;
    }

    public void setAdditional_info(String additional_info) {
        this.additional_info = additional_info;
    }

    public Integer getClientVersion() {
        return clientVersion;
    }

    public void setClientVersion(Integer clientVersion) {
        this.clientVersion = clientVersion;
    }

    public String getBitrix() {
        return bitrix;
    }

    public void setBitrix(String bitrix) {
        this.bitrix = bitrix;
    }

    public InnerClassDocument getDocument() {
        return document;
    }

    public void setDocument(InnerClassDocument document) {
        this.document = document;
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