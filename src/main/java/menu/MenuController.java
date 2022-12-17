package menu;

import java.util.List;
import menu.view.MenuView;

public class MenuController {
    private final MenuView menuView = new MenuView();

    public void run() {
        menuView.printInformStart();
        List<String> coachNames = menuView.inputCoachNames();
        System.out.println(coachNames);
    }
}
