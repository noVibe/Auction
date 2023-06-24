package pro.sky.auction.expetion_logic.exceptions;

public class WrongLotStatus extends RuntimeException {
    public WrongLotStatus() {
        super("Wrong Lot Status");
    }
}
