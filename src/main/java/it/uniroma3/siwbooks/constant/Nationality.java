package it.uniroma3.siwbooks.constant;

public enum Nationality {
    ITALIANA("Italiana"),
    FRANCESE("Francese"),
    TEDESCA("Tedesca"),
    SPAGNOLA("Spagnola"),
    INGLESE("Inglese"),
    AMERICANA("Americana"),
    CANADESE("Canadese"),
    CINESE("Cinese"),
    GIAPPONESE("Giapponese"),
    RUSSA("Russa"),
    INDIANA("Indiana"),
    BRASILIANA("Brasiliana"),
    AUSTRALIANA("Australiana"),
    PORTOGHESE("Portoghese"),
    OLANDESE("Olandese"),
    BELGA("Belga"),
    SVIZZERA("Svizzera"),
    AUSTRIACA("Austriaca"),
    GRECA("Greca"),
    SVEDESE("Svedese"),
    NORVEGESE("Norvegese"),
    DANESE("Danese"),
    POLACCA("Polacca"),
    UCRAINA("Ucraina"),
    RUMENA("Rumena"),
    UNGHERESE("Ungherese"),
    IRLANDESE("Irlandese"),
    MESSICANA("Messicana"),
    ARGENTINA("Argentina"),
    SUDAFRICANA("Sudafricana");

    private final String displayName;

    Nationality(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
