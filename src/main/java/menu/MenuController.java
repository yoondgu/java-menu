package menu;

import java.util.List;
import menu.view.MenuView;

public class MenuController {
    private final MenuView menuView = new MenuView();

    public void run() {
        menuView.printInformStart();
        List<String> coachNames = menuView.inputCoachNames();
        List<List<String>> dislikeMenus = menuView.inputDisLikeMenus(coachNames);
        // TODO 코치 정보 생성
        // TODO 추천 정보 생성
//        List<String> dailyCategories = new ArrayList<>(List.of("aa, aa, aa"));
//        Map<String, List<String>> coachMenus = new LinkedHashMap<>(Map.of("bb", List.of("김", "밥")));
//        menuView.printResult(dailyCategories, coachMenus);
    }
}
