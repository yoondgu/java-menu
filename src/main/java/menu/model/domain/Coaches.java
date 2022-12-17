package menu.model.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Coaches {
    public static final int COACHES_SIZE_MIN = 2;
    public static final int COACHES_SIZE_MAX = 5;

    private final List<Coach> coaches;

    public Coaches(List<String> names, List<List<String>> disLikeMenus) {
        this.coaches = makeCoaches(names, disLikeMenus);
    }

    public List<Coach> coaches() {
        return Collections.unmodifiableList(coaches);
    }

    private List<Coach> makeCoaches(List<String> names, List<List<String>> dislikeMenus) {
        validateMatchingMenus(names.size(), dislikeMenus.size());
        validateNames(names);
        return IntStream.range(0, names.size())
                .mapToObj(index -> new Coach(names.get(index), dislikeMenus.get(index)))
                .collect(Collectors.toList());
    }

    private void validateNames(List<String> names) {
        validateNameSize(names);
        validateDuplicatedName(names);
    }

    private void validateMatchingMenus(int namesSize, int menusSize) {
        if (namesSize != menusSize) {
            throw new IllegalArgumentException("코치 이름 수와 못 먹는 메뉴 목록의 수가 일치하지 않습니다.");
        }
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
}
