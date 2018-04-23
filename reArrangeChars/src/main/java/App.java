import java.util.Arrays;
import java.util.List;

/**
 *  rearrange characters of the string to make the string does not
 *  contain same adjacent character
 */
public class App
{

    public static void main(String[] args) {


        List<String>  testCases = Arrays.asList(

                "geeksforgeeks",
                "bbbabaaacd",
                "bbbb"
        );
        for(String testCase: testCases) {

            System.out.println( testCase + ": " + rearrangeString(testCase));
        }

    }

    public static boolean rearrangeString(final String input) {

        int[] freqs = new int[256];
        for(char c :input.toCharArray()) {

           freqs[c] = freqs[c] + 1;

        }
        Arrays.sort(freqs);
        for(int i =freqs.length-1 ;i>-0 ; i--) {

            if(freqs[i]<=1) {

                continue;
            }
            for(int j=i-1; j>=0; j--) {

                if(freqs[j]<=freqs[i]-1) {
                    freqs[i] = freqs[i] - freqs[j];
                    freqs[j] = 1;
                }
                else {

                    freqs[i] = 1;
                    freqs[j] = freqs[j] - freqs [i];
                }


            }
            if(freqs[i]>1) {
                System.out.println((char)i + "[" + freqs[i] +" ]");
                return false;
            }

        }
        return true;





    }
}
