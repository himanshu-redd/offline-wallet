package com.masters.pay.offline_wallet.service;

import com.masters.pay.offline_wallet.Session.SessionManager;
import com.masters.pay.offline_wallet.Util.UserUtil;
import com.masters.pay.offline_wallet.dao.User;
import com.masters.pay.offline_wallet.repository.UserRepo;
import com.masters.pay.offline_wallet.request.UserLogInRequest;
import com.masters.pay.offline_wallet.request.UserSignUpRequest;
import com.masters.pay.offline_wallet.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.xml.bind.ValidationException;
import java.util.List;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private SessionManager sessionManager;
    @Autowired
    private WalletService walletService;

    public BaseResponse userSignUp(UserSignUpRequest userSignUpRequest) {
        log.info("Signing Up the user : " + userSignUpRequest.toString());
        try {
            validateUserSignUpRequest(userSignUpRequest);
            List<User> resultSet = userRepo.findByUsername(userSignUpRequest.getUsername());
            if (resultSet.isEmpty()) {
                User user = new User();
                user.setUsername(userSignUpRequest.getUsername());
                user.setFirstName(userSignUpRequest.getFirstName());
                user.setLastName(userSignUpRequest.getLastName());
                user.setEmailId(userSignUpRequest.getEmailId());
                user.setPassword(userSignUpRequest.getPassword());
                user.setPhoneNo(userSignUpRequest.getPhoneNo());
                user.setWalletBalance(0L);
                userRepo.save(user);
                log.info("User Sign Up Successful");
                walletService.createWallet(user.getUsername());
                return UserUtil.getBaseResponseWithSuccessStatus("200", "User Signup Successful");
            } else {
                log.info("Username already exists");
                return UserUtil.getBaseRepWithFailedStatus("400", "Username already exists");
            }
        } catch (ValidationException e) {
            log.error("Validation Exception >>> " + e.getMessage());
            return UserUtil.getBaseRepWithFailedStatus("400", e.getMessage());
        }
    }

    public BaseResponse userLogin(UserLogInRequest userLoginRequest) {
        log.info("Loging in the user : " + userLoginRequest.toString());
        try {
            validateUserLoginRequest(userLoginRequest);
            List<User> resultSet = userRepo.findByUsername(userLoginRequest.getUsername());
            if (resultSet.isEmpty()) {
                log.error("User not registered");
                return UserUtil.getBaseRepWithFailedStatus("400", "User not registered");
            } else if (!resultSet.get(0).getPassword().equals(userLoginRequest.getPassword())) {
                log.error("Password incorrect");
                return UserUtil.getBaseRepWithFailedStatus("400", "Password incorrect");
            } else {
                sessionManager.setLoggedInUser(resultSet.get(0));
                log.info("Login Successful");
                return UserUtil.getBaseResponseWithSuccessStatus("200", "Login Successful");
            }
        } catch (ValidationException e) {
            log.error("Validation Exception >>> " + e.getMessage());
            return UserUtil.getBaseRepWithFailedStatus("400", e.getMessage());
        }
    }

    private void validateUserSignUpRequest(UserSignUpRequest userSignUpRequest) throws ValidationException {
        if (!StringUtils.hasText(userSignUpRequest.getUsername())) {
            throw new ValidationException("Username Missing");
        }
        if (!StringUtils.hasText(userSignUpRequest.getFirstName())) {
            throw new ValidationException("First name Missing");
        }
        if (!StringUtils.hasText(userSignUpRequest.getLastName())) {
            throw new ValidationException("Last name missing");
        }
        if (!StringUtils.hasText(userSignUpRequest.getPassword())) {
            throw new ValidationException("Password Missing");
        }
        if (!StringUtils.hasText(userSignUpRequest.getEmailId())) {
            throw new ValidationException("Email Missing");
        }
        if (!StringUtils.hasText(userSignUpRequest.getPhoneNo())) {
            throw new ValidationException("Phone No Missing");
        }
    }

    private void validateUserLoginRequest(UserLogInRequest userLoginRequest) throws ValidationException {
        if (!StringUtils.hasText(userLoginRequest.getUsername())) {
            throw new ValidationException("Username missing");
        }
        if (!StringUtils.hasText(userLoginRequest.getPassword())) {
            throw new ValidationException("Password Missing");
        }
    }

    public BaseResponse checkBalance(UserLogInRequest userLoginRequest) {
        try {
            validateUserLoginRequest(userLoginRequest);
            List<User> resultSet = userRepo.findByUsername(userLoginRequest.getUsername());
            if (resultSet.isEmpty()) {
                log.error("User not registered");
                return UserUtil.getBaseRepWithFailedStatus("400", "User not registered");
            } else if (!resultSet.get(0).getPassword().equals(userLoginRequest.getPassword())) {
                log.error("Password incorrect");
                return UserUtil.getBaseRepWithFailedStatus("400", "Password incorrect");
            } else {
                log.info("Current Balance for Username " + userLoginRequest.getUsername() + " is : " + resultSet.get(0).getWalletBalance());
                return UserUtil.getCheckBalanceResponse("200", "Check Balance Successful", resultSet.get(0));
            }
        } catch (ValidationException e) {
            throw new RuntimeException(e);
        }
    }

//    public BaseResponse transferAmount(TransferAmountRequest request){
//        return null;
//    }

}
