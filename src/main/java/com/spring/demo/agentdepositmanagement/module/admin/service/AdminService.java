package com.spring.demo.agentdepositmanagement.module.admin.service;

import com.spring.demo.agentdepositmanagement.dto.ResponseDto;
import com.spring.demo.agentdepositmanagement.module.admin.dto.AdminDto;
import com.spring.demo.agentdepositmanagement.module.admin.entity.AdminEntity;
import com.spring.demo.agentdepositmanagement.module.admin.repository.AdminRepository;
import com.spring.demo.agentdepositmanagement.module.admin.request.AdminRequest;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private DozerBeanMapper dozerBeanMapper;

    public ResponseDto getAdminById(Integer adminId) throws NotFoundException {
        ResponseDto responseDto = new ResponseDto();
        List<AdminDto> adminDtoList = new ArrayList<>();
        Optional<AdminEntity> adminEntity = adminRepository.findById(adminId);
        if (adminEntity.isPresent()) {
            adminDtoList.add(dozerBeanMapper.map(adminEntity.get(), AdminDto.class));
            responseDto.setCode("000");
            responseDto.setMessage("success");
            responseDto.setData(adminDtoList);
        } else {
            throw new NotFoundException("Agent Not Found");
        }
        return responseDto;
    }

    public ResponseDto registerAdmin(AdminRequest adminRequest) {
        ResponseDto responseDto = new ResponseDto();
        List<AdminDto> adminDtoList = new ArrayList<>();
        AdminEntity adminEntity = dozerBeanMapper.map(adminRequest, AdminEntity.class);
        adminDtoList.add(dozerBeanMapper.map(adminRepository.save(adminEntity), AdminDto.class));
        responseDto.setCode("000");
        responseDto.setMessage("success");
        responseDto.setData(adminDtoList);
        return responseDto;
    }
}
