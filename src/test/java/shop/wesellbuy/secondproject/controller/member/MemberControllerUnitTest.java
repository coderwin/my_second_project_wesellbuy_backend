package shop.wesellbuy.secondproject.controller.member;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockPart;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import shop.wesellbuy.secondproject.service.member.MemberService;
import shop.wesellbuy.secondproject.web.controller.MemberController;
import shop.wesellbuy.secondproject.web.member.MemberOriginForm;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * MemberController 단위 테스트
 */
//@WebMvcTest(MemberController.class)
@ExtendWith(MockitoExtension.class)
public class MemberControllerUnitTest {

    @InjectMocks // 가짜 객체 주입
    MemberController memberController;

    @Mock // 가짜 객체 생성
    MemberService memberService;

    MockMvc mockMvc;// Http 호출 가능

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(memberController).build();
    }

    @DisplayName("회원 가입 성공")
    @Test
    public void 회원_가입_성공() throws Exception {
        // given
        // 결과값 만들기
        int response = 1;
        // Mock 객체(가짜 객체)의 결과값 만들기
        doReturn(response).when(memberService).join(any(MemberOriginForm.class));

        /// 클라이언트 data 만들기
        // MockFile 만들기
        String fileName = "testFile1"; // 파일명
        String contentType = "jpg"; // 파일 확장자/ 파일타입
        String originFileName = fileName + "." + contentType;
        String filePath = "src/test/resources/testImages/" + fileName + "." + contentType;
        FileInputStream fileInputStream = new FileInputStream(filePath); // 첨부파일 읽어오기

        MockMultipartFile file = new MockMultipartFile("file", originFileName, contentType, fileInputStream);

        // == MemberOriginForm 만들기 그것을 json String으로 만들기
        MemberOriginForm memberOriginForm = new MemberOriginForm(
                "랄라",
                "number1",
                "asd123!@#",
                "asd123!@#",
                "number@naver2.com2",
                "01012341234",
                "021231234",
                "korea",
                "bu",
                "dong",
                "apart",
                "12345",
                null
        );
        // dto를 String json으로  만들기 그다음 byte[]/inputStream으로 만들기
        String body = new ObjectMapper().writeValueAsString(memberOriginForm);
        byte[] strJsonBody = body.getBytes(StandardCharsets.UTF_8);
        // dto를 바로 string으로
//        byte[] strJsonBody = memberOriginForm.toString().getBytes(StandardCharsets.UTF_8); (X)

//        MockMultipartFile jsonBody = new MockMultipartFile("image", "", "application/json", result);
        MockMultipartFile jsonBody = new MockMultipartFile(
                "memberOriginForm",
                "",
                "application/json",
                "{ \"id\": \"ok\", \"name\": \"ok\", \"pwd\": \"ads123!@#\", \"pwdConfirm\": \"ads123!@#\", \"email\": \"ok@ok.com\", \"country\": \"korea\", \"city\":\"bu1123\", \"street\": \"bu12\", \"detail\": \"bu1\", \"zipcode\": \"12345\", \"selfPhone\": \"01012341234\", \"homePhone\": \"021231234\"}".getBytes(StandardCharsets.UTF_8));

        MockMultipartFile jsonBody2 = new MockMultipartFile(
                "memberOriginForm",
                "",
                "application/json",
                strJsonBody);
        // when
        // test 메서드 실행하기
        // O 성공
//        ResultActions resultActions = mockMvc.perform(
//                multipart("/members")
//                        .file(file)
//                        .file(jsonBody)
//                        .contentType(MediaType.MULTIPART_FORM_DATA)
//                        .accept(MediaType.APPLICATION_JSON)
//        );
        // O 성공
        ResultActions resultActions = mockMvc.perform(
                multipart("/members")
                        .file(file)
                        .file(jsonBody2)
                        .contentType("multipart/form-data")
                        .accept(MediaType.APPLICATION_JSON)
        );

        // then
        // 결과 확인하기
        resultActions.andExpect(status().isCreated())
                .andExpect(jsonPath("data").value("회원가입 성공"))
                .andExpect(jsonPath("$.data").value("회원가입 성공"))
                .andDo(MockMvcResultHandlers.print());
    }

    @DisplayName("회원 가입 실패")
    @Test
    public void 회원_가입_실패() {

    }









}
