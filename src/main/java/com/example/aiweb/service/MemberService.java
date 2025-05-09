package com.example.aiweb.service;

import com.example.aiweb.entity.Member;
import com.example.aiweb.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;




@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void saveMember(Member member) {
        memberRepository.save(member);
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member findByMembername(String membername) {
        return memberRepository.findByMembername(membername);
    }

    // 💾 저장 메서드 추가
    public void save(Member member) {
        memberRepository.save(member);
    }
}
