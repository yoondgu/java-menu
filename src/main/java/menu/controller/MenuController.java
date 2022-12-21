package menu.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import menu.controller.util.ExceptionHandler;
import menu.model.MenuRecommender;
import menu.model.domain.Coaches;
import menu.model.domain.DislikeMenus;
import menu.view.InputView;
import menu.view.OutputView;

public class MenuController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private Coaches coaches;

    public void run() {
        outputView.printInformStart();
        ExceptionHandler.retryForIllegalArgument(this::askCoachNames, outputView::printError);
        ExceptionHandler.retryForIllegalArgument(this::askDislikeMenus, outputView::printError);
        recommend();
    }

    private void askCoachNames() {
        List<String> coachNames = inputView.inputCoachNames();
        coaches = new Coaches(coachNames);
    }

    private void askDislikeMenus() {
        List<List<String>> dislikeMenusByCoach = inputView.inputDisLikeMenus(coaches.getNames());
        List<DislikeMenus> dislikeMenus = dislikeMenusByCoach.stream()
                .map(DislikeMenus::new)
                .collect(Collectors.toList());
        coaches.updateDisLikeMenus(dislikeMenus);
    }

    private void recommend() {
        MenuRecommender menuRecommender = new MenuRecommender(coaches);
        List<String> dailyCategories = menuRecommender.getDailyCategories();
        Map<String, List<String>> coachMenus = menuRecommender.getCoachMenus();
        outputView.printResult(dailyCategories, coachMenus);
    }
}
