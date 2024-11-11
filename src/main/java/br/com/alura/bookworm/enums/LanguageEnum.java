package br.com.alura.bookworm.enums;

public enum LanguageEnum {
    PORTUGUESE("pt", "Português"),
    ENGLISH("en", "Inglês");

    private final String language;
    private final String description;

    LanguageEnum(String language, String description) {
        this.language = language;
        this.description = description;
    }

    public static LanguageEnum fromString(String language) {
        for (LanguageEnum e : LanguageEnum.values()) {
            if (e.language.equals(language)) {
                return e;
            }
        }

        System.out.println("The Language Inserted Is Not Available");
        return null;
    }

    public String getLanguage() {
        return language;
    }

    public String getDescription() {
        return description;
    }
}
