package com.epam.mjc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     * 1. access modifier - optional, followed by space: ' '
     * 2. return type - followed by space: ' '
     * 3. method name
     * 4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     * accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     * private void log(String value)
     * Vector3 distort(int x, int y, int z, float magnitude)
     * public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        MethodSignature result = null;
        String first = signatureString.substring(0, signatureString.indexOf("("));
        String second = signatureString.contains("()") ? "" : signatureString.substring(signatureString.indexOf("(") + 1, signatureString.indexOf(")"));

        String[] firstSplit = first.split(" ");
        String access = "";
        String retType = "";
        String method = "";
        int start = -1;
        if (firstSplit.length == 3) {
            start++;
            access = firstSplit[0];
        }
        retType = firstSplit[start + 1];
        method = firstSplit[start + 2];

        List<MethodSignature.Argument> args = new ArrayList<>();
        String[] secondSplit = second.split(", ");
        for (int i = 0; i < secondSplit.length && !Objects.equals(secondSplit[0], ""); i++) {
            String[] tmp = secondSplit[i].split(" ");
            args.add(new MethodSignature.Argument(tmp[0], tmp[1]));
        }
        result = new MethodSignature(method, args);
        result.setReturnType(retType);
        if (start == 0) {
            result.setAccessModifier(access);
        }
        return result;
    }
}
