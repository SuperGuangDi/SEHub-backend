package com.scut.se.sehubbackend.Repository.application;

import com.scut.se.sehubbackend.Domain.application.ApplicationForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface ApplicationFormRepository extends JpaRepository<ApplicationForm,Long> {
}
