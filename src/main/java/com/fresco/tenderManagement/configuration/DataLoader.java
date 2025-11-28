package com.fresco.tenderManagement.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.fresco.tenderManagement.model.RoleModel;
import com.fresco.tenderManagement.model.UserModel;
import com.fresco.tenderManagement.repository.RoleRepository;
import com.fresco.tenderManagement.repository.UserRepository;

@Component
public class DataLoader implements ApplicationRunner {

    private final PasswordEncoder passwordEncoder;


    @Autowired  
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    DataLoader(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public void run(ApplicationArguments args) throws InterruptedException {

        roleRepository.save(new RoleModel("BIDDER"));
        roleRepository.save(new RoleModel("APPROVER"));

        userRepository.save(new UserModel(1,"bidder1","companyOne",passwordEncoder.encode("bidder123$"),"bidderemail@gmail.com", new RoleModel(1)));
        userRepository.save(new UserModel(2,"bidder2","companyTwo",passwordEncoder.encode("bidder789$"),"bidderemail2@gmail.com",new RoleModel(1)));
        userRepository.save(new UserModel(3,"approver","defaultCompany",passwordEncoder.encode("approver123$"), "approveremail@gmail.com",new RoleModel(2)));
    }

}