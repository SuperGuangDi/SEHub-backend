package com.scut.se.sehubbackend.Repository.user;

import com.scut.se.sehubbackend.Domain.user.UserAuthentication;

public interface OwnerOnly{
    UserAuthentication getOwner();
}
