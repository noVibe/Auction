package pro.sky.auction.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import pro.sky.auction.enums.LotStatus;
import pro.sky.auction.model.LotModel;

public interface LotPagingRepository extends PagingAndSortingRepository<LotModel, Integer> {
    Page<LotModel> findAllByStatusIs(LotStatus status, Pageable pageable);

}
