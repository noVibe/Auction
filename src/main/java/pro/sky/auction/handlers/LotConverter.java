package pro.sky.auction.handlers;

import org.springframework.stereotype.Component;
import pro.sky.auction.dto.CreateLot;
import pro.sky.auction.dto.Lot;
import pro.sky.auction.enums.LotStatus;
import pro.sky.auction.model.LotModel;

@Component
public class LotConverter {

    public LotModel toLotModel(CreateLot dto) {
        return new LotModel(LotStatus.CREATED, dto.title(), dto.description(), dto.startPrice(), dto.bidPrice());
    }

    public Lot toLot(LotModel lotModel) {
        return new Lot(lotModel.getId(),
                lotModel.getStatus(),
                lotModel.getTitle(),
                lotModel.getDescription(),
                lotModel.getStartPrice(),
                lotModel.getBidPrice());
    }

}
