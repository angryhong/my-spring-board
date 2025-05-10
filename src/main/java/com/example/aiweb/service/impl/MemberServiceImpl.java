package com.example.aiweb.service.impl;

import com.example.aiweb.dto.MemberDto;
import com.example.aiweb.entity.Member;
import com.example.aiweb.exception.ResourceNotFoundException;
import com.example.aiweb.repository.MemberRepository;
import com.example.aiweb.service.MemberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

    private final MemberRepository repo;

    public MemberServiceImpl(MemberRepository repo) {
        this.repo = repo;
    }

    @Override
    public MemberDto findById(Long id) {
        Member m = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("회원이 없습니다: " + id));
        return new MemberDto(
                m.getId(),
                m.getUsername(),
                m.getPassword(),
                m.getDisplayName(),
                m.getJoinedAt()
        );
    }

    @Override
    @Transactional
    public MemberDto register(MemberDto dto) {
        Member m = new Member();
        m.setUsername(dto.getUsername());
        m.setPassword(dto.getPassword());
        m.setDisplayName(dto.getDisplayName());
        Member saved = repo.save(m);
        return new MemberDto(
                saved.getId(),
                saved.getUsername(),
                saved.getPassword(),
                saved.getDisplayName(),
                saved.getJoinedAt()
        );
    }

    @Override
    public MemberDto login(String username, String password) {
        // 1) 사용자 조회
        Member m = repo.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("회원이 없습니다: " + username));
        // 2) 비밀번호 검증
        if (!m.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        // 3) DTO 반환
        return new MemberDto(
                m.getId(),
                m.getUsername(),
                m.getPassword(),
                m.getDisplayName(),
                m.getJoinedAt()
        );
    }
}
