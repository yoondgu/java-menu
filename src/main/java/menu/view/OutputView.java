package menu.view;

import java.util.List;
import java.util.Map;
import menu.view.util.FormatMaker;

public class OutputView {
    public static final String MESSAGE_INFORM_START = "점심 메뉴 추천을 시작합니다.";
    public static final String MESSAGE_INFORM_RESULT = "메뉴 추천 결과입니다.";
    public static final String MESSAGE_INFORM_COMPLETE = "추천을 완료했습니다.";
    public static final String FORMAT_INFORM_ERROR = "[ERROR] %s" + System.lineSeparator();

    public void printInformStart() {
        System.out.println(MESSAGE_INFORM_START);
    }

    public void printResult(List<String> dailyCategories, Map<String, List<String>> coachMenus) {
        System.out.println();
        System.out.println(MESSAGE_INFORM_RESULT);
        System.out.println(FormatMaker.makeMenuResultsDisplay(dailyCategories, coachMenus));
        System.out.println();
        System.out.println(MESSAGE_INFORM_COMPLETE);
    }

    public void printError(String errorMessage) {
        System.out.printf(FORMAT_INFORM_ERROR, errorMessage);
    }
}
