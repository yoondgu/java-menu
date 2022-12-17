package menu.controller;

import java.util.List;
import java.util.Map;
import menu.controller.util.ExceptionHandler;
import menu.model.MenuRecommender;
import menu.model.domain.Coaches;
import menu.view.MenuView;

public class MenuController {
    private final MenuView menuView = new MenuView();
    private MenuRecommender menuRecommender;

    public void run() {
        menuView.printInformStart();
        // TODO 각 입력값마다 재요청하게 만들기
        ExceptionHandler.retryForIllegalArgument(this::initializeCoaches, menuView::printError);
        recommend();
    }

    private void initializeCoaches() {
        List<String> coachNames = menuView.inputCoachNames();
        List<List<String>> dislikeMenus = menuView.inputDisLikeMenus(coachNames);
        Coaches coaches = new Coaches(coachNames, dislikeMenus);
        menuRecommender = new MenuRecommender(coaches);
    }

    private void recommend() {
        List<String> dailyCategories = menuRecommender.getDailyCategories();
        Map<String, List<String>> coachMenus = menuRecommender.getCoachMenus();
        menuView.printResult(dailyCategories, coachMenus);
    }
}
