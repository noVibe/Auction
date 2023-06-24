package pro.sky.auction.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pro.sky.auction.dto.FullLot;
import pro.sky.auction.enums.LotStatus;
import pro.sky.auction.model.LotModel;

import java.util.Optional;

public interface LotRepository extends CrudRepository<LotModel, Integer> {


    @Query("select new pro.sky.auction.dto.FullLot(l.id, l.status, l.title, l.description," +
            " l.startPrice, l.bidPrice, l.currentPrice, l.lastBidModel)" +
            " from LotModel l where l.id = :id")
    Optional<FullLot> findFullLotValues(int id);

    @Modifying
    @Query("update LotModel l set l.status = :status where l.id = :id")
    void updateStatusById(int id, LotStatus status);

}

