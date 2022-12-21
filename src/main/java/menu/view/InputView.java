package menu.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import menu.view.util.TextParser;

public class InputView {
    public static final String MESSAGE_INPUT_COACHES = "코치의 이름을 입력해 주세요. (, 로 구분)";
    public static final String FORMAT_INPUT_DISLIKE_MENU = "%s(이)가 못 먹는 메뉴를 입력해 주세요." + System.lineSeparator();

    public List<String> inputCoachNames() {
        System.out.println();
        System.out.println(MESSAGE_INPUT_COACHES);
        return TextParser.parseNotEmptyLine(Console.readLine());
    }

//    public List<List<String>> inputDisLikeMenus(List<String> coachNames) {
//        return coachNames.stream()
//                .map(this::inputDisLikeMenu)
//                .collect(Collectors.toList());
//    }

    public List<String> inputDisLikeMenu(String coachName) {
        System.out.println();
        System.out.printf(FORMAT_INPUT_DISLIKE_MENU, coachName);
        return TextParser.parseCouldEmptyLine(Console.readLine());
    }
}
