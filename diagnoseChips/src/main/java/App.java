import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 *    A      B
 *    Good   Good  both are good or bad
 *    Good   Bad   at least one of them is bad
 *    Bad    Good  at lease one of them is bad
 *    Bad    Bad   at lease one of them is bad
 *
 *    Condition: Group of chips , more than half of them are good.
 *    Then:      find all the good and bad chips
 *
 */
public class App {


    enum ChipStatus {

        GOOD,
        BAD

    }

    public static void main(String[] args) {

        System.out.println("Start");
        List<List<String>> testCases  = Arrays.asList(

                generateChipsList(3),
                generateChipsList(10)
                //,
                //generateChipsList(1000)
        );

        for(List<String> testCase : testCases) {

            diagnoseChips(testCase);
        }


    }

    public static void diagnoseChips(List<String> chips) {

        for(String chip : chips) {

            System.out.print(chip + " ");
        }
        System.out.println();
        System.out.println("Diagnose result is as follows: ");

        List<Integer> indexes  =  new ArrayList<>();
        for(int i =0 ;i<chips.size();i++) {

            indexes.add(i);
        }
        int singleGoodChipIndex = findSingleGoodChip(indexes, chips);

        for(String chip: chips) {
            String goodChip = chips.get(singleGoodChipIndex);
            List<String> report = generateChipsStatusReport(goodChip, chip);
            System.out.print(report.get(1) + " ");
        }
        System.out.println();

    }

    public static int findSingleGoodChip(List<Integer> indexes , List<String> chips) {

        if(indexes.size() == 1) {

            return indexes.get(0);
        }
        List<Integer> newIndex = new ArrayList<>();
        for(int i=0;i<indexes.size();i=i+2) {

            if(i == indexes.size()-1) {

                newIndex.add(indexes.get(i));
                break;
            }
                List<String> report = generateChipsStatusReport(chips.get(indexes.get(i)),
                        chips.get(indexes.get(i+1)));

                if(report.get(0).equals(ChipStatus.GOOD.name())
                        && report.get(1).equals(ChipStatus.GOOD.name()))
                {
                    newIndex.add(i);

                }

        }

        return findSingleGoodChip(newIndex , chips);

    }

    /**
     *
     * @param chipA
     * @param chipB
     */
    private static List<String> generateChipsStatusReport(String chipA, String chipB) {

        if(chipA.equals(ChipStatus.GOOD.name()) && chipB.equals(ChipStatus.GOOD.name())) {

            return Arrays.asList("GOOD" , "GOOD");
        }
        else if(chipA.equals(ChipStatus.BAD.name()) && chipB.equals(ChipStatus.BAD.name())) {

            return Arrays.asList("GOOD","GOOD");
        }
        else if(chipA.equals(ChipStatus.GOOD) && chipB.equals((ChipStatus.BAD.name()))) {

            return Arrays.asList("GOOD" ,"BAD");
        }
        else {

            return Arrays.asList("BAD","BAD");
        }

    }

    private static List<String> generateChipsList(int numberOfChips) {

        List<String> result = new ArrayList<>();
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int maxBad =  numberOfChips %2 == 0? numberOfChips/2 - 1 : numberOfChips/2;
        int numBad = 0;

        for(int i=0; i< numberOfChips ; i++) {

            boolean isBad = random.nextBoolean();
            if(isBad && numBad < maxBad) {

                result.add(ChipStatus.BAD.name());
                numBad = numBad + 1;
            }
            else {
                result.add(ChipStatus.GOOD.name());
            }
        }

        return result;

    }
}
