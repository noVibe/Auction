package pro.sky.auction.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.sky.auction.dto.BidDTO;
import pro.sky.auction.enums.LotStatus;
import pro.sky.auction.expetion_logic.exceptions.NothingFoundById;
import pro.sky.auction.expetion_logic.exceptions.WrongLotStatus;
import pro.sky.auction.handlers.BidConverter;
import pro.sky.auction.model.BidModel;
import pro.sky.auction.model.LotModel;
import pro.sky.auction.repository.BidRepository;
import pro.sky.auction.repository.LotRepository;


@Service
public class BidService {
    final private BidRepository bidRepository;
    final private LotRepository lotRepository;

    final private BidConverter bidConverter;

    public BidService(BidRepository bidRepository, LotRepository lotRepository, BidConverter bidConverter) {
        this.bidRepository = bidRepository;
        this.lotRepository = lotRepository;
        this.bidConverter = bidConverter;
    }

    public BidDTO getFirstBidder(int lotId) {
        return bidConverter.toBidDTO(bidRepository.findFirstByLotIdOrderByBidDateAsc(lotId)
                .orElseThrow(() -> new NothingFoundById(lotId)));
    }


    public BidDTO getMostFrequentBidder(int lotId) {
        return bidRepository.findTheMostFrequentBidder(lotId).orElseThrow(() -> new NothingFoundById(lotId));
    }

    @Transactional
    public void addBid(int lotId, BidDTO dto) {
        BidModel bidModel = new BidModel(lotId, dto.getBidderName());
        LotModel lot = lotRepository.findById(lotId).orElseThrow(() -> new NothingFoundById(lotId));
        if (!lot.getStatus().equals(LotStatus.STARTED)) {
            throw new WrongLotStatus();
        }
        lot.setLastBid(bidModel);
        lotRepository.save(lot);
    }

}
