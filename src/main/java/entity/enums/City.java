package entity.enums;

public enum City {
    AMSTERDAM("AM"),
    BERLIN("BR"),
    BRUSSELS("BS"),
    LONDON("LN"),
    MADRID("MD"),
    MUNICH("MN"),
    PARIS("PR"),
    PRAGUE("PG"),
    ROME("RM"),
    VIENNA("VN"),
    WARSAW("WR");

    String code;
    City(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}
