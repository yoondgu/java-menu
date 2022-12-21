package menu.model.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

class CoachesTest {
    @Test
    void 코치목록정보_저장_중복이름_예외발생() {
        assertThatThrownBy(() -> new Coaches(List.of("포비", "포비")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 코치목록정보_저장_인원수_예외발생() {
        assertThatThrownBy(() -> new Coaches(List.of("포비")))
                .isInstanceOf(IllegalArgumentException.class);
    }
}