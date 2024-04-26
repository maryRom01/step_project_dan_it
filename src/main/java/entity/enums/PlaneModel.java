package entity.enums;

public enum PlaneModel {
    A_319(128),
    Boeing_737(127),
    Boeing_777_200(273),
    Boeing_777_300(304),
    Boeing_787_8(234),
    Boeing_787_9(285),
    CRJ_200(50),
    CRJ_700(65),
    CRJ_900(79);

    int seatsAmount;
    PlaneModel(int seatsAmount) {
        this.seatsAmount = seatsAmount;
    }

    public int getSeats() {
        return seatsAmount;
    }

    @Override
    public String toString() {
        return String.valueOf(seatsAmount);
    }
}
