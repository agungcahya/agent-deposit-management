package com.spring.demo.agentdepositmanagement.module.admin.controller;

import com.spring.demo.agentdepositmanagement.dto.ResponseDto;
import com.spring.demo.agentdepositmanagement.module.admin.request.AdminRequest;
import com.spring.demo.agentdepositmanagement.module.admin.service.AdminService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/admin-management")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping(value = "/admin/{adminId}")
    public ResponseEntity<ResponseDto> getAdminById(@PathVariable Integer adminId) throws NotFoundException {
        return ResponseEntity.ok(adminService.getAdminById(adminId));
    }

    @PostMapping(value = "/admin/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponseDto> registerAdmin(@RequestBody AdminRequest adminRequest) {
        return ResponseEntity.ok(adminService.registerAdmin(adminRequest));
    }
}
