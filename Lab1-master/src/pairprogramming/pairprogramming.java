package pairprogramming;

import java.util.Scanner;
import java.util.Stack;

/**
 * pairprogramming.
 */
public class pairprogramming {

    /**
     * @param args
     *            string
     */
    public static void main(final String[] args) {
        // TODO Auto-generated method stub
        pairprogramming ipt = new pairprogramming();
        Scanner temp = new Scanner(System.in);
        String test = temp.next();
        String test1 = test;
        String test2 = new String();
        System.out.println(test + "\n");
        for (int i = 0;; i++) {
            System.out.print("!simplify ");
            Scanner valuetemp = new Scanner(System.in);
            String value = valuetemp.next();
            Stack s1 = new Stack(), s2 = new Stack();
            String[] array = new String[test.length()];
            array = ipt.expression(test);
            test = ipt.simplify(array, test, value);
            test2 = test;
            System.out.print("!d/d");
            Scanner xtemp = new Scanner(System.in);
            char x = xtemp.nextLine().charAt(0);
            if (i == 0) {
                ipt.derivative(test1, x);
            } else {
                ipt.derivative(test2, x);
            }
        }
    }

    /**
     * expression.
     * 
     * 
     * @return string
     * @param test
     *            string
     */
    private  String[] expression(final String test) { // ǰ׺���ʽת��Ϊ��׺

        char[] testarray = test.toCharArray();
        String[] arraytemp = new String[testarray.length];
        int starter = 0, ender = 0;
        Stack s1 = new Stack(), s2 = new Stack();
        s1.empty();
        s2.empty(); // ����ջ������Ų������������
        for (int i = 0; i < testarray.length; i++) {
            if (ender == testarray.length - 1) { // �жϽ�����������Ԫ�ؽ�ջ

                if (testarray[ender] == ')') { // ��������ender-starter�ĳ��ȱ��������ּ���s2
                    char[] temp1 = new char[ender - starter];
                    System.arraycopy(testarray, starter, temp1, 0,
                            ender - starter);
                    String t = new String(temp1);
                    s2.push(t);
                } else { // ����ender-starter+1�ĳ��ȵı��������ּ���s2����Ϊ���ڲ�����֮ǰ�������ǲ�������һ����
                    char[] temp2 = new char[ender - starter + 1];
                    System.arraycopy(testarray, starter, temp2, 0,
                            ender - starter + 1);
                    String t = new String(temp2);
                    s2.push(t);
                }
            } else { // �Բ�������ջ�����������ȵ��ж�
                if (testarray[i] == '+' || testarray[i] == '-'
                        || testarray[i] == '*' || testarray[i] == '/'
                        || testarray[i] == '(' || testarray[i] == ')'
                        || testarray[i] == '^') {
                    if (i == 0) { // �����ʼ��������������˳�����
                        System.out.print("Error, no variable ");
                        System.exit(0);
                    }
                    if (testarray[i] == '(') {
                        starter += 1;
                        s1.push(testarray[i]);
                    } else {
                        ender = i;
                        if (ender > starter) {
                            char[] temp = new char[ender - starter];
                            System.arraycopy(testarray, starter, temp, 0,
                                    ender - starter);
                            String t = new String(temp);
                            s2.push(t);
                            starter = ender + 1;
                        }
                        if (s1.isEmpty()) {
                            s1.push(testarray[i]);
                        } else if (testarray[i] == ')') {
                            s2.push(s1.peek());
                            s1.pop();
                            if (!s1.isEmpty()) {
                                while ((char) s1.peek() != '(') {
                                    s2.push(s1.peek());
                                    s1.pop();
                                }
                                if ((char) s1.peek() != '(') {
                                    s1.pop();
                                }
                            }
                            starter = starter + 1;
                            /*
                             * ��������۲�������ջ����
                             */
                        } else if (testarray[i] == '*' || testarray[i] == '/') {
                            if ((char) s1.peek() == '+'
                                    || (char) s1.peek() == '-'
                                    || (char) s1.peek() == '(') {
                                s1.push(testarray[i]);
                            } else {
                                s2.push(s1.peek());
                                s1.pop();
                                if (!s1.isEmpty()) {
                                    while ((char) s1.peek() == '*'
                                            || (char) s1.peek() == '/'
                                            || (char) s1.peek() == '^') {
                                        s2.push(s1.peek());
                                        s1.pop();
                                    }
                                }
                                s1.push(testarray[i]);
                            }
                        } else if (testarray[i] == '^') {
                            s1.push(testarray[i]);

                        } else if (testarray[i] == '+' || testarray[i] == '-') {
                            if ((char) s1.peek() == '(') {
                                s1.push(testarray[i]);

                            } else {
                                s2.push(s1.peek());
                                s1.pop();
                                while (!s1.empty()) {
                                    s2.push(s1.peek());
                                    s1.pop();
                                }
                                s1.push(testarray[i]);
                            }

                        }
                    }
                }
            }
            ender = ender + 1;
        }

        while (!s1.isEmpty()) { // ���ʽ������ɺ�s1ʣ�µĲ�����ѹ��s2��
            if ((char) s1.peek() == '(') {
                s1.pop();
            } else {
                s2.push(s1.peek());
                s1.pop();
            }
        }

        for (int i = ender - 1; i >= 0; i--) {
            if (!s2.isEmpty()) {
                arraytemp[i] = s2.peek().toString();
                s2.pop(); // �����׺
            }
        }

        return arraytemp; // ���õ��ĺ�׺���ʽ�����ַ�������
    }

    /**
     * simplify.
     * 
     * 
     * @return string
     * 
     *
     * @param test
     *            string
     * 
     * @param arr
     *            string
     * 
     * @param val
     *            string
     */
    private  String simplify(final String[] arr, final String test,
            final String val) {
        int i = 0, k = 0, o = 0;
        int starter = 0, ender = 0;
        String data = new String();
        String word = new String();
        String[] d = new String[2];
        String[] result = new String[arr.length];
        Stack s3 = new Stack();
        char[] value = val.toCharArray();
        for (int n = 0; n < value.length; n++) {
            if (value[n] == '=') {
                starter = n + 1;
            }
        }
        ender = value.length;
        char[] temp = new char[ender - starter];
        String w = val.substring(0, 1);
        System.arraycopy(value, starter, temp, 0, ender - starter);
        String t = new String(temp);
        while (i < arr.length) {
            int h = 0;
            int q = 0;
            if (arr[i] == null || arr[i].equals("(")) { // ���������ý�������
                i++;
            } else if (arr[i].equals("+") || arr[i].equals("-")
                    || arr[i].equals("*") || arr[i].equals("/")
                    || arr[i].equals("^")) {
                // ����������ʱ�������������
                while (h <= 1) {
                    while (q < 2) {
                        int j = 0;
                        char[] isword = String.valueOf(s3.peek()).toCharArray();
                        do {
                            if (isword[j] <= 'z' && isword[j] >= 'a') {
                                /*
                                 * ���������������ĸ�����Һ�����ı���һ�� ������ı���ֵ����ԭ����
                                 */
                                if (w.equals(String.valueOf(s3.peek()))) {
                                    d[q] = t;
                                    s3.pop();
                                    // �����ֲ�������˵��ջ��Ԫ�ز��ܲ������
                                } else if (isword[j] == '+' || isword[j] == '-'
                                        || isword[j] == '*' || isword[j] == '/'
                                        || isword[j] == '^') {
                                    d[q] = String.valueOf(s3.peek());
                                    s3.pop();
                                    // ����ֱ�ӽ�ջ��Ԫ�ؽ�������
                                } else {
                                    word = String.valueOf(s3.peek());
                                    d[q] = String.valueOf(s3.peek());
                                    s3.pop();
                                    j = isword.length;
                                }
                                j++;
                                h++;
                                q++;
                            } else {
                                j++;
                                h++;
                            }
                        } while (j < isword.length);
                        // �����ж��Ƿ�ȡ��s3�����������������
                        if (q < 2) {
                            if (w.equals(String.valueOf(s3.peek()))) {
                                d[q] = t;
                            } else {
                                d[q] = String.valueOf(s3.peek());
                            }
                            s3.pop();
                            q++;
                            h++;
                        }
                    }
                }
                char[] is0 = d[0].toCharArray();
                char[] is1 = d[1].toCharArray();
                StringBuffer buffer = new StringBuffer();
                int isjump = 0;
                // �ж��Ƿ����޷�����������ַ���
                for (int g = 0; g < is0.length; g++) {
                    if (is0[g] <= 'z' && is0[g] >= 'a') {
                        isjump = 1;
                    }
                }
                for (int g = 0; g < is1.length; g++) {
                    if (is1[g] <= 'z' && is1[g] >= 'a') {
                        isjump = 1;
                    }
                }
                // �������ֱ�ӽ�s3�������Ԫ������������ջ
                if (isjump == 1) {
                    buffer.append(d[1] + arr[i] + d[0]);
                    data = buffer.toString();
                    // ���������Ӧ������
                } else {
                    switch (arr[i]) {
                    case "+":
                        data = String.valueOf(Integer.parseInt(d[1])
                                + Integer.parseInt(d[0]));
                        break;
                    case "-":
                        data = String.valueOf(Integer.parseInt(d[1])
                                - Integer.parseInt(d[0]));
                        break;
                    case "*":
                        data = String.valueOf(Integer.parseInt(d[1])
                                * Integer.parseInt(d[0]));
                        break;
                    case "/":
                        data = String.valueOf(Integer.parseInt(d[1])
                                / Integer.parseInt(d[0]));
                        break;
                    case "^":
                        data = String
                                .valueOf((int) (Math.pow(Integer.parseInt(d[1]),
                                        Integer.parseInt(d[0]))));
                        break;
                    default:
                        System.out.println("Error!");
                        break;
                    }
                }
                s3.push(data);
                i++;
            } else {
                s3.push(arr[i]);
                i++;
            }
        }
        while (!s3.isEmpty()) {
            result[arr.length - 1] = String.valueOf(s3.peek());
            s3.pop();
            k++;
        }
        String newtest = new String();
        do {
            if (result[o] != null) {
                newtest = newtest + result[o];
                System.out.println(result[o] + "\n");
            }
            o++;
        } while (o < arr.length);
        return newtest; // ���±��ʽ
    }

    /**
     * ddx.
     * 
     * 
     * @return string
     * @param x
     *            a
     * @param s1
     *            string
     */
    public final String ddx(final String s1, final char x) {
        String s = s1;
        int couter = 0, flag = 0;
        for (int i = 0; i < s.length(); i++) {
            char temp = s.charAt(i);
            if (temp == x) {
                couter++;
                flag = 1;
            }
        }
        if (flag == 1) {
            s = s.replaceFirst(String.valueOf(x), Integer.toString(couter));
        } else {
            s = "";
        }
        return s;
    }

    /**
     * derivative.
     * 
     * @param x
     *            a
     *
     * @param readerin
     *            string
     */
    public final void derivative(final String readerin, final char x) { // ��

        String reader = readerin;
        reader = reader.replaceAll("-", "~-");
        reader = reader.replaceAll("[+]", "~+");
        String[] oprator = reader.split("~");
        String output = "";
        for (String s : oprator) {
            s = ddx(s, x);
            if (s == null || s.length() <= 0) {
                continue;
            } else {
                output = output + s;
            }
        }

        if (output.charAt(0) == '+') {
            output = output.replaceFirst("[+]", "");
        }
        System.out.print(output + "\n");
    }
}
