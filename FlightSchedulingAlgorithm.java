import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FlightSchedulingAlgorithm {
    private final static String[] listAirport = {"San bay 1", "San bay 2", "San bay 3", "San bay 4"};
    private final static int[] listAirplane = {4, 3, 4, 2};

    private final static int[] listRunways = {4, 3, 4, 3};

    private final static String dateTimePattern = "yyyy-MM-dd HH:mm";


    public static String calculateLastDestination(int totalAirports, String lastDes, String currentAirport) {
        int posInListAirport = 0;

        // not have last destination
        if (lastDes.isEmpty()) {
            if (listAirport[0].equals(currentAirport)) {
                return listAirport[1];
            } else {
                return listAirport[0];
            }
        }

        // find index in array
        for (int i = 0; i < listAirport.length; i++) {
            if (listAirport[i].equals(lastDes)) {
                posInListAirport = i;
            }
        }

        // if it over array
        if (posInListAirport + 1 > totalAirports - 1) {
            if (listAirport[0].equals(currentAirport)) {
                return listAirport[1];
            } else {
                return listAirport[0];
            }
        }

        if (listAirport[posInListAirport + 1].equals(currentAirport)) {
            if (posInListAirport + 2 > totalAirports - 1) {
                if (listAirport[0].equals(currentAirport)) {
                    return listAirport[1];
                } else {
                    return listAirport[0];
                }
            } else {
                posInListAirport++;
            }
        }

        return listAirport[posInListAirport + 1];
    }

    public static LocalDateTime calculateDate(LocalDateTime currenDate, float ETime) {
        // convert addition hours to minutes
        long minutesToAdd = (long) (ETime * 60);

        LocalDateTime resultDateTime = currenDate.plus(minutesToAdd, ChronoUnit.MINUTES);
        return resultDateTime;
    }

    public static float getETime(List<FlightTime> flightTimes, String dp, String des) {
        List<FlightTime> ls = flightTimes.stream().filter(i ->
                i.getFrom().equals(dp) && i.getTo().equals(des)
                        || i.getFrom().equals(des) && i.getTo().equals(dp)
        ).collect(Collectors.toList());
        float result = ls.get(0).getEstimatedTime();
        return result;
    }

    public static LocalDateTime transperStrToLocalDateTime(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateTimePattern);
        return LocalDateTime.parse(date, formatter);
    }

    public static List<AirportSate> initAirportState() {
        List<AirportSate> airportSateList = new ArrayList<>();
        for (int i = 0; i < listAirport.length; i++) {
            List<AirplaneInfo> airplaneInfos = new ArrayList<>();
            List<RunwayInfo> runwayInfos = new ArrayList<>();
            for (int j = 0; j < listAirplane[i]; j++) {
                AirplaneInfo airplaneInfo =
                        new AirplaneInfo("Airbus" + (i + 1) + (j + 1), transperStrToLocalDateTime("2023-12-03 00:00"));
                airplaneInfos.add(airplaneInfo);
            }

            for (int k = 0; k < listRunways[i]; k++) {
                RunwayInfo runwayInfo = new RunwayInfo("Runway" + (i + 1) + (k + 1), transperStrToLocalDateTime("2023-12-03 00:00"));
                runwayInfos.add(runwayInfo);
            }

            AirportSate airportSate = new AirportSate();
            airportSate.setEachAirplaneInfo(airplaneInfos);
            airportSate.setEachRunwayInfo(runwayInfos);
            airportSate.setName(listAirport[i]);
            airportSate.setLastDestination("");
            airportSate.setTotalAirplanes(listAirplane[i]);
            airportSate.setTotalRunway(listRunways[i]);
            airportSateList.add(airportSate);
        }
        return airportSateList;
    }

    public static boolean compareTwoDates(LocalDateTime d1, LocalDateTime d2) {
        int diff = d1.compareTo(d2);
        if (diff >= 0) return true;
        return false;
    }

    private static ResultDateWithIndex findTheSmallestTime(List<RunwayInfo> runwayInfos, LocalDateTime givenDate) {
        return IntStream.range(0, runwayInfos.size())
                .boxed()
                .filter(index -> runwayInfos.get(index).getAvailableTime().isBefore(givenDate) || runwayInfos.get(index).getAvailableTime().isEqual(givenDate))
                .min((index1, index2) -> runwayInfos.get(index1).getAvailableTime().compareTo(runwayInfos.get(index2).getAvailableTime()))
                .map(index -> new ResultDateWithIndex(runwayInfos.get(index).getAvailableTime(), index))
                .orElseGet(() -> {
                    int minIndex = IntStream.range(0, runwayInfos.size())
                            .boxed()
                            .min((index1, index2) -> runwayInfos.get(index1).getAvailableTime().compareTo(runwayInfos.get(index2).getAvailableTime()))
                            .orElse(-1);
                    return new ResultDateWithIndex(runwayInfos.get(minIndex).getAvailableTime(), minIndex);
                });
    }

    private static float calculateDifferenceInHours(LocalDateTime dateTime1, LocalDateTime dateTime2) {
        Duration duration = Duration.between(dateTime1, dateTime2);
        long seconds = duration.getSeconds();
        float hours = seconds / 3600.0f;

        return Math.abs(hours); // Trả về giá trị tuyệt đối
    }

    public static void myAlgorithm(List<FlightTime> flightTimes) {
        List<AirportSate> initState = initAirportState();
        List<FlightSchedule> scheduleList = new ArrayList<>();
        int i = 0;
        int breakState = 0;
        int counter = 0;
        while (breakState == 0) {
            System.out.println(counter++);
            for (i = 0; i < 4; i++) {
                String lastDestination = "";
                for (int j = 0; j < initState.get(i).getTotalAirplanes(); j++) {
                    // find the next destination
                    lastDestination = calculateLastDestination(4, lastDestination, listAirport[i]);

                    // get estimate time to fly
                    float ETime = getETime(flightTimes, listAirport[i], lastDestination);

                    System.out.println("i: " + i + " , j: " + j);
                    // cal start time (+1h to prepare)
                    LocalDateTime readyTime = calculateDate(initState.get(i).getEachAirplaneInfo().get(j).getReadyTime(), 1F);

                    // find the most reasonable time to take off
                    ResultDateWithIndex smallestDpTime = findTheSmallestTime(initState.get(i).getEachRunwayInfo(), readyTime);

                    if(!compareTwoDates(readyTime, smallestDpTime.getDate())) {
                        readyTime = smallestDpTime.getDate();
                    }

                    // cal the estimate arrival time
                    LocalDateTime timeArrival = calculateDate(readyTime, ETime);

                    // get des index in airport list
                    int indexAirport = 0;
                    for (int k = 0; k < listAirport.length; k++) {
                        if (lastDestination.equals(listAirport[k])) {
                            indexAirport = k;
                            break;
                        }
                    }

                    // find the most reasonable time to landing
                    ResultDateWithIndex smallestDesTime = findTheSmallestTime(initState.get(indexAirport).getEachRunwayInfo(), timeArrival);

                    if(!compareTwoDates(timeArrival, smallestDesTime.getDate())) {
                        float additionalHours = calculateDifferenceInHours(timeArrival, smallestDesTime.getDate());
                        readyTime = calculateDate(readyTime, additionalHours);
                        timeArrival = smallestDesTime.getDate();
                    }

                    initState.get(i).getEachRunwayInfo().get(smallestDpTime.getIndex()).setAvailableTime(calculateDate(readyTime, 1F));
                    initState.get(indexAirport).getEachRunwayInfo().get(smallestDesTime.getIndex()).setAvailableTime(calculateDate(timeArrival, 1F));

                    initState.get(indexAirport)
                            .getEachAirplaneInfo()
                            .add(new AirplaneInfo(initState.get(i).getEachAirplaneInfo().get(j).getAirplaneName(), timeArrival));
                    initState.get(indexAirport)
                            .setTotalAirplanes(initState.get(indexAirport).getTotalAirplanes() + 1);

                    FlightSchedule schedule = new FlightSchedule();
                    schedule.setFrom(listAirport[i]);
                    schedule.setTo(lastDestination);
                    schedule.setFlightName(initState.get(i).getEachAirplaneInfo().get(j).getAirplaneName());
                    schedule.setArrivalTime(timeArrival);
                    schedule.setTakeOffTime(readyTime);
                    scheduleList.add(schedule);
                    if (compareTwoDates(timeArrival, transperStrToLocalDateTime("2023-12-05 00:00"))) {
                        breakState = 1;
                    }
                }
                initState.get(i).setTotalAirplanes(0);
                initState.get(i).getEachAirplaneInfo().clear();
                if (breakState == 1) break;
            }
        }

        for (int z = 0; z < 4; z++) {
            System.out.println(initState.get(z).toString());
        }

        for (FlightSchedule sch : scheduleList) {
            System.out.println(sch.toString());
        }
    }

    public static void main(String[] agrs) {
        int airplanes = 4;
        int airports = 4;
        List<FlightTime> flightTimes = new ArrayList<>();
        flightTimes.add(new FlightTime("San bay 1", "San bay 2", 4.5F));
        flightTimes.add(new FlightTime("San bay 1", "San bay 3", 7F));
        flightTimes.add(new FlightTime("San bay 1", "San bay 4", 5F));
        flightTimes.add(new FlightTime("San bay 2", "San bay 3", 2.25F));
        flightTimes.add(new FlightTime("San bay 2", "San bay 4", 6F));
        flightTimes.add(new FlightTime("San bay 3", "San bay 4", 3F));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateTimePattern);
        LocalDateTime today = LocalDateTime.parse("2020-12-10 00:00", formatter);
        LocalDateTime tempDate1 = LocalDateTime.parse("2020-12-12 00:00", formatter);
        LocalDateTime result = calculateDate(today, 24F);
        System.out.println(formatter.format(result));
        System.out.println(getETime(flightTimes, "San bay 4", "San bay 2"));

//        LocalDateTime now = LocalDateTime.now();
//        LocalDateTime pastDate = LocalDateTime.parse("2017-01-14T15:32:56.000");
//
//        boolean isBefore = now.isAfter(pastDate);	//false
//        System.out.println(isBefore);
        myAlgorithm(flightTimes);
    }
}
