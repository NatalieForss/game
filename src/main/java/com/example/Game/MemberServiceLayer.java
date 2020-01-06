package com.example.Game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceLayer {
    @Autowired
    MemberRepository memberRepository;
    public void addMember(Member member) {
        memberRepository.addMember(member);
    }

    public Member getMember(String username) {
        return memberRepository.getMemberByUsername(username);
    }
}
