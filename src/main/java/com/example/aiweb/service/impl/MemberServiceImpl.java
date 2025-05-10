package com.example.aiweb.service.impl;

import com.example.aiweb.dto.MemberDto;
import com.example.aiweb.repository.entity.Member;
import com.example.aiweb.repository.MemberRepository;
import com.example.aiweb.service.MemberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {
    private final MemberRepository repo;
    public MemberServiceImpl(MemberRepository repo) { this.repo = repo; }
    @Override public MemberDto findById(Long id) {
        Member m = repo.findById(id).orElseThrow(() -> new RuntimeException("회원이 없습니다: " + id));
        return new MemberDto(m.getId(), m.getUsername(), m.getDisplayName(), m.getJoinedAt()); }
    @Override @Transactional public MemberDto register(MemberDto dto) {
        Member m = new Member(); m.setUsername(dto.getUsername()); m.setPassword(dto.getPassword()); m.setDisplayName(dto.getDisplayName());
        Member saved = repo.save(m);
        return new MemberDto(saved.getId(), saved.getUsername(), saved.getDisplayName(), saved.getJoinedAt()); }
}