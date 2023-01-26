package shop.wesellbuy.secondproject.service.member;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.wesellbuy.secondproject.domain.Member;
import shop.wesellbuy.secondproject.domain.member.SelfPicture;
import shop.wesellbuy.secondproject.exception.member.ExistingIdException;
import shop.wesellbuy.secondproject.repository.member.MemberJpaRepository;
import shop.wesellbuy.secondproject.repository.member.MemberSearchCond;
import shop.wesellbuy.secondproject.web.member.MemberDetailForm;
import shop.wesellbuy.secondproject.web.member.MemberForm;
import shop.wesellbuy.secondproject.web.member.MemberOriginForm;
import shop.wesellbuy.secondproject.web.member.MemberUpdateForm;

import java.io.IOException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Setter
public class MemberServiceImpl implements MemberService {

    private final MemberJpaRepository memberJpaRepository;
    private final FileStoreOfSelfPicture fileStoreOfSelfPicture;

    /**
     * writer : 이호진
     * init : 2023.01.26
     * updated by writer :
     * update :
     * description : 회원 가입
     *
     * @return
     */
    @Override
    @Transactional
    public int join(MemberOriginForm memberOriginForm) throws IOException {

        // 아이디 중복 확인
        checkId(memberOriginForm.getId());

        // selfPicture 생성
        SelfPicture selfPicture = fileStoreOfSelfPicture.storeFile(memberOriginForm.getFile());

        // MemberForm 생성
        MemberForm memberForm = memberOriginForm.changeAsMemberForm(selfPicture);

        // member 생성
        Member member = Member.createMember(memberForm);

        // 회원 정보 저장
        return memberJpaRepository.save(member).getNum();
    }

    /**
     * writer : 이호진
     * init : 2023.01.26
     * updated by writer :
     * update :
     * description : 아이디가 존재하는지 확인한다.
     */
    private void checkId(String id) {
        // 아이디로 member 조회하기
        if(!memberJpaRepository.findByMemberId(id).isEmpty()) {
            String errMsg = "이미 사용중인 아이디";
            throw new ExistingIdException(errMsg);
        };
    }

    /**
     * writer : 이호진
     * init : 2023.01.26
     * updated by writer :
     * update :
     * description : 회원 정보 수정
     */
    @Override
    @Transactional
    public void update(MemberUpdateForm memberUpdateForm, Integer num) throws IOException {
        // 파일 저장하기
        SelfPicture selfPicture = fileStoreOfSelfPicture.storeFile(memberUpdateForm.getFile());

        // 회원 찾기
        Member findMember = memberJpaRepository.findById(num).orElseThrow();

        // 회원 정보 수정하기
        findMember.updateMember(memberUpdateForm, selfPicture);
    }

    /**
     * writer : 이호진
     * init : 2023.01.26
     * updated by writer :
     * update :
     * description : 회원 탈퇴
     *               -> status 변경
     */
    @Override
    @Transactional
    public void withdrawal(int num) {
        // 회원 찾기
        Member findMember = memberJpaRepository.findById(num).orElseThrow();

        // 회원 상태 변경
        findMember.withdrawMember(findMember);
    }

    /**
     * writer : 이호진
     * init : 2023.01.26
     * updated by writer :
     * update :
     * description : 회원 정보 상세보기
     */
    @Override
    public MemberDetailForm watchDetail(int num) {
        // 회원 정보 가져오기
        Member findMember = memberJpaRepository.findDetailInfoById(num).orElseThrow();
        // MemberDetailForm으로 변경하기
        return MemberDetailForm.createMemberDetailForm(findMember);
    }

//   ------------------------------methods using admin --------------------------------

    /**
     * writer : 이호진
     * init : 2023.01.26
     * updated by writer :
     * update :
     * description : 모든 회원 정보 가져오기 + 검색
     */
    @Override
    public Page<MemberDetailForm> selectList(MemberSearchCond memberSearchCond, Pageable pageable) {
        // member List 가져오기
        Page<Member> members = memberJpaRepository.findAllInfo(memberSearchCond, pageable);
        // memberDetailForm으로 변경하기
        Page<MemberDetailForm> memberDetailFormList = members.map(m -> MemberDetailForm.createMemberDetailForm(m));

        return memberDetailFormList;
    }
}
