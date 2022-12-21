package menu.model.domain;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Coaches {
    private static final int COACHES_SIZE_MIN = 2;
    private static final int COACHES_SIZE_MAX = 5;

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

    private Coach findCoachByName(String name) {
        return coaches.stream()
                .filter(coach -> Objects.equals(coach.getName(), name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 이름을 가진 코치가 존재하지 않습니다."));
    }

    public void updateDislikeMenusByCoach(String coachName, DislikeMenus dislikeMenus) {
        Coach coach = findCoachByName(coachName);
        coach.updateDislikeMenus(dislikeMenus);
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
}
