package menu.controller;

import java.util.List;
import java.util.Map;
import menu.controller.util.ExceptionHandler;
import menu.model.MenuRecommender;
import menu.view.MenuView;

public class MenuController {
    private final MenuView menuView = new MenuView();
    private MenuRecommender menuRecommender;

    public void run() {
        menuView.printInformStart();
        ExceptionHandler.retryForIllegalArgument(this::initializeCoaches, menuView::printError);
        recommend();
    }

    private void initializeCoaches() {
        List<String> coachNames = menuView.inputCoachNames();
        List<List<String>> dislikeMenus = menuView.inputDisLikeMenus(coachNames);
        menuRecommender = new MenuRecommender(coachNames, dislikeMenus);
    }

    private void recommend() {
        List<String> dailyCategories = menuRecommender.getDailyCategories();
        Map<String, List<String>> coachMenus = menuRecommender.getCoachMenus();
        System.out.println(coachMenus);
        menuView.printResult(dailyCategories, coachMenus);
    }
}
