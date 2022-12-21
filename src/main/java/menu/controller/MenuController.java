package menu.controller;

import java.util.List;
import java.util.Map;
import menu.controller.util.ExceptionHandler;
import menu.model.MenuRecommender;
import menu.model.domain.Coaches;
import menu.model.domain.DislikeMenus;
import menu.view.InputView;
import menu.view.OutputView;

public class MenuController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        outputView.printInformStart();
        Coaches coaches = ExceptionHandler.retryForIllegalArgument(Coaches::new, inputView::inputCoachNames,
                outputView::printError);
        coaches.getNames()
                .forEach(coachNames -> saveDislikeMenusByCoach(coaches, coachNames));
        recommend(coaches);
    }

    private void saveDislikeMenusByCoach(Coaches coaches, String coachName) {
        DislikeMenus dislikeMenus = ExceptionHandler.retryForIllegalArgument(DislikeMenus::new,
                () -> inputView.inputDisLikeMenu(coachName),
                outputView::printError);
        coaches.updateDislikeMenusByCoach(coachName, dislikeMenus);
    }

    private void recommend(Coaches coaches) {
        MenuRecommender menuRecommender = new MenuRecommender(coaches);
        List<String> dailyCategories = menuRecommender.getDailyCategories();
        Map<String, List<String>> coachMenus = menuRecommender.getCoachMenus();
        outputView.printResult(dailyCategories, coachMenus);
    }
}
