package pro.sky.auction.handlers;

import org.springframework.stereotype.Component;
import pro.sky.auction.dto.Bid;
import pro.sky.auction.dto.BidDTO;

@Component
public class BidConverter {
    public BidDTO toBidDTO(Bid bid) {
        return new BidDTO(bid.bidderName());
    }
}
