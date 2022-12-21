package menu.model.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Coaches {
    public static final int COACHES_SIZE_MIN = 2;
    public static final int COACHES_SIZE_MAX = 5;

    private final List<Coach> coaches;

    public Coaches(List<String> names) {
        this.coaches = makeCoaches(names);
    }

    public List<Coach> coaches() {
        return Collections.unmodifiableList(coaches);
    }

    public List<String> getNames() {
        return coaches.stream()
                .map(Coach::getName)
                .collect(Collectors.toList());
    }

    private List<Coach> makeCoaches(List<String> names) {
        validateNames(names);
        return names.stream()
                .map(Coach::new)
                .collect(Collectors.toList());
    }

    public void updateDisLikeMenus(List<DislikeMenus> dislikeMenusByCoach) {
        validateDislikeMenusSize(dislikeMenusByCoach);
        IntStream.range(0, coaches.size())
                .forEach(index -> coaches.get(index).updateDislikeMenus(dislikeMenusByCoach.get(index)));
    }

    private void validateNames(List<String> names) {
        validateNameSize(names);
        validateDuplicatedName(names);
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

    private void validateDislikeMenusSize(List<DislikeMenus> menus) {
        if (coaches.size() != menus.size()) {
            throw new IllegalArgumentException("코치의 인원 수와 못 먹는 메뉴 정보 수가 일치하지 않습니다.");
        }
    }
}
