package jpabook2.jpashop2.service;

import jpabook2.jpashop2.domain.Member;
import jpabook2.jpashop2.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
// JPA의 모든 데이터 변경 및 로직은 가급적 트랜잭션 안에서 동작해야함
@Transactional(readOnly = true) // 조회하는 곳에서 성능 최적
@RequiredArgsConstructor // 생성자 자동 생성
public class MemberService {

    private final MemberRepository memberRepository;

    // 회원가입
    @Transactional
    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);

        return member.getId();
    }

    // 중복회원 체크
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers  = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 회원 전체 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    // 회원 조회
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }
}


/**
 * Service에 @Autowired가 없는 EU
 * 요즘 방식은 생성자를 생성해서 고정되는 자동주입을
 * 변동이 쉽게 하기위해, 즉 테스트를 쉽게하려고 저런식으로 @RequiredArgs~ 를 사용해서
 * 주입을 진행한다고 함
 * */