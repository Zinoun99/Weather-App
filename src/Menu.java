import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

import static java.sql.Date.valueOf;

public class Menu {
    private static final String URL = "jdbc:mysql://localhost:3300/Weatherappli";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1234";


    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
    public void ListChoix() throws SQLException {
        int Choix;
        City city = new City();
        CityHistory cityHistory = new CityHistory();

        do{
            System.out.println("\t\t\t||========================================================||");
            System.out.println("\t\t\t||                       CITY MENU                        ||");
            System.out.println("\t\t\t||========================================================||");
            System.out.println("\t\t\t||              1: Add City                               ||");
            System.out.println("\t\t\t||              2: Display city                           ||");
            System.out.println("\t\t\t||              3: Update City                            ||");
            System.out.println("\t\t\t||              4: Delete City                            ||");
            System.out.println("\t\t\t||========================================================||");
            System.out.println("\t\t\t||                      CITY HISTORY MENU                 ||");
            System.out.println("\t\t\t||========================================================||");
            System.out.println("\t\t\t||              5: Add the historical Data of the city    ||");
            System.out.println("\t\t\t||              6: Display  historical Data of all cities ||");
            System.out.println("\t\t\t||              7: Update the historical Data of the city ||");
            System.out.println("\t\t\t||              8: Delete the historical Data of the city ||");
            System.out.println("\t\t\t||              9: Search for a city                      ||");
            System.out.println("\t\t\t||             10: Search for the city history            ||");
            System.out.println("\t\t\t||              0: Exit                                   ||");
            System.out.println("\t\t\t||========================================================||");
            Choix=new Scanner(System.in).nextInt();
            switch (Choix){
                case 1:
                    System.out.println("Enter the city ID  :");
                    city.setCityId(new Scanner(System.in).nextInt());
                    System.out.println("Enter the city name  :");
                    city.setCityName(new Scanner(System.in).nextLine());
                    System.out.println("Enter the current temperature: ");
                    city.setCurrentTemperature(new Scanner(System.in).nextInt());
                    System.out.println("Enter the current humidity : ");
                    city.setCurrentHumidity(new Scanner(System.in).nextInt());
                    System.out.println("Enter the current wind speed : ");
                    city.setCurrentWindSpeed(new Scanner(System.in).nextInt());
                    City.addCity(city);

                    break;
                case 2:
                    for(City city1 : City.displayCity()){
                        System.out.println("                                                 ");
                        System.out.println("City ID : "+city1.getCityId());
                        System.out.println("City Name : "+city1.getCityName());
                        System.out.println("Current Temperature : "+city1.getCurrentTemperature());
                        System.out.println("Current humidity : "+city1.getCurrentHumidity());
                        System.out.println("Current wind speed : "+city1.getCurrentWindSpeed());
                        System.out.println("____________________________________________________");

                    }

                    break;
                case 3:
                    System.out.println("Enter the cityID");
                    city.setCityId(new Scanner(System.in).nextInt());
                    System.out.println("Enter the cityName :");
                    city.setCityName(new Scanner(System.in).nextLine());
                    System.out.println("Enter the current temperature: ");
                    city.setCurrentTemperature(new Scanner(System.in).nextInt());
                    System.out.println("Enter the current humidity: ");
                    city.setCurrentHumidity(new Scanner(System.in).nextInt());
                    System.out.println("Enter the current wind speed : ");
                    city.setCurrentWindSpeed(new Scanner(System.in).nextInt());
                    City.updateCity(city);
                    break;
                case 4:
                    System.out.println("Enter the cityID to delete : ");
                    city.setCityId(new Scanner(System.in).nextInt());
                    City.deleteCity(city);
                    break;
                case 5:
                    System.out.println("Enter the historical cityId : ");
                    cityHistory.setHistoricalDataId(new Scanner(System.in).nextInt());
                    System.out.println("Enter the cityId : ");
                    cityHistory.setCityId(new Scanner(System.in).nextInt());
                    System.out.println("Enter the date (DD-MM-YYYY) : ");
                    String inputDate = new Scanner(System.in).next();
                    cityHistory.setEventDate(valueOf(LocalDate.parse(inputDate)).toLocalDate());
                    System.out.println("Temperature history : ");
                    cityHistory.setTemperature(new Scanner(System.in).nextInt());
                    CityHistory.addCityHistory(cityHistory);
                    break;
                case 6:
                    System.out.println("_________________City History_____________________ ");
                    for(CityHistory cityhist:CityHistory.displayCityHistory()){
                        System.out.println("                                                 ");
                        System.out.println("Historical ID : "+cityhist.getHistoricalDataId());
                        System.out.println("City ID : "+cityhist.getCityId());
                        System.out.println("Event date "+cityhist.getEventDate());
                        System.out.println("Temperature : "+cityhist.getTemperature());
                    }
                    break;
                case 7:
                    System.out.println("Enter the Id of city history that you want to update : ");
                    cityHistory.setCityId(new Scanner(System.in).nextInt());
                    System.out.println("Enter the city ID : ");
                    cityHistory.setCityId(new Scanner(System.in).nextInt());
                    System.out.println("Enter the date (DD-MM-YYYY) : ");
                    String inpuT = new Scanner(System.in).next();
                    cityHistory.setEventDate(LocalDate.parse(inpuT));
                    System.out.println("Enter the temperature : ");
                    cityHistory.setTemperature(new Scanner(System.in).nextInt());
                    CityHistory.updateCityHistory(cityHistory);
                    break;
                case 8:
                    System.out.println("Enter the id of city history to delete: ");
                    cityHistory.setHistoricalDataId(new Scanner(System.in).nextInt());
                    CityHistory.deleteCityHistory(cityHistory);
                    break;
                case 9:
                    System.out.println("Enter the city name : ");
                    String name = new Scanner(System.in).nextLine();
                    City.SearchCity(name);
                    break;
                case 10:
                    System.out.println("Enter the city ID : ");
                    int Id = new Scanner(System.in).nextInt();
                    CityHistory.SearchCityHistory(Id);
                    break;
                default:
                    System.out.println("invalid choice!");

            }
        }while(Choix!=0);
    }
}