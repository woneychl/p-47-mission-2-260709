package util;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UtilFileTest {
    @BeforeAll
    static void beforeAll() {
        com.back.global.Util.file.mkdir("temp");
    }

    @AfterAll
    static void afterAll() {
        com.back.global.Util.file.rmdir("temp");
    }
    @Test
    @DisplayName("파일 생성")
    void t1() {

        // 무언가를 세팅하고
        String filePath = "temp/test.txt";

        // 수행하면
        com.back.global.Util.file.touch(filePath);

        // 결과가 나온다. => 실제 파일이 존재하는가?
        boolean rst = com.back.global.Util.file.exists(filePath);

        assertThat(rst).isTrue();
        com.back.global.Util.file.delete(filePath);
    }
    @Test
    @DisplayName("파일 삭제")
    void t2() {

        // 무언가를 세팅하고
        String filePath = "temp/test.txt";

        // 수행하면
        com.back.global.Util.file.touch(filePath);

        com.back.global.Util.file.delete(filePath);
        // 결과가 나온다. => 실제 파일이 존재하는가?
        boolean rst = com.back.global.Util.file.exists(filePath);

        assertThat(rst).isFalse();

    }

    @Test
    @DisplayName("파일 읽기/쓰기")
    void t3() {

        // given
        String filePath = "temp/test.txt";
        com.back.global.Util.file.set(filePath, "hello world"); // 파일 쓰기

        // when
        String content = com.back.global.Util.file.get(filePath, "");

        // then
        assertThat(content).isEqualTo("hello world");

    }

}
