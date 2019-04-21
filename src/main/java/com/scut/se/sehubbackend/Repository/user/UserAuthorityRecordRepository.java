package com.scut.se.sehubbackend.Repository.user;

import com.scut.se.sehubbackend.Domain.user.UserAuthorityRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface UserAuthorityRecordRepository extends JpaRepository<UserAuthorityRecord,Long> {
    List<OwnerOnly> findAllByAuthority(String authority);
}

