package com.rishabh.CMS.controller;

import com.rishabh.CMS.model.Complaint;
import com.rishabh.CMS.repository.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/complaints")
public class ComplaintController {

    @Autowired
    private ComplaintRepository repo;

    @PostMapping
    public Complaint submitComplaint(@RequestBody Complaint complaint) {
        return repo.save(complaint);
    }

    @GetMapping
    public List<Complaint> getAllComplaints() {
        return repo.findAll();
    }

    @PutMapping("/{id}/resolve")
    public Complaint resolveComplaint(@PathVariable Long id) {
        Complaint complaint = repo.findById(id).orElseThrow();
        complaint.setStatus("Resolved");
        return repo.save(complaint);
    }
}
