package com.rishabh.CMS.controller;

import com.rishabh.CMS.model.Complaint;
import com.rishabh.CMS.repository.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import io.micrometer.core.instrument.MeterRegistry;



@RestController
@RequestMapping("/api/complaints")
public class ComplaintController {

    @Autowired
    private ComplaintRepository repo;
    private final MeterRegistry meterRegistry;

    public ComplaintController(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

   

    // Create a new complaint
    @PostMapping
    public Complaint submitComplaint(@RequestBody Complaint complaint) {
    	meterRegistry.counter("complaints.submitted").increment();
        complaint.setStatus("OPEN"); // default status
        return repo.save(complaint);
    }

    // Get all complaints
    @GetMapping
    public List<Complaint> getAllComplaints() {
        return repo.findAll();
    }

    // Get a specific complaint by ID
    @GetMapping("/{id}")
    public Complaint getComplaintById(@PathVariable Long id) {
        return repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Complaint not found with ID: " + id));
    }

    // Update an entire complaint
    @PutMapping("/{id}")
    public Complaint updateComplaint(@PathVariable Long id, @RequestBody Complaint updated) {
        Complaint complaint = repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Complaint not found with ID: " + id));

        complaint.setDescription(updated.getDescription());
        
        complaint.setStatus(updated.getStatus());
        return repo.save(complaint);
    }

    // Update only the status
    @PatchMapping("/{id}/status")
    public Complaint updateStatus(@PathVariable Long id, @RequestParam String status) {
        Complaint complaint = repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Complaint not found with ID: " + id));

        complaint.setStatus(status);
        return repo.save(complaint);
    }

    // Delete a complaint
    @DeleteMapping("/{id}")
    public void deleteComplaint(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
