package com.masters.pay.offline_wallet.Session;

import com.masters.pay.offline_wallet.dao.User;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class SessionManager {

    private User loggedInUser;
}
