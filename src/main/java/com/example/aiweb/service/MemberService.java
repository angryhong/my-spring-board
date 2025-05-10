package com.example.aiweb.service;

import com.example.aiweb.dto.MemberDto;

public interface MemberService {
    MemberDto findById(Long id);
    MemberDto register(MemberDto dto);
    MemberDto login(String username, String password);
}
