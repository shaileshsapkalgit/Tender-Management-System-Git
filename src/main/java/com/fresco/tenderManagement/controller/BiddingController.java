package com.fresco.tenderManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fresco.tenderManagement.model.BiddingModel;
import com.fresco.tenderManagement.service.BiddingService;


@RequestMapping("/bidding")
public class BiddingController {


    private BiddingService biddingService;

    @PostMapping("/add")
    public ResponseEntity<Object> postBidding( BiddingModel biddingModel) {
        return null;
    }

    @GetMapping("/list")
    public ResponseEntity<Object> getBidding( double bidAmount) {
        return null;
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Object> updateBidding( int id,  BiddingModel biddingModel) {
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteBidding( int id)
    {
        return null;
    }

    @GetMapping("/hi")
    public String hello() {
        return "Hi working...!!!";
    }
}
