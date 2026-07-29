import com.back.domain.wiseSaying.dto.PageDto;
import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.domain.wiseSaying.repository.WiseSayingFileRepository;
import com.back.global.AppContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class WiseSayingFileRepositoryTest {

    private WiseSayingFileRepository wiseSayingFileRepository;

    public WiseSayingFileRepositoryTest() {
        AppContext.init(true);
        wiseSayingFileRepository = AppContext.wiseSayingFileRepository;
    }
    @BeforeEach
    void clearDb() {
        WiseSayingFileRepository.clear();
    }
    @Test
    @DisplayName("명언 저장")
    void t1() {
        WiseSaying wiseSaying = new WiseSaying("꿈을 지녀라. 그러면 어려운 현실을 이길 수 있다.", "괴테");

        wiseSayingFileRepository.save(wiseSaying);

        WiseSaying foundedWiseSaying = wiseSayingFileRepository.findById(1).get();

        assertThat(foundedWiseSaying).isEqualTo(wiseSaying);

    }
    @Test
    @DisplayName("2개 이상의 명언 저장")
    void t2() {
        WiseSaying wiseSaying1 = new WiseSaying("꿈을 지녀라. 그러면 어려운 현실을 이길 수 있다.", "괴테");
        WiseSaying wiseSaying2 = new WiseSaying("너 자신을 알라", "소크라테스");

        wiseSayingFileRepository.save(wiseSaying1);
        wiseSayingFileRepository.save(wiseSaying2);

        WiseSaying foundedWiseSaying1 = wiseSayingFileRepository.findById(1).get();
        assertThat(foundedWiseSaying1).isEqualTo(wiseSaying1);

        WiseSaying foundedWiseSaying2 = wiseSayingFileRepository.findById(2).get();
        assertThat(foundedWiseSaying2).isEqualTo(wiseSaying2);


    }
    @Test
    @DisplayName("명언 삭제")
    void t3() {
        WiseSaying wiseSaying1 = new WiseSaying("꿈을 지녀라. 그러면 어려운 현실을 이길 수 있다.", "괴테");
        WiseSaying wiseSaying2 = new WiseSaying("너 자신을 알라", "소크라테스");

        wiseSaying1 = wiseSayingFileRepository.save(wiseSaying1);
        wiseSaying2 = wiseSayingFileRepository.save(wiseSaying2);

        wiseSayingFileRepository.delete(wiseSaying1); // id를 가지고 삭제

        WiseSaying foundedWiseSaying1 = wiseSayingFileRepository.findById(1).orElse(null);
        assertThat(foundedWiseSaying1).isNull();

    }
    @Test
    @DisplayName("명언 수정")
    void t4() {
        WiseSaying wiseSaying1 = new WiseSaying("꿈을 지녀라. 그러면 어려운 현실을 이길 수 있다.", "괴테");
        wiseSayingFileRepository.save(wiseSaying1);

        WiseSaying wiseSaying = wiseSayingFileRepository.findById(1).get();

        wiseSaying.setSaying("너 자신을 알라");
        wiseSaying.setAuthor("소크라테스");

        wiseSayingFileRepository.save(wiseSaying); // id를 가지고 삭제

        WiseSaying foundedWiseSaying1 = wiseSayingFileRepository.findById(1).get();

        assertThat(foundedWiseSaying1.getId())
                .isEqualTo(1);

        assertThat(foundedWiseSaying1.getSaying())
                .isEqualTo("너 자신을 알라");

        assertThat(foundedWiseSaying1.getAuthor())
                .isEqualTo("소크라테스");
    }
    @Test
    @DisplayName("명언 다건 조회 - 모든 명언 조회")
    void t5() {

        WiseSaying wiseSaying1 = new WiseSaying("꿈을 지녀라. 그러면 어려운 현실을 이길 수 있다.", "괴테");
        wiseSayingFileRepository.save(wiseSaying1);

        WiseSaying wiseSaying2 = new WiseSaying("너 자신을 알라.", "소크라테스");
        wiseSayingFileRepository.save(wiseSaying2);

        WiseSaying wiseSaying3 = new WiseSaying("꿈은 현실이 된다.", "작자미상");
        wiseSayingFileRepository.save(wiseSaying3);

        List<WiseSaying> wiseSayings = wiseSayingFileRepository.findAll();

        assertThat(wiseSayings)
                .containsExactly(
                        wiseSaying1,
                        wiseSaying2,
                        wiseSaying3
                );

    }
    @Test
    @DisplayName("명언 다건 조회 - content 필터링")
    void t6() {

        WiseSaying wiseSaying1 = new WiseSaying("꿈을 지녀라. 그러면 어려운 현실을 이길 수 있다.", "괴테");
        wiseSayingFileRepository.save(wiseSaying1);

        WiseSaying wiseSaying2 = new WiseSaying("너 자신을 알라.", "소크라테스");
        wiseSayingFileRepository.save(wiseSaying2);

        WiseSaying wiseSaying3 = new WiseSaying("꿈은 현실이 된다.", "작자미상");
        wiseSayingFileRepository.save(wiseSaying3);

        PageDto pageDto = wiseSayingFileRepository.findByContentContainingIdDesc("꿈", 5, 1);

        assertThat(pageDto.getContent())
                .containsExactly(
                        wiseSaying3,
                        wiseSaying1
                );

    }
    @Test
    @DisplayName("명언 다건 조회 - author 필터링")
    void t7() {

        WiseSaying wiseSaying1 = new WiseSaying("꿈을 지녀라. 그러면 어려운 현실을 이길 수 있다.", "괴테");
        wiseSayingFileRepository.save(wiseSaying1);

        WiseSaying wiseSaying2 = new WiseSaying("너 자신을 알라.", "소크라테스");
        wiseSayingFileRepository.save(wiseSaying2);

        WiseSaying wiseSaying3 = new WiseSaying("꿈은 현실이 된다.", "작자미상");
        wiseSayingFileRepository.save(wiseSaying3);

        PageDto pageDto = wiseSayingFileRepository.findByAuthorContainingIdDesc("테", 5, 1);

        assertThat(pageDto.getContent())
                .containsExactly(
                        wiseSaying2,
                        wiseSaying1
                );

    }

    @Test
    @DisplayName("명언 다건 조회 - author, content 필터링")
    void t8() {

        WiseSaying wiseSaying1 = new WiseSaying("꿈을 지녀라. 그러면 어려운 현실을 이길 수 있다.", "괴테");
        wiseSayingFileRepository.save(wiseSaying1);

        WiseSaying wiseSaying2 = new WiseSaying("너 자신을 알라.", "소크라테스");
        wiseSayingFileRepository.save(wiseSaying2);

        WiseSaying wiseSaying3 = new WiseSaying("꿈은 현실이 된다.", "작자미상");
        wiseSayingFileRepository.save(wiseSaying3);

        WiseSaying wiseSaying4 = new WiseSaying("잠을 잘 자야 합니다.", "꿈꾸는자");
        wiseSayingFileRepository.save(wiseSaying4);

        PageDto pageDto = wiseSayingFileRepository.findByContentContainingOrAuthorContainingIdDesc("꿈", 5, 1);

        assertThat(pageDto.getContent())
                .containsExactly(
                        wiseSaying4,
                        wiseSaying3,
                        wiseSaying1
                );

    }
}