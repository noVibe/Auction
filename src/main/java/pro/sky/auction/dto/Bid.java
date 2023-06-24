package pro.sky.auction.dto;

import java.time.LocalDateTime;

public record Bid(String bidderName, LocalDateTime bidDate) {
    public Bid {
    }

    @Override
    public String toString() {
        return "Bid{" +
                "bidderName='" + bidderName + '\'' +
                ", bidDate=" + bidDate +
                '}';
    }
}
