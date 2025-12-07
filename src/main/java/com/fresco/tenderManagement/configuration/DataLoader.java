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

        if (roleRepository.findByRolename("BIDDER") == null) {
            roleRepository.save(new RoleModel("BIDDER"));
        }
        if (roleRepository.findByRolename("APPROVER") == null) {
            roleRepository.save(new RoleModel("APPROVER"));
        }

        if (userRepository.findByEmail("bidderemail@gmail.com") == null) {
            userRepository.save(new UserModel(0, "bidder1", "companyOne", passwordEncoder.encode("bidder123$"),
                    "bidderemail@gmail.com", new RoleModel(1)));
        }
        if (userRepository.findByEmail("bidderemail2@gmail.com") == null) {
            userRepository.save(new UserModel(0, "bidder2", "companyTwo", passwordEncoder.encode("bidder789$"),
                    "bidderemail2@gmail.com", new RoleModel(1)));
        }
        if (userRepository.findByEmail("approveremail@gmail.com") == null) {
            userRepository.save(new UserModel(0, "approver", "defaultCompany", passwordEncoder.encode("approver123$"),
                    "approveremail@gmail.com", new RoleModel(2)));
        }
    }

}