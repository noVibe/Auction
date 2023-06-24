package pro.sky.auction.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pro.sky.auction.dto.Bid;
import pro.sky.auction.dto.BidDTO;
import pro.sky.auction.model.BidModel;

import java.util.Optional;

public interface BidRepository extends CrudRepository<BidModel, Integer> {

    Optional<Bid> findFirstByLotIdOrderByBidDateAsc(int lotId);
    @Query("select new pro.sky.auction.dto.BidDTO(b.bidderName) from BidModel b " +
            "where b.lotId = :lotId " +
            "group by b.id " +
            "order by count(b.bidderName) desc limit 1")
    Optional<BidDTO> findTheMostFrequentBidder(int lotId);

    int countAllByLotId(int id);
}

