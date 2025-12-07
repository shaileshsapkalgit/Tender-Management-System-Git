package com.fresco.tenderManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fresco.tenderManagement.model.BiddingModel;


public interface BiddingRepository extends JpaRepository<BiddingModel, Integer> {

}
