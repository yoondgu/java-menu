package menu.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import menu.model.domain.Category;
import menu.model.domain.Coach;
import menu.model.domain.Coaches;

public class MenuRecommender {
    private static final int DAY_SIZE = 5;
    private static final int DAILY_CATEGORY_MAX = 2;

    private final Coaches coaches;
    private final Map<Coach, List<String>> coachMenus = new LinkedHashMap<>();
    private final List<Category> dailyCategories = new ArrayList<>();

    public MenuRecommender(Coaches coaches) {
        this.coaches = coaches;
        initialize();
    }

    private void initialize() {
        coaches.coaches()
                .forEach(coach -> coachMenus.put(coach, new ArrayList<>()));
        updateDailyCategories();
        updateCoachMenus();
    }

    private void updateDailyCategories() {
        while (dailyCategories.size() < DAY_SIZE) {
            Category category = MenuPicker.pickCategory();
            if (!isTwiceDuplicated(category)) {
                dailyCategories.add(category);
            }
        }
    }

    private boolean isTwiceDuplicated(Category findCategory) {
        int foundCount = (int) dailyCategories.stream()
                .filter(category -> Objects.equals(category, findCategory))
                .count();
        return foundCount == DAILY_CATEGORY_MAX;
    }

    public List<String> getDailyCategories() {
        validateDailyCategories();
        return dailyCategories.stream()
                .map(Category::getName)
                .collect(Collectors.toList());
    }

    private void updateCoachMenus() {
        validateDailyCategories();
        dailyCategories.forEach(this::updateCoachMenusByCategory);
    }

    public Map<String, List<String>> getCoachMenus() {
        validateCoachMenusState();
        Map<String, List<String>> result = coachMenus.keySet()
                .stream()
                .collect(Collectors.toMap(Coach::getName, coachMenus::get));
        return new LinkedHashMap<>(result);
    }

    private void updateCoachMenusByCategory(Category category) {
        coaches.coaches()
                .forEach(coach -> pickMenusForCoach(category, coach));
    }

    private void pickMenusForCoach(Category category, Coach coach) {
        List<String> currentMenus = coachMenus.get(coach);
        String menu = category.getRandomMenu();
        if (currentMenus.contains(menu) || coach.dislike(menu)) {
            pickMenusForCoach(category, coach);
            return;
        }
        currentMenus.add(menu);
        coachMenus.put(coach, currentMenus);
    }

    private void validateDailyCategories() {
        if (dailyCategories.isEmpty()) {
            throw new IllegalStateException("요일 별 추천 카테고리 정보가 생성되지 않은 상태입니다.");
        }
    }

    private void validateCoachMenusState() {
        if (coachMenus.isEmpty()) {
            throw new IllegalStateException("코치 별 추천 메뉴 정보가 생성되지 않은 상태입니다.");
        }
    }
}
