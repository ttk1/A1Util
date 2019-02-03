package net.ttk1.a1util;

import java.util.regex.Pattern;

public class A1Util {
    private static final Pattern a1ColNumPattern = Pattern.compile("^[A-Z]+$");

    public static String increment(String a1ColNum) {
        StringBuilder ret = new StringBuilder();
        boolean carry = true;
        for (int i = a1ColNum.length() -1; i >= 0; i--) {
            if (carry) {
                if (a1ColNum.charAt(i) == 'Z') {
                    ret.insert(0, 'A');
                } else {
                    ret.insert(0, nextChar(a1ColNum.charAt(i)));
                    carry = false;
                }
            } else {
                ret.insert(0, a1ColNum.charAt(i));
            }
        }
        if (carry) {
            ret.insert(0, 'A');
        }
        return ret.toString();
    }

    private static char nextChar(char a) {
        if (a == 'Z') {
            return 'A';
        } else {
            return ++a;
        }
    }

    public static String toA1ColNum(int r1c1ColNum) throws Exception {
        if (r1c1ColNum <= 0) {
            throw  new Exception();
        }

        StringBuilder ret = new StringBuilder();
        for (; r1c1ColNum > 0; r1c1ColNum = (r1c1ColNum - 1) / 26) {
            ret.append((char) ('A' + (r1c1ColNum - 1) % 26));
            ;
        }
        return ret.reverse().toString();
    }

    public static int fromA1ColNum(String a1ColNum) throws Exception {
        if (!a1ColNumPattern.matcher(a1ColNum).find()) {
            throw new Exception();
        }

        int ret = 0;
        for (int i = 0; i < a1ColNum.length(); i++) {
            ret = ret * 26 + (a1ColNum.charAt(i) - 'A' + 1);
        }
        return ret;
    }

    // 動作確認
    public static void main(String[] args) throws Exception {
        String a1 = "A";
        for (int i = 1; i < 10000; i++) {
            if (!a1.equals(toA1ColNum(i))) {
                throw new Exception();
            }
            if (i != fromA1ColNum(a1)) {
                throw new Exception();
            }
            a1 = increment(a1);
        }
    }
}
