package com.fresco.tenderManagement.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.fresco.tenderManagement.model.BiddingModel;
import com.fresco.tenderManagement.model.UserModel;
import com.fresco.tenderManagement.repository.BiddingRepository;
import com.fresco.tenderManagement.repository.UserRepository;

@Service
public class BiddingService {
    @Autowired
    private BiddingRepository biddingRepository;
    @Autowired
    private UserService userService;

    //1. ADD BIDDING
    public ResponseEntity<Object> postBidding(BiddingModel biddingModel) {
        UserModel userModel = userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

        biddingModel.setBidderId(userModel.getId());
        biddingModel.setDateOfBidding(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        biddingRepository.save(biddingModel);

        return new ResponseEntity<>(biddingModel, HttpStatus.CREATED); // Returns 201 Created
    }


    //2.  LIST BIDDING (by amount greater than)
    public ResponseEntity<Object> getBidding(double bidAmount) {
        List<BiddingModel> results = biddingRepository.findByBidAmountGreaterThan(bidAmount);

        if (results.isEmpty()) {
            return new ResponseEntity<>("No data available", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    //3. UPDATE BIDDING (only approver)
    public ResponseEntity<Object> updateBidding(int id, BiddingModel model) {
        BiddingModel bidding = biddingRepository.findById(id).orElse(null);

        if (bidding == null) {
            return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);
        }

        bidding.setStatus(model.getStatus());
        biddingRepository.save(bidding);

        return new ResponseEntity<>(bidding, HttpStatus.OK);
    }

    //4. DELETE BIDDING (approver can delete any, bidder can delete own)
    public ResponseEntity<Object> deleteBidding(int id)
    {
        BiddingModel bidding = biddingRepository.findById(id).orElse(null);

        if (bidding == null) {
            return new ResponseEntity<>("Not found", HttpStatus.BAD_REQUEST);
        }

        UserModel user = userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

        if ("APPROVER".equals(user.getRole().getRolename()) || bidding.getBidderId() == user.getId()) {
            biddingRepository.deleteById(id);
            return new ResponseEntity<>("Deleted successfully", HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>("You don't have permission", HttpStatus.FORBIDDEN);
    }
}
/*

//1. ADD BIDDING
public ResponseEntity<Object> postBidding(BiddingModel biddingModel) {
    // VALIDATION: Check for null or invalid values
    if (biddingModel.getBiddingId() <= 0 ||
            biddingModel.getBidAmount() == null || biddingModel.getBidAmount() <= 0 ||
            biddingModel.getYearsToComplete() == null || biddingModel.getYearsToComplete() <= 0) {
        return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);
    }

    try {
        UserModel userModel = userService.getUserByEmail(
                SecurityContextHolder.getContext().getAuthentication().getName()
        );
        biddingModel.setBidderId(userModel.getId());
        biddingModel.setDateOfBidding(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));

        biddingRepository.save(biddingModel);
        return new ResponseEntity<>(biddingModel, HttpStatus.CREATED);
    } catch (Exception e) {
        return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);
    }
}
*/
