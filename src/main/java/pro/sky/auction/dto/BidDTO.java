package pro.sky.auction.dto;

public class BidDTO {
    private String bidderName;

    public BidDTO(String bidderName) {
        this.bidderName = bidderName;
    }

    public BidDTO() {
    }

    public String getBidderName() {
        return bidderName;
    }

    public void setBidderName(String bidderName) {
        this.bidderName = bidderName;
    }

    @Override
    public String toString() {
        return "BidDTO{" +
                "bidderName='" + bidderName + '\'' +
                '}';
    }
}
