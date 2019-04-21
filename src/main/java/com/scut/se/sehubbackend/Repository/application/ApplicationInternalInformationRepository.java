package com.scut.se.sehubbackend.Repository.application;

import com.scut.se.sehubbackend.Domain.application.ApplicationInternalInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ApplicationInternalInformationRepository extends JpaRepository<ApplicationInternalInformation,Long> {
}
