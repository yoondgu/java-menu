package menu.model;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import menu.model.domain.Coach;

public class MenuRecommender {
    public static final int COACHES_SIZE_MIN = 2;
    public static final int COACHES_SIZE_MAX = 5;

    private final List<Coach> coaches;

    public MenuRecommender(List<String> names, List<List<String>> dislikeMenus) {
        this.coaches = makeCoaches(names, dislikeMenus);
    }

    private List<Coach> makeCoaches(List<String> names, List<List<String>> dislikeMenus) {
        validate(names);
        return IntStream.range(0, names.size())
                .mapToObj(index -> new Coach(names.get(index), dislikeMenus.get(index)))
                .collect(Collectors.toList());
    }

    private void validate(List<String> name) {
        validateNameSize(name);
        validateDuplicatedName(name);
    }

    private void validateNameSize(List<String> names) {
        if (names.size() < COACHES_SIZE_MIN || names.size() > COACHES_SIZE_MAX) {
            throw new IllegalArgumentException("식사할 코치의 인원 수는 최소 2, 최대 5여야 합니다.");
        }
    }

    private void validateDuplicatedName(List<String> names) {
        int removeDuplicated = (int) names.stream()
                .distinct()
                .count();
        if (removeDuplicated != names.size()) {
            throw new IllegalArgumentException("중복된 코치 이름을 입력할 수 없습니다.");
        }
    }

    // TODO 카테고리 랜덤으로 5개 저장
    public List<String> makeDailyCategories() {
        List<String> dailyCategories = new ArrayList<>();
        return null;
    }

    private int generateRandomValue() {
        return Randoms.pickNumberInRange(1, 5);
    }
    // TODO 코치 별로 특정 카테고리의 메뉴 5개 저장
}
