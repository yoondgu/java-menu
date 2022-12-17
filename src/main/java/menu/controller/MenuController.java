package menu.controller;

import java.util.List;
import menu.controller.util.ExceptionHandler;
import menu.view.MenuView;

public class MenuController {
    private final MenuView menuView = new MenuView();

    public void run() {
        menuView.printInformStart();
        ExceptionHandler.retryForIllegalArgument(this::initializeCoaches, menuView::printError);
        // TODO 코치 정보 생성
        // TODO 추천 정보 생성
//        List<String> dailyCategories = new ArrayList<>(List.of("aa, aa, aa"));
//        Map<String, List<String>> coachMenus = new LinkedHashMap<>(Map.of("bb", List.of("김", "밥")));
//        menuView.printResult(dailyCategories, coachMenus);
//        System.out.println(Category.takeRandomCategory(1));
//        System.out.println(Category.ASIAN.getRandomMenu());
//        System.out.println(Category.ASIAN.getRandomMenu());
//        System.out.println(Category.ASIAN.getRandomMenu());
    }

    // TODO 코치 정보 반환하도록 반환값 타입 변경, 코드 추가
    private List<String> initializeCoaches() {
        List<String> coachNames = menuView.inputCoachNames();
        List<List<String>> dislikeMenus = menuView.inputDisLikeMenus(coachNames);
        return null;
    }
}
