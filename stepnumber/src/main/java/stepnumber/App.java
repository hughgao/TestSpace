package stepnumber;

import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class App {

    public static void main(String[] args) {

        List<int[]> testCases = Arrays.asList(

                new int[] {0,21},
                new int[] {10,15}
        );

        for(int[] testCase: testCases) {

            int result = findStepNumbers(testCase);

            System.out.println(result);
        }
    }

    /**
     *      0<=M N <=10^6
     * @param input
     * @return
     */
    public static int findStepNumbers(int[] range) {


        int result = 0;

        for(int i=0;i<10;i++) {

            if(i>=range[0] && i<=range[1]) {

                result = result + 1;
                System.out.print(i + ":");

            }

            if( i<=range[1] && i>0) {

                result = result + findChildResults(i*10, i-1, range);
                result = result + findChildResults(i*10, i+1, range);


            }


        }

        return result;



    }

    public static int findChildResults(int total, int root, int[] range) {

          int result = 0;
          if(root<0 || root >9 ) {

               return 0;
           }
           total = total + root;

           if(total>=range[0] && total<=range[1]) {
               System.out.print(total + " : ");
               result = 1;
           }
           if(total<=range[1]) {

               result = result +  findChildResults(total*10, root-1, range);
               result = result +  findChildResults(total*10, root+1, range);
           }

           return result;
    }



}
