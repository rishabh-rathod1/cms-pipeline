package com.rishabh.CMS.repository;

import com.rishabh.CMS.model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {}