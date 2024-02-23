import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
    private static final String URL = "jdbc:mysql://localhost:3300/weather_app";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1234";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
    int choice, id;
    String name, city;
        do {
        System.out.println("\t\t\t||======================================================================||");
        System.out.println("\t\t\t||------------|              Weather Application            |-----------||");
        System.out.println("\t\t\t||======================================================================||");
        System.out.println("\t\t\t||------------|   1: Add City                            |-----------||");
        System.out.println("\t\t\t||------------|   2: Update City                         |-----------||");
        System.out.println("\t\t\t||------------|   3: Delete City                         |-----------||");
        System.out.println("\t\t\t||------------|   4: Display All City                    |-----------||");
        System.out.println("\t\t\t||------------|   5: Quitter application                    |-----------||");
        System.out.println("\t\t\t||======================================================================||");
        System.out.println("Enter votre choix: ");
        Scanner scanner = new Scanner(System.in);
        choice = new Scanner(System.in).nextInt();
        switch (choice) {
            case 1:
                System.out.print("CityId : ");
                int cityId = new Scanner(System.in).nextInt();
                System.out.print("CityId : ");
                String CityName = new Scanner(System.in).nextLine();
                System.out.print("CityName : ");
                int currentTemperature = new Scanner(System.in).nextInt();
                System.out.println("Current Temperature: ");
                int currentHumidity = new Scanner(System.in).nextInt();
                System.out.println("current Humidity: ");
                int currentWindSpeed = new Scanner(System.in).nextInt();
                System.out.println("current Wind Speed: ");
                City.addCity(new City(cityId, CityName, currentTemperature, currentHumidity, currentWindSpeed));
                break;
            case 2:
                System.out.print("Enter student ID to update: ");
                id = new Scanner(System.in).nextInt();
                System.out.print("Enter new name: ");
                name = new Scanner(System.in).nextLine();
                System.out.print("Enter new city: ");
                city = new Scanner(System.in).nextLine();
                DatabaseManager.updateStudent(new Student(id, name, city));
                break;
            case 3:
                System.out.print("Enter student ID to delete: ");
                id = new Scanner(System.in).nextInt();
                DatabaseManager.deleteStudent(id);
                break;
            case 4:
                System.out.println("All students:");
                for (Student std : DatabaseManager.getAllStudents()) {
                    System.out.println(std);
                }
                break;
        }
    }while (choice != 5) ;
}
}
