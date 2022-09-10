package algorithms;

import java.util.Arrays;
import java.util.Comparator;
import java.util.StringJoiner;
import java.util.function.Function;

public class AlgoTest {
    public static void main(String[] args) {
        var people = new Person[]{
                new Person(11, 10, 12),
                new Person(11, 10, 8),
                new Person(12, 10, 19),
                new Person(10, 10, 7),
                new Person(13, 10, 14),

                new Person(11, 20, 12),
                new Person(10, 20, 18),
                new Person(12, 20, 25),
                new Person(12, 20, 9),

                new Person(11, 30, 19),
                new Person(10, 30, 7),
                new Person(12, 30, 25),
                new Person(12, 30, 12)
        };

        Function<Person[], String> peopleToString = arr -> Arrays.stream(arr).reduce(new StringJoiner(",\n", "[\n", "\n]"), (x, y) -> x.add(y.toString()), (x, y) -> x).toString();

        System.out.println("Before sort: " + peopleToString.apply(people));

        Person[] sortedByAge = QuickSort.sort(people, Comparator.comparingInt(Person::getAge));
        System.out.println("\nSorted by age: " + peopleToString.apply(sortedByAge));

        Person[] sortedByWeight = QuickSort.sort(people, Comparator.comparingInt(Person::getWeight));
        System.out.println("\nSorted by weight: " + peopleToString.apply(sortedByWeight));

        Person[] sortedByHeight = QuickSort.sort(people, Comparator.comparingInt(Person::getHeight));
        System.out.println("\nSorted by height: " + peopleToString.apply(sortedByHeight));

        int peopleWithSameWeightAndDifferentHeight = countPeopleWithSameWeightAndDifferentHeight(people);
        System.out.println("\nAmount of people with the same weight but different height is " + peopleWithSameWeightAndDifferentHeight);
    }

    public static int countPeopleWithSameWeightAndDifferentHeight(Person[] people){ // Worst case: O(n^2)
        Person[] sortedByWeight = QuickSort.sort(people, Comparator.comparingInt(Person::getWeight));
        int low = 0;
        int high = 0;
        int lastWeight = sortedByWeight[0].getWeight();
        int countPeople = 0;
        while (high < sortedByWeight.length) {
            if (high == sortedByWeight.length - 1 || sortedByWeight[high].getWeight() != lastWeight) {
                Person[] sortedByHeight;
                if (high == sortedByWeight.length - 1) {
                    sortedByHeight = QuickSort.sort(Arrays.copyOfRange(people, low, high + 1), Comparator.comparingInt(Person::getHeight));
                } else
                    sortedByHeight = QuickSort.sort(Arrays.copyOfRange(people, low, high), Comparator.comparingInt(Person::getHeight));

                countPeople += Arrays.stream(sortedByHeight).map(Person::getHeight).distinct().count();
                low = high;
                lastWeight = sortedByWeight[low].getWeight();
            }
            high++;
        }
        return countPeople;
    }

}
