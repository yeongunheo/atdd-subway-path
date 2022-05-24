package wooteco.subway.domain.fare;

public class FareCalculator {

    public FareCalculator() {
    }

    public int calculateFare(int distance, int extraFare, int age) {
        Integer fareByDistance = FareByDistance.calculateFareByDistance(distance);
        int total = fareByDistance + extraFare;
        return FareByAge.calculateFareByAge(age, total);
    }
}