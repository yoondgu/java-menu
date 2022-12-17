package menu.view.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TextParser {
    public static final String DELIMITER = ",";
    public static final String ERROR_INVALID_FORMAT = "잘못된 형식의 입력값입니다.";

    private TextParser() {
    }

    public static List<String> parseFormattedLine(String readLine) {
        String[] inputValues = readLine.split(DELIMITER);
        List<String> parsedValues = Arrays.stream(inputValues)
                .map(String::trim)
                .collect(Collectors.toList());
        validateValues(parsedValues);
        return parsedValues;
    }

    private static void validateValues(List<String> values) {
        if (values.isEmpty()) {
            throw new IllegalArgumentException(ERROR_INVALID_FORMAT);
        }
        values.forEach(TextParser::validateValue);
    }

    private static void validateValue(String value) {
        if (value.isEmpty()) {
            throw new IllegalArgumentException(ERROR_INVALID_FORMAT);
        }
    }
}
