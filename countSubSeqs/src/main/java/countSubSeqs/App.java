package countSubSeqs;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 *
 * count the number of subseq of a^ib^jC^k
 */
public class App {

    public static void main(String[] args) {

        List<String> testCases = Arrays.asList(

                "abbc",
                "abcabc"
        );
        for(String testCase: testCases) {

           int  result = countSubSeqs(testCase);
           System.out.println("result is: [" + result + "]");
        }

    }

    /**
     *
     * @param input
     * @return
     */
    public static int countSubSeqs(String input) {

        int result = 0;
        int endWithA = 0;
        int endWithB = 0;
        int endWithC = 0;
        for(int i=0;i<input.length();i++) {


            if(input.charAt(i)=='a') {
                endWithA = endWithA + 1;

            }

            if(input.charAt(i) == 'b') {

                if(endWithB>0) endWithB = endWithB +1;
                if(endWithA > 0) endWithB = endWithB + endWithA;
            }
            if(input.charAt(i) == 'c')  {
                if(endWithC > 0 ) endWithC = endWithC + 1;
                if(endWithB >0 ) endWithC = endWithC + endWithB;
            }

        }

        result = endWithC;

        return result;


    }
}
