package net.ttk1.a1util;

import java.util.regex.Pattern;

public class A1Util {
    private static Pattern p = Pattern.compile("^[A-Z]+$");
    public static String increment(String a1) {
        StringBuilder ret = new StringBuilder();
        boolean carry = true;
        for (int i = a1.length() -1; i >= 0; i--) {
            if (carry) {
                if (a1.charAt(i) == 'Z') {
                    ret.insert(0, 'A');
                } else {
                    ret.insert(0, nextChar(a1.charAt(i)));
                    carry = false;
                }
            } else {
                ret.insert(0, a1.charAt(i));
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

    public static String toA1(int r1c1) throws Exception {
        if (r1c1 <= 0) {
            throw  new Exception();
        }

        StringBuilder ret = new StringBuilder();
        while (r1c1 > 0) {
            int rem = r1c1 % 26;
            if (rem == 0) {
                ret.insert(0, 'Z');
                r1c1 -= 26;
            } else {
                ret.insert(0, (char) ('A' + rem - 1));
                r1c1 -= rem;
            }
            r1c1 /= 26;
        }
        return ret.toString();
    }

    public static int fromA1(String a1) throws Exception {
        if (!p.matcher(a1).find()) {
            throw new Exception();
        }

        int ret = 0;
        for (int i = 0; i < a1.length(); i++) {
            ret += (a1.charAt(a1.length() - 1 - i) - 'A' + 1) * Math.pow(26, i);
        }
        return ret;
    }

    // 動作確認
    public static void main(String[] args) throws Exception {
        String a1 = "A";
        for (int i = 1; i < 10000; i++) {
            if (!a1.equals(toA1(i))) {
                throw new Exception();
            }
            if (i != fromA1(a1)) {
                throw new Exception();
            }
            a1 = increment(a1);
        }
    }
}
