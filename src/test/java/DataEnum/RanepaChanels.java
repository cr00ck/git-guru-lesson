package DataEnum;

public enum RanepaChanels {
        VK("https://vk.com/ranepaabitura", "vk", "ВКонтакте"),
    TELEGRAM("https://t.me/ranepa_regions", "telegram", "Telegram"),
    RUTUBE("https://rutube.ru/channel/23812270/", "rutube", "Rutube");

        public final String url;
        public final String dataAttribute;
        public final String displayName;

        RanepaChanels(String url, String dataAttribute, String displayName) {
            this.url = url;
            this.dataAttribute = dataAttribute;
            this.displayName = displayName;
        }
}
