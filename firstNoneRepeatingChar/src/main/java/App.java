import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * first None repeating char in a stream
 */
public class App {
    /**
     *
     *
     * @param args
     */
    public static void main(String[] args) {

        List<String> testCases = Arrays.asList("abcad" , "ddefghoabcdefga");

        for(String testCase : testCases) {

            String result = firstNoneRepeatChars(testCase);
            System.out.println(result);
        }

    }


    public static String firstNoneRepeatChars(final String input) {

        final char[] charStatus = new char[256];
        Queue<Integer> noneRepeatCharacterQueue = new ArrayDeque<>();

        StringBuilder result = input.subSequence(0, input.length()).chars().map(e -> {

            if (charStatus[e] == 0) {

                noneRepeatCharacterQueue.offer(e);
                charStatus[e] = 1;
            } else {
                charStatus[e] = 2;
            }
            int index = -1;
            while(!noneRepeatCharacterQueue.isEmpty()) {

                index = noneRepeatCharacterQueue.peek();
                if(charStatus[index] == 1) {

                    break;
                }
                else {
                    index = -1;
                    noneRepeatCharacterQueue.poll();
                }

            }
            return index;

        }).collect(()->new StringBuilder(),(s,e)-> {
            if(e != -1)
                s.append((char)e);
            else
                s.append("-1");

        }, null);


        return result.toString();
    }
}
