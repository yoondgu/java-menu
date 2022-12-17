package menu.model.domain;

import java.util.ArrayList;
import java.util.List;

public class Coach {
    public static final int NAME_MAX = 4;
    public static final int NAME_MIN = 2;
    public static final int MENU_SIZE_MAX = 2;

    private final String name;
    private final List<String> dislikeMenus = new ArrayList<>();

    public Coach(String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(String name) {
        if (name.length() < NAME_MIN || name.length() > NAME_MAX) {
            throw new IllegalArgumentException("코치 이름의 길이는 최소 2, 최대 4여야 합니다.");
        }
    }

    public void addDisLikeMenus(List<String> menus) {
        validateMenus(menus);
        dislikeMenus.addAll(menus);
    }

    private void validateMenus(List<String> menus) {
        if (menus.size() > MENU_SIZE_MAX) {
            throw new IllegalArgumentException("못 먹는 메뉴 개수는 최대 2여야 합니다.");
        }
        validateDuplicatedMenu(menus);
        menus.forEach(this::validateMenuName);
    }

    private void validateDuplicatedMenu(List<String> menus) {
        int removeDuplicated = (int) menus.stream()
                .distinct()
                .count();
        if (removeDuplicated != menus.size()) {
            throw new IllegalArgumentException("중복된 메뉴 이름을 입력할 수 없습니다.");
        }
    }

    private void validateMenuName(String menuName) {
        if (!Category.isExistMenu(menuName)) {
            throw new IllegalArgumentException("못 먹는 메뉴의 이름이 메뉴 정보에 존재하지 않습니다.");
        }
    }

    public String getName() {
        return name;
    }

    public List<String> getDislikeMenus() {
        return dislikeMenus;
    }

    @Override
    public String toString() {
        return "Coach{" +
                "name='" + name + '\'' +
                ", dislikeMenus=" + dislikeMenus +
                '}';
    }
}
