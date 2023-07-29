package pro.sky.auction.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.auction.dto.BidDTO;
import pro.sky.auction.dto.CreateLot;
import pro.sky.auction.dto.FullLot;
import pro.sky.auction.dto.Lot;
import pro.sky.auction.service.BidService;
import pro.sky.auction.service.LotService;

import java.io.File;
import java.net.MalformedURLException;
import java.util.List;
@RestController
@RequestMapping("/lot")
public class LotController {
    private final LotService lotService;
    private final BidService bidService;

    @Value("${page.size}")
    private int pageSize;

    @GetMapping
    public List<Lot> findLots(@RequestParam(name = "status", required = false) String status,
                              @RequestParam(name = "page", defaultValue = "0") int page) {
        return lotService.findLots(status, page, pageSize);
    }

    public LotController(LotService lotService, BidService bidService) {
        this.lotService = lotService;
        this.bidService = bidService;
    }

    @GetMapping(value = "/export", produces = "application/csv")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Resource> getCSVFile() throws MalformedURLException {
        File file = lotService.getLotsInCSV();
        Resource resource = new UrlResource(file.toURI());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"lots.csv\"")
                .body(resource);
    }

    @GetMapping(path = "/{id}/first", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public BidDTO getFirstBidder(@PathVariable(name = "id") int id) {
        return bidService.getFirstBidder(id);
    }

    @GetMapping(path = "/{id}/frequent", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public BidDTO getMostFrequentBidder(@PathVariable(name = "id") int id) {
        return bidService.getMostFrequentBidder(id);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public FullLot getFullLot(@PathVariable(name = "id") int id) {
        return lotService.getFullLot(id);
    }

    @PostMapping("/{id}/start")
    public void startLot(@PathVariable(name = "id") int id) {
        lotService.startLot(id);
    }

    @PostMapping("/{id}/bid")
    public void addBid(@PathVariable(name = "id") int id, @RequestBody BidDTO bidder) {
        bidService.addBid(id, bidder);
    }

    @PostMapping("/{id}/stop")
    public void stopLot(@PathVariable(name = "id") int id) {
        lotService.stopLot(id);
    }

    @PostMapping
    public void addLod(@RequestBody CreateLot lot) {
        lotService.addLod(lot);
    }
}
