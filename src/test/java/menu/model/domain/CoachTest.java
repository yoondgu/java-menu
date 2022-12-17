package menu.model.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CoachTest {
    @ParameterizedTest
    @ValueSource(strings = {"ㄱ", "가나다라마"})
    void 코치_정보_저장_코치이름_예외테스트(String name) {
        assertThatThrownBy(() -> new Coach(name, List.of("김밥", "떡볶이")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 코치_정보_저장_메뉴이름_예외테스트() {
        assertThatThrownBy(() -> new Coach("포비", List.of("김밥", "엽떡")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 코치_정보_저장_메뉴이름_중복_예외테스트() {
        assertThatThrownBy(() -> new Coach("포비", List.of("김밥", "김밥")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 코치_정보_저장_메뉴이름_개수_예외테스트() {
        assertThatThrownBy(() -> new Coach("포비", List.of("김밥", "떡볶이", "김치찌개")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 코치_정보_저장_메뉴_0개_허용() {
        Coach coach = new Coach("포비", Collections.emptyList());
        System.out.println(coach);
    }
}