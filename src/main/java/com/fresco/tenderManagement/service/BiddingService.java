package com.fresco.tenderManagement.service;

import org.springframework.http.ResponseEntity;

import com.fresco.tenderManagement.model.BiddingModel;
import com.fresco.tenderManagement.repository.BiddingRepository;


public class BiddingService {
    private BiddingRepository biddingRepository;
    private UserService userService;

    // 1. ADD BIDDING -> CREATED-201
    public ResponseEntity<Object> postBidding(BiddingModel biddingModel) {
        return null;
    }

    // 2. LIST BIDDING (by amount greater than) --- OK-200
    public ResponseEntity<Object> getBidding(double bidAmount) {
        return null;
    }

    // 3. UPDATE BIDDING (only approver)--- OK-200
    public ResponseEntity<Object> updateBidding(int id, BiddingModel model) {
        return null;

    }

    // 4. DELETE BIDDING (approver can delete any, bidder can delete own)--- NO
    // CONTENT-204, FORBIDDEN-403, BAD REQUEST-400
    public ResponseEntity<Object> deleteBidding(int id) {
        return null;
    }
}
/*
 * 
 * //1. ADD BIDDING
 * public ResponseEntity<Object> postBidding(BiddingModel biddingModel) {
 * // VALIDATION: Check for null or invalid values
 * if (biddingModel.getBiddingId() <= 0 ||
 * biddingModel.getBidAmount() == null || biddingModel.getBidAmount() <= 0 ||
 * biddingModel.getYearsToComplete() == null ||
 * biddingModel.getYearsToComplete() <= 0) {
 * return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);
 * }
 * 
 * try {
 * UserModel userModel = userService.getUserByEmail(
 * SecurityContextHolder.getContext().getAuthentication().getName()
 * );
 * biddingModel.setBidderId(userModel.getId());
 * biddingModel.setDateOfBidding(new SimpleDateFormat("dd/MM/yyyy").format(new
 * Date()));
 * 
 * biddingRepository.save(biddingModel);
 * return new ResponseEntity<>(biddingModel, HttpStatus.CREATED);
 * } catch (Exception e) {
 * return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);
 * }
 * }
 */
