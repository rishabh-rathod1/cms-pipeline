package com.rishabh.CMS.repository;

import com.rishabh.CMS.model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
    List<Complaint> findByStatus(String status);
    List<Complaint> findByPriority(String priority);
    List<Complaint> findByDateBetween(LocalDate start, LocalDate end);
}
