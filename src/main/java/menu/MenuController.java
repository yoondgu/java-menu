package menu;

import menu.view.MenuView;

public class MenuController {
    private final MenuView menuView = new MenuView();

    public void run() {
        menuView.printInformStart();
    }
}
