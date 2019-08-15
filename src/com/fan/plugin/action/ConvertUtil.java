package com.fan.plugin.action;

import java.util.regex.Pattern;

/**
 * 请填写类的描述
 *
 * @author maxiaoyao
 * @date 2019-08-15 18:56
 */
public class ConvertUtil {

    // 1 全大写 2全小写 3camel_case
    static Pattern ALL_UPPER_CASE = Pattern.compile("[A-Z0-9]+");
    static Pattern ALL_LOW_CASE = Pattern.compile("[a-z0-9]+");
    static Pattern UPPER_CASE_AND_PASCAL_CASE = Pattern.compile("[_A-Z0-9]+");
    static Pattern LOW_CASE_AND_PASCAL_CASE = Pattern.compile("[_a-z0-9]+");
    static final char PASCAL_CHAR = '_';
    static final String PASCAL_STRING = "_";

    /**
     * @param text
     * @return
     */
    private static ConvertModel getModel(String text) {
        if (ALL_UPPER_CASE.matcher(text).matches()) {
            return ConvertModel.ALL_UPPER_CASE;
        }
        if (ALL_LOW_CASE.matcher(text).matches()) {
            return ConvertModel.ALL_LOW_CASE;
        }
        if (isPascalCase(text)) {
            if (UPPER_CASE_AND_PASCAL_CASE.matcher(text).matches()) {
                return ConvertModel.UPPER_CASE_AND_PASCAL_CASE;
            } else {
                return ConvertModel.LOW_CASE_AND_PASCAL_CASE;
            }
        }
        return ConvertModel.CAMEL_CASE;
    }

    private static boolean isPascalCase(String text) {
        return text.contains(PASCAL_STRING);
    }

    public static String getResult(String text) {
        final ConvertModel model = getModel(text);
        switch (model) {
            case ALL_LOW_CASE:
                return text.toUpperCase();
            case ALL_UPPER_CASE:
                return text.toLowerCase();
            case CAMEL_CASE:
                return camelCaseToUpCaseWithPascal(text);
            case UPPER_CASE_AND_PASCAL_CASE:
                return text.toLowerCase();
            case LOW_CASE_AND_PASCAL_CASE:
                return lowCaseWithPascalToCamelCase(text);
            default:
                return camelCaseToUpCaseWithPascal(text);
        }
    }

    private static String lowCaseWithPascalToCamelCase(String text) {
        StringBuilder builder = new StringBuilder(text.length());
        final char[] chars = text.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (i > 0 && chars[i - 1] == PASCAL_CHAR) {
                builder.append(Character.toUpperCase(chars[i]));
            } else if (chars[i] != PASCAL_CHAR) {
                builder.append(chars[i]);
            }
        }
        return builder.toString();
    }

    private static String camelCaseToUpCaseWithPascal(String text) {
        StringBuilder builder = new StringBuilder(text.length() + 8);
        final char[] chars = text.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            final char aChar = chars[i];
            if (i > 0 && Character.isUpperCase(aChar)) {
                builder.append(PASCAL_CHAR);
            }
            builder.append(Character.toUpperCase(aChar));
        }
        return builder.toString();
    }
}
