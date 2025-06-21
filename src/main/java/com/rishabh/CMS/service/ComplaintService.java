package com.rishabh.CMS.service;

import com.rishabh.CMS.model.Complaint;
import com.rishabh.CMS.repository.ComplaintRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class ComplaintService {
    private final ComplaintRepository repo;

    public ComplaintService(ComplaintRepository repo) {
        this.repo = repo;
    }

    public List<Complaint> getAll() {
        return repo.findAll();
    }

    public Complaint create(Complaint complaint) {
        complaint.setStatus("OPEN");
        return repo.save(complaint);
    }

    public Complaint update(Long id, Complaint updated) {
        return repo.findById(id).map(c -> {
            c.setTitle(updated.getTitle());
            c.setDescription(updated.getDescription());
            c.setPriority(updated.getPriority());
            return repo.save(c);
        }).orElseThrow();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public Complaint updateStatus(Long id, String status) {
        return repo.findById(id).map(c -> {
            c.setStatus(status);
            return repo.save(c);
        }).orElseThrow();
    }

    public List<Complaint> byStatus(String status) {
        return repo.findByStatus(status);
    }

    public List<Complaint> byPriority(String priority) {
        return repo.findByPriority(priority);
    }

    public List<Complaint> byDateRange(LocalDate start, LocalDate end) {
        return repo.findByDateBetween(start, end);
    }
}
