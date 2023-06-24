package pro.sky.auction.expetion_logic.exceptions;

public class NothingFoundById extends RuntimeException{
    public NothingFoundById(int id) {
        super("No instances for id: " + id);
    }
}
