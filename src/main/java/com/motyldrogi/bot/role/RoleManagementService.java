package com.motyldrogi.bot.role;

import com.motyldrogi.bot.user.UserRepository;

public class RoleManagementService {

    private final UserRepository userRepository;
    
    public RoleManagementService(
        UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }

    public void updateUserRole(String role){
        
    }


}
