import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
import java.util.Scanner;

public class Race {
    ArrayList<Vehicle> members = new ArrayList<>();
    Scanner scanner;
    int maxMember;
    int timeRace;
    int minLenVehicleTitle = 3;
    int maxLenVehicleTitle = 10;
    int minLenVehicleSpeed = 0;
    int maxLenVehicleSpeed = 250;

    public Race(int maxMember, int timeRace){
        this.maxMember = maxMember;
        this.timeRace = timeRace;

        System.out.println("Необходимо добавить участников для гонки.\n");
        scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Результат..");
        System.out.println("..");
        System.out.println("....");

        for (Vehicle vh: members)
        {
            int range = vh.getSpeed() * timeRace;
            vh.setRange(range);

            System.out.printf("%s№%d - Автомобиль \"%s\"%s , прошел: %d км\n",
                    Utils.GREEN, vh.getNumber(), vh.getTitle(),Utils.RESET, vh.getRange());
        }
        System.out.println("-----------------------------------\n");

        Optional<Vehicle> c = members.stream().max(Comparator.comparing(Vehicle::getRange));
        if(c.isPresent()){
            Vehicle vh = c.get();
            System.out.printf("%s№%d%s - Самая быстрая машина %s\"%s\"%s, пройденое растояние %d км",
                    Utils.GREEN, vh.getNumber(), Utils.RESET,  Utils.GREEN, vh.getTitle(), Utils.RESET, vh.getRange());
        }
        else{
            System.out.print("Победителей нет!");
        }
    }

    public void initMembers(){
        String title = "";
        int speed = 0;
        while (members.size() < maxMember){

            int numMember = members.size() +1;

            if(title.isEmpty()){
                System.out.printf("- Введите название автомобиля №%s\n", numMember);
                String titleRaw = scanner.nextLine();

                if(titleRaw.length() < minLenVehicleTitle || titleRaw.length() > maxLenVehicleTitle){
                    System.out.printf("%sНеправильное название автомобиля, минимальная длина названия %d, а максимальная %d символов.%s\n",
                            Utils.RED, minLenVehicleTitle, maxLenVehicleTitle, Utils.RESET);
                    continue;
                }
                else{
                    title = titleRaw.substring(0,1).toUpperCase() + titleRaw.substring(1);
                }
            }

            System.out.printf("- Введите скорость автомобиля №%s\n", numMember);
            if (scanner.hasNextInt()){
                int speedRaw = scanner.nextInt();

                if(speedRaw <= minLenVehicleSpeed || speedRaw >= maxLenVehicleSpeed){
                    System.out.printf("%sСкорость неправильная, попробуйте еще раз.%s\n", Utils.RED, Utils.RESET);
                    continue;
                }
                else{
                    speed = speedRaw;
                    scanner.nextLine();
                }
            }
            else{
                System.out.printf("%sНеправильная скорость.%s\n", Utils.RED, Utils.RESET);
                scanner.nextLine();
                continue;
            }

            members.add(new Vehicle(numMember, title, speed));
            System.out.printf("%sАвтомобиль добавлен!%s\n\n",  Utils.GREEN, Utils.RESET);

            title = "";
            //speed = 0;
        }

        System.out.printf("Автомобилей в гонке - %d\n", members.size());

        members.sort(Comparator.comparing(Vehicle::getNumber));
        for (Vehicle vh: members)
        {
            System.out.printf("%s№%d Автомобиль \"%s\"%s | скорость: %d\n", Utils.GREEN, vh.getNumber(), vh.getTitle(),Utils.RESET, vh.getSpeed());
        }
        System.out.println("-----------------------------------\n");
    }
}
