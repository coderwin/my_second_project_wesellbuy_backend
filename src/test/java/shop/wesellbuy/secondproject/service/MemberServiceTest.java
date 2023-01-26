package shop.wesellbuy.secondproject.service;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import shop.wesellbuy.secondproject.domain.Member;
import shop.wesellbuy.secondproject.domain.member.MemberStatus;
import shop.wesellbuy.secondproject.exception.member.ExistingIdException;
import shop.wesellbuy.secondproject.repository.member.MemberJpaRepository;
import shop.wesellbuy.secondproject.repository.member.MemberSearchCond;
import shop.wesellbuy.secondproject.service.member.MemberService;
import shop.wesellbuy.secondproject.web.member.MemberDetailForm;
import shop.wesellbuy.secondproject.web.member.MemberOriginForm;
import shop.wesellbuy.secondproject.web.member.MemberUpdateForm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
@Slf4j
public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberJpaRepository memberJpaRepository;

    private int memberNum; // 저장된 회원 num

    @BeforeEach
    public void init() throws IOException {

        log.info("test init 시작");

        // 회원 정보 저장하기
        // MokMultipartFile 생성
        String fileName = "testFile1"; // 파일명
        String contentType = "jpg"; // 파일 확장자/ 파일타입
        String originFileName = fileName + "." + contentType;
        String filePath = "src/test/resources/testImages/" + fileName + "." + contentType;
        FileInputStream fileInputStream = new FileInputStream(filePath); // 첨부파일 읽어오기

        // MokMultipartFile 생성
        String fileName2 = "testFile2"; // 파일명
        String contentType2 = "png"; // 파일 확장자/ 파일타입
        String originFileName2 = fileName2 + "." + contentType2;
        String filePath2 = "src/test/resources/testImages/" + fileName2 + "." + contentType2;
        FileInputStream fileInputStream2 = new FileInputStream(filePath2); // 첨부파일 읽어오기

        MockMultipartFile file = new MockMultipartFile("image1", originFileName, contentType, fileInputStream);
        MockMultipartFile file2 = new MockMultipartFile("image12", originFileName2, contentType2, fileInputStream2);

        // 회원 가입
        MemberOriginForm memberOriginForm = new MemberOriginForm("a", "a1", "a1@a1.a1", "01012341234", "021231234", "korea", "s", "h", "123", "12345", file);
        MemberOriginForm memberOriginForm2 = new MemberOriginForm("a2", "a2", "a2@a1.a1", "01012341234", "021231234", "eu", "s", "h", "123", "12345", file2);
        MemberOriginForm memberOriginForm3 = new MemberOriginForm("a3", "a3", "a3@a1.a1", "01012341234", "021231234", "korea", "d", "h", "123", "12345", null);
        MemberOriginForm memberOriginForm4 = new MemberOriginForm("a4", "a4", "a4@a1.a1", "01012341234", "021231234", "eu", "s", "h", "123", "12345", file);
        MemberOriginForm memberOriginForm5 = new MemberOriginForm("a5", "a5", "a5@a1.a1", "01012341234", "021231234", "korea", "d", "h", "123", "12345", file);
        MemberOriginForm memberOriginForm6 = new MemberOriginForm("a6", "a6", "a6@a1.a1", "01012341234", "021231234", "us", "s", "h", "123", "12345", file2);
        MemberOriginForm memberOriginForm7 = new MemberOriginForm("a7", "a7", "a7@a1.a1", "01012341234", "021231234", "korea", "s", "h", "123", "12345", null);

        int memberNum = memberService.join(memberOriginForm);
        memberService.join(memberOriginForm2);
        memberService.join(memberOriginForm3);
        memberService.join(memberOriginForm4);
        memberService.join(memberOriginForm5);
        memberService.join(memberOriginForm6);
        memberService.join(memberOriginForm7);

        this.memberNum = memberNum;

        log.info("test init 끝");
    }


    /**
     * 회원 가입 확인
     * -> 회원 가입 가능할 때
     */
    @Test
//    @Rollback(value = false)
    public void 회원가입_가능_확인() throws IOException {
        // given
        // MokMultipartFile 생성
        String fileName = "testFile1"; // 파일명
        String contentType = "jpg"; // 파일 확장자/ 파일타입
        String originFileName = fileName + "." + contentType;
        String filePath = "src/test/resources/testImages/" + fileName + "." + contentType;
        FileInputStream fileInputStream = new FileInputStream(filePath); // 첨부파일 읽어오기

        MockMultipartFile file = new MockMultipartFile("image1", originFileName, contentType, fileInputStream);
        // MemberOriginForm 생성
        MemberOriginForm memberOriginForm = new MemberOriginForm("a", "a1", "a1@a1.a1", "01012341234", "021231234", "korea", "s", "h", "123", "12345", file);

        // when
        // 회원 가입
        int memberNum = memberService.join(memberOriginForm);

        // then
        // 회원 정보 찾기
        Member findMember = memberJpaRepository.findById(memberNum).orElseThrow();

        assertThat(findMember.getId()).isEqualTo(findMember.getId());
        assertThat(findMember.getName()).isEqualTo(findMember.getName());
        assertThat(findMember.getAddress()).isEqualTo(findMember.getAddress());
    }

    /**
     * 회원 가입 확인
     * -> 아이디 중복 예외 발생 확인
     */
    @Test
    public void 회원가입_불가_확인_by아이디중복() throws IOException {
        // given
        // MokMultipartFile 생성
        String fileName = "testFile1"; // 파일명
        String contentType = "jpg"; // 파일 확장자/ 파일타입
        String originFileName = fileName + "." + contentType;
        String filePath = "src/test/resources/testImages/" + fileName + "." + contentType;
        FileInputStream fileInputStream = new FileInputStream(filePath); // 첨부파일 읽어오기
        FileInputStream fileInputStream2 = new FileInputStream(filePath); // 첨부파일 읽어오기

        MockMultipartFile file = new MockMultipartFile("image1", originFileName, contentType, fileInputStream);
        MockMultipartFile file2 = new MockMultipartFile("image2", originFileName, contentType, fileInputStream2);
        // MemberOriginForm 생성
        MemberOriginForm memberOriginForm = new MemberOriginForm("a", "a1", "a1@a1.a1", "01012341234", "021231234", "korea", "s", "h", "123", "12345", file);

        // 회원 가입
        memberService.join(memberOriginForm);

        // when
        // then
        // 아이디 같은 회원 가입
        MemberOriginForm memberOriginForm2 = new MemberOriginForm("a", "a1", "a1@a1.a1", "01012341234", "021231234", "korea", "s", "h", "123", "12345", file2);
        assertThrows(ExistingIdException.class, () -> memberService.join(memberOriginForm2));
    }

    /**
     * 회원 정보 상세보기 확인
     * -> 첨부파일 있을 때, 없을 때
     */
    @Test
//    @Rollback(value = false)
    public void 회원정보_상세보기_확인() throws IOException {
        // given
        // 회원 가입하기
        String fileName = "testFile1"; // 파일명
        String contentType = "jpg"; // 파일 확장자/ 파일타입
        String originFileName = fileName + "." + contentType;
        String filePath = "src/test/resources/testImages/" + fileName + "." + contentType;
        FileInputStream fileInputStream = new FileInputStream(filePath); // 첨부파일 읽어오기

        MockMultipartFile file = new MockMultipartFile("image1", originFileName, contentType, fileInputStream);
        // 회원 가입
        // 첨부파일 있을 때
        MemberOriginForm memberOriginForm = new MemberOriginForm("a2", "b2", "a1@a1.a1", "01012341234", "021231234", "korea", "s", "h", "123", "12345", file);
        int memberNum = memberService.join(memberOriginForm);

        // 첨부파일 없을 때
        MemberOriginForm memberOriginForm2 = new MemberOriginForm("a2", "b3", "a1@a1.a1", "01012341234", "021231234", "korea", "s", "h", "123", "12345", null);
        int memberNum2 = memberService.join(memberOriginForm2);

        // when
        MemberDetailForm findMemberDetailForm = memberService.watchDetail(memberNum);
        MemberDetailForm findMemberDetailForm2 = memberService.watchDetail(memberNum2);

        // then
        // 첨부파일 있을 때
        assertThat(findMemberDetailForm.getId()).isEqualTo(memberOriginForm.getId());
        assertThat(findMemberDetailForm.getName()).isEqualTo(memberOriginForm.getName());
        assertThat(findMemberDetailForm.getAddress().getCountry()).isEqualTo(memberOriginForm.getCountry());
        assertThat(findMemberDetailForm.getSelfPictureForm().getOriginalFileName()).isEqualTo(memberOriginForm.getFile().getOriginalFilename());

        // 첨부파일 없을 때
        assertThat(findMemberDetailForm2.getId()).isEqualTo(memberOriginForm2.getId());
        assertThat(findMemberDetailForm2.getName()).isEqualTo(memberOriginForm2.getName());
        assertThat(findMemberDetailForm2.getAddress().getCountry()).isEqualTo(memberOriginForm2.getCountry());
        assertThat(findMemberDetailForm2.getSelfPictureForm()).isEqualTo(memberOriginForm2.getFile());
        log.info("selfPictureForm2 : {}", findMemberDetailForm2.getSelfPictureForm());
        log.info("selfOriginalForm2.getFile() : {}", memberOriginForm2.getFile());
    }

    /**
     * 회원 정보 수정 확인
     */
    @Test
    @Rollback(value = false)
    public void 회원정보_수정_확인() throws IOException {
        // given
        // 수정 Form 만들기
        // 회원 picture 삭제
        MemberUpdateForm memberUpdateForm = new MemberUpdateForm("b2@b2.com", "01012341238", "0631231234", "e", "1", "st", "mu", "78945", null);

        // 회원 picture 존재
        String fileName = "testFile2"; // 파일명
        String contentType = "png"; // 파일 확장자/ 파일타입
        String originFileName = fileName + "." + contentType;
        String filePath = "src/test/resources/testImages/" + fileName + "." + contentType;
        FileInputStream fileInputStream = new FileInputStream(filePath); // 첨부파일 읽어오기
        MockMultipartFile file = new MockMultipartFile("image3", originFileName, contentType, fileInputStream);

        MemberUpdateForm memberUpdateForm2 = new MemberUpdateForm("b3@b3.com", "01012341238", "0631231234", "e", "1", "st", "mu", "78945", file);

        // when
        memberService.update(memberUpdateForm, memberNum);
        memberService.update(memberUpdateForm2, memberNum);

        // then
        Member findMember = memberJpaRepository.findById(memberNum).orElseThrow();

        //// 파일 없을 때
//        assertThat(findMember.getSelfPicture()).isEqualTo(memberUpdateForm.getFile());
//        assertThat(findMember.getEmail()).isEqualTo(memberUpdateForm.getEmail());
//        log.info("findMember.getEmail() : {}", findMember.getEmail());
//        log.info("memberUpdateForm.getEmail() : {}", memberUpdateForm.getEmail());
//        assertThat(findMember.getPhones().getSelfPhone()).isEqualTo(memberUpdateForm.getSelfPhone());
//        assertThat(findMember.getAddress().getCity()).isEqualTo(memberUpdateForm.getCity());
//        // 변하지 않는 것 확인
//        assertThat(findMember.getName()).isEqualTo("a");
//        assertThat(findMember.getId()).isEqualTo("a1");

        //// 파일 있을 때
        assertThat(findMember.getSelfPicture().getOriginalFileName()).isEqualTo(memberUpdateForm2.getFile().getOriginalFilename());
        assertThat(findMember.getEmail()).isEqualTo(memberUpdateForm2.getEmail());
        log.info("findMember.getEmail() : {}", findMember.getEmail());
        log.info("memberUpdateForm.getEmail() : {}", memberUpdateForm2.getEmail());
        assertThat(findMember.getPhones().getSelfPhone()).isEqualTo(memberUpdateForm2.getSelfPhone());
        assertThat(findMember.getAddress().getCity()).isEqualTo(memberUpdateForm2.getCity());
        // 변하지 않는 것 확인
        assertThat(findMember.getName()).isEqualTo("a");
        assertThat(findMember.getId()).isEqualTo("a1");
    }

    /**
     * 회원 탈퇴 확인
     * -> status 바꼈는지 확인(J -> W)
     */
    @Test
    @Rollback(value = false)
    public void 회원_탈퇴_확인() {
        // given
        // when
        // 회원 탈퇴하기
        memberService.withdrawal(memberNum);

        // then
        Member findMember = memberJpaRepository.findById(memberNum).orElseThrow();

        assertThat(findMember.getStatus()).isEqualTo(MemberStatus.W);
    }

    /**
     * 회원 page 불러오기 확인
     */
    @Test
    @Rollback(value = false)
    public void 회원_page_불러오기_확인() {
        // given
        // 페이지, 사이즈 정하기
        Pageable pageablePage0Size10 = PageRequest.of(0, 10);
        Pageable pageablePage1Size5 = PageRequest.of(1, 5);
        Pageable pageablePage0Size5 = PageRequest.of(0, 5);
        Pageable pageablePage2Size7 = PageRequest.of(2, 7);

        // 날짜 condition
        String today = "2023-01-26";
        String otherDay = "2023-01-27";

        // 검색조건 생성
        // when
        // 조건 1
        // 조건 0개
        MemberSearchCond memberSearchCond0 = new MemberSearchCond("", "", "", ""); // 7
        // 조건 1개
        MemberSearchCond memberSearchCond1 = new MemberSearchCond("a", null, null, null); // 7
        MemberSearchCond memberSearchCond2 = new MemberSearchCond(null, "k", null, null); // 4
        MemberSearchCond memberSearchCond3 = new MemberSearchCond(null, null, "s", null); // 5
        MemberSearchCond memberSearchCond4 = new MemberSearchCond(null, null, null, today); // 7
        // 조건 2개
        MemberSearchCond memberSearchCond21 = new MemberSearchCond("a", "korea", null, null); // 4
        MemberSearchCond memberSearchCond22 = new MemberSearchCond("a", null, "d", null); // 2
        MemberSearchCond memberSearchCond23 = new MemberSearchCond("a", null, null, today);// 7
        MemberSearchCond memberSearchCond24 = new MemberSearchCond(null, "u", null, today);// 3
        MemberSearchCond memberSearchCond25 = new MemberSearchCond(null, "u", "a", null);// 0
        MemberSearchCond memberSearchCond26 = new MemberSearchCond(null, null, "d", today);// 2
        MemberSearchCond memberSearchCond27 = new MemberSearchCond(null, "a", "b", null);// 0
        // 조건 3개
        MemberSearchCond memberSearchCond31 = new MemberSearchCond(null, "a", "b", otherDay);// 0
        MemberSearchCond memberSearchCond32 = new MemberSearchCond("a", "e", "s", null);// 4
        MemberSearchCond memberSearchCond33 = new MemberSearchCond("1", "kore", null, today);// 1
        MemberSearchCond memberSearchCond34 = new MemberSearchCond("b", null, "s", today);// 0
        // 조건 4개
        MemberSearchCond memberSearchCond41 = new MemberSearchCond("a", "k", "s", today);// 2


        // then
        // 조건 0
//        findAllWithCondTestWithQuantity(memberSearchCond0, pageablePage0Size5, 5);
        // 조건 1
//        findAllWithCondTestWithQuantity(memberSearchCond1, pageablePage0Size10, 7);
//        findAllWithCondTestWithQuantity(memberSearchCond2, pageablePage0Size10, 4);
//        findAllWithCondTestWithQuantity(memberSearchCond3, pageablePage0Size10, 5);
//        findAllWithCondTestWithQuantity(memberSearchCond4, pageablePage0Size10, 7);
        // 조건 2
        // pageable page1 size 5 일 때
//        findAllWithCondTestWithQuantity(memberSearchCond21, pageablePage1Size5, 0);
//        findAllWithCondTestWithQuantity(memberSearchCond22, pageablePage1Size5, 0);
//        findAllWithCondTestWithQuantity(memberSearchCond23, pageablePage1Size5, 2);
//        findAllWithCondTestWithQuantity(memberSearchCond24, pageablePage1Size5, 0);
//        findAllWithCondTestWithQuantity(memberSearchCond25, pageablePage1Size5, 0);
//        findAllWithCondTestWithQuantity(memberSearchCond26, pageablePage1Size5, 0);
//        findAllWithCondTestWithQuantity(memberSearchCond27, pageablePage1Size5, 0);
        // pageable page0 size 10 일 때
//        findAllWithCondTestWithQuantity(memberSearchCond21, pageablePage0Size10, 4);
//        findAllWithCondTestWithQuantity(memberSearchCond22, pageablePage0Size10, 2);
//        findAllWithCondTestWithQuantity(memberSearchCond23, pageablePage0Size10, 7);
//        findAllWithCondTestWithQuantity(memberSearchCond24, pageablePage0Size10, 3);
//        findAllWithCondTestWithQuantity(memberSearchCond25, pageablePage0Size10, 0);
//        findAllWithCondTestWithQuantity(memberSearchCond26, pageablePage0Size10, 2);
//        findAllWithCondTestWithQuantity(memberSearchCond27, pageablePage0Size10, 0);

        // 조건 3
//        findAllWithCondTestWithQuantity(memberSearchCond31, pageablePage0Size5, 0);
//        findAllWithCondTestWithQuantity(memberSearchCond32, pageablePage0Size5, 4);
//        findAllWithCondTestWithQuantity(memberSearchCond33, pageablePage0Size5, 1);
//        findAllWithCondTestWithQuantity(memberSearchCond34, pageablePage0Size5, 0);

        // 조건 4
        // pageable page0 size 5 일 때
        findAllWithCondTestWithQuantity(memberSearchCond41, pageablePage0Size5, 2);
        // pageable page2 size 7 일 때
        findAllWithCondTestWithQuantity(memberSearchCond41, pageablePage2Size7, 0);



    }

    /**
     * pageable의 조건에 따라 찾은 MemberDetailForm 개수를 비교
     */
    private void findAllWithCondTestWithQuantity(MemberSearchCond memberSearchCond, Pageable pageable, int quantity) {
        Page<MemberDetailForm> findMemberDetailForms = memberService.selectList(memberSearchCond, pageable);

        // Page<>에 있는 객체의 수이다. 전체가 아니라
        assertThat(findMemberDetailForms.getContent().size()).isEqualTo(quantity);
    }







}
