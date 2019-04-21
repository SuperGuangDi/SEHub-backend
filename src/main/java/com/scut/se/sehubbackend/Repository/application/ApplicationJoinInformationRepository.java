package com.scut.se.sehubbackend.Repository.application;

import com.scut.se.sehubbackend.Domain.application.ApplicationJoinInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface ApplicationJoinInformationRepository extends JpaRepository<ApplicationJoinInformation,Long> {
}
