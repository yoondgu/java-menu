package menu.controller;

import java.util.List;
import menu.controller.util.ExceptionHandler;
import menu.model.MenuRecommender;
import menu.view.MenuView;

public class MenuController {
    private final MenuView menuView = new MenuView();
    private MenuRecommender menuRecommender;

    public void run() {
        menuView.printInformStart();
        ExceptionHandler.retryForIllegalArgument(this::initializeCoaches, menuView::printError);
        initializeCoaches();
        // TODO 추천 정보 생성
    }

    private void initializeCoaches() {
        List<String> coachNames = menuView.inputCoachNames();
        List<List<String>> dislikeMenus = menuView.inputDisLikeMenus(coachNames);
        menuRecommender = new MenuRecommender(coachNames, dislikeMenus);
    }
}
