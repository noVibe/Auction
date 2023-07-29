package pro.sky.auction.dto;

import pro.sky.auction.enums.LotStatus;

public record Lot(int id, LotStatus status, String title, String description, int startPrice, int bidPrice) {
    public Lot {
    }
}
