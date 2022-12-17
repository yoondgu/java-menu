package menu.model;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import menu.model.domain.Category;
import menu.model.domain.Coach;

public class MenuRecommender {
    public static final int COACHES_SIZE_MIN = 2;
    public static final int COACHES_SIZE_MAX = 5;
    public static final int DAY_SIZE = 5;

    private final List<Coach> coaches;
    private final Map<Coach, List<String>> coachMenus = new LinkedHashMap<>();
    private final List<Category> dailyCategories = new ArrayList<>();

    public MenuRecommender(List<String> names, List<List<String>> dislikeMenus) {
        this.coaches = makeCoaches(names, dislikeMenus);
        coaches.forEach(coach -> coachMenus.put(coach, new ArrayList<>()));
        updateDailyCategories();
        updateCoachMenus();
    }

    private List<Coach> makeCoaches(List<String> names, List<List<String>> dislikeMenus) {
        validate(names);
        return IntStream.range(0, names.size())
                .mapToObj(index -> new Coach(names.get(index), dislikeMenus.get(index)))
                .collect(Collectors.toList());
    }

    // TODO validate 클래스 분리
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

    private void updateDailyCategories() {
        while (dailyCategories.size() < DAY_SIZE) {
            Category category = pickCategory();
            if (!dailyCategories.contains(category)) {
                dailyCategories.add(category);
            }
        }
    }

    private void updateCoachMenus() {
        // TODO IllegalState 예외처리
        dailyCategories.forEach(this::updateCoachMenusByCategory);
    }

    public List<String> getDailyCategories() {
        // TODO IllegalState 예외처리
        return dailyCategories.stream()
                .map(Category::getName)
                .collect(Collectors.toList());
    }

    public Map<String, List<String>> getCoachMenus() {
        // TODO IllegalState 예외처리
        Map<String, List<String>> result = coachMenus.keySet()
                .stream()
                .collect(Collectors.toMap(Coach::getName, coachMenus::get));
        return new LinkedHashMap<>(result);
    }

    private void updateCoachMenusByCategory(Category category) {
        coaches.forEach(coach -> pickMenusForCoach(category, coach));
    }

    private void pickMenusForCoach(Category category, Coach coach) {
        List<String> currentMenus = coachMenus.get(coach);
        String menu = category.getRandomMenu();
        if (currentMenus.contains(menu)) {
            pickMenusForCoach(category, coach);
            return;
        }
        currentMenus.add(menu);
        coachMenus.put(coach, currentMenus);
    }

    private Category pickCategory() {
        return Category.takeRandomCategory(generateRandomValue());
    }

    private int generateRandomValue() {
        return Randoms.pickNumberInRange(1, 5);
    }
}
