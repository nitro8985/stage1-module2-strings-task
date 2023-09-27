package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

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
        StringTokenizer st1 = new StringTokenizer(signatureString, "(");
        String beforeBrackets = st1.nextToken();
        String afterBrackets = st1.nextToken(")").replace("(", "");
        List<String> sig = new ArrayList<>();
        StringTokenizer st2 = new StringTokenizer(beforeBrackets);
        while (st2.hasMoreTokens()) {
            sig.add(st2.nextToken(" "));
        }
        String accessModifier = null;
        String returnType = null;
        String methodName = null;
        switch (sig.size()) {
            case 2:
                returnType = sig.get(0);
                methodName = sig.get(1);
                break;
            case 3:
                accessModifier = sig.get(0);
                returnType = sig.get(1);
                methodName = sig.get(2);
                break;
        }
        List<MethodSignature.Argument> args = new ArrayList<>();
        StringTokenizer st3 = new StringTokenizer(afterBrackets, ",");
        while (st3.hasMoreTokens()) {
            StringTokenizer st = new StringTokenizer(st3.nextToken(), " ");
            String type = st.nextToken();
            String name = st.nextToken();
            args.add(new MethodSignature.Argument(type, name));
        }
        MethodSignature ms = new MethodSignature(methodName, args);
        ms.setReturnType(returnType);
        ms.setAccessModifier(accessModifier);
        return ms;
    }
}
