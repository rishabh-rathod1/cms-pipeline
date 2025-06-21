package com.rishabh.CMS.controller;

import com.rishabh.CMS.model.Complaint;
import com.rishabh.CMS.service.ComplaintService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/complaints")
public class ComplaintController {
    private final ComplaintService service;

    public ComplaintController(ComplaintService service) {
        this.service = service;
    }

    @GetMapping
    public List<Complaint> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Complaint create(@RequestBody Complaint complaint) {
        return service.create(complaint);
    }

    @PutMapping("/{id}")
    public Complaint update(@PathVariable Long id, @RequestBody Complaint complaint) {
        return service.update(id, complaint);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PatchMapping("/{id}/status")
    public Complaint updateStatus(@PathVariable Long id, @RequestParam String status) {
        return service.updateStatus(id, status);
    }

    @GetMapping("/filter/status")
    public List<Complaint> byStatus(@RequestParam String status) {
        return service.byStatus(status);
    }

    @GetMapping("/filter/priority")
    public List<Complaint> byPriority(@RequestParam String priority) {
        return service.byPriority(priority);
    }

    @GetMapping("/filter/date")
    public List<Complaint> byDateRange(@RequestParam String start, @RequestParam String end) {
        return service.byDateRange(LocalDate.parse(start), LocalDate.parse(end));
    }
}
