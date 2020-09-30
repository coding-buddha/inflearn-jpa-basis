package edu.pasudo123.study.inflearn.member.service;

import edu.pasudo123.study.inflearn.member.model.Member;
import edu.pasudo123.study.inflearn.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Long join(final Member member) {
        this.validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    /**
     * 중복회원을 검증한다.
     * @param member
     */
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()) {
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        }
    }

    @Transactional(readOnly = true)
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Member findById(final long id) {
        return memberRepository.findById(id);
    }
}
