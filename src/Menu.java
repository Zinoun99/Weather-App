import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

import static java.sql.Date.valueOf;

public class Menu {
    private static final String URL = "jdbc:mysql://localhost:3300/weatherApplication";
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
            System.out.println("\t\t\t||              2: Update City                            ||");
            System.out.println("\t\t\t||              3: Delete City                            ||");
            System.out.println("\t\t\t||              4: Display All Cities                     ||");
            System.out.println("\t\t\t||========================================================||");
            System.out.println("\t\t\t||                      CITY HISTORY MENU                 ||");
            System.out.println("\t\t\t||========================================================||");
            System.out.println("\t\t\t||              5: Add the historical Data of the city    ||");
            System.out.println("\t\t\t||              6: Update the historical Data of the city ||");
            System.out.println("\t\t\t||              7: Delete the historical Data of the city ||");
            System.out.println("\t\t\t||              8: Display  historical Data of all cities ||");
            System.out.println("\t\t\t||              0: Exit                                   ||");
            System.out.println("\t\t\t||========================================================||");
            Choix=new Scanner(System.in).nextInt();
            switch (Choix){
                case 1:
                    System.out.println("Entrez le nom de la ville :");
                    city.setCityName(new Scanner(System.in).nextLine());
                    System.out.println("Entrez la température actuelle");
                    city.setCurrentTemperature(new Scanner(System.in).nextInt());
                    System.out.println("Entrez le taux d'humidité actuelle");
                    city.setCurrentHumidity(new Scanner(System.in).nextInt());
                    System.out.println("Entrez la vitesse du vent actuelle");
                    city.setCurrentWindSpeed(new Scanner(System.in).nextInt());
                    City.addCity(city);

                    break;
                case 2:
                    System.out.println("_________________Les villes_____________________ ");
                    for(City city1 : City.displayCity()){
                        System.out.println("                                                 ");
                        System.out.println("id Ville : "+city.getCityId());
                        System.out.println("Nom ville : "+city.getCityName());
                        System.out.println("Température actuelle : "+city.getCurrentTemperature());
                        System.out.println("Taux d'humidité actuelle : "+city.getCurrentHumidity());
                        System.out.println("Vitesse du vent actuelle : "+city.getCurrentWindSpeed());
                        System.out.println("____________________________________________________");

                    }

                    break;
                case 3:
                    System.out.println("id");
                    city.setCityId(new Scanner(System.in).nextInt());
                    System.out.println("Entrez le nouveau nom de la ville :");
                    city.setCityName(new Scanner(System.in).nextLine());
                    System.out.println("Entrez la nouvelle température actuelle");
                    city.setCurrentTemperature(new Scanner(System.in).nextInt());
                    System.out.println("Entrez le nouveau taux d'humidité actuelle");
                    city.setCurrentHumidity(new Scanner(System.in).nextInt());
                    System.out.println("Entrez la nouvelle vitesse du vent actuelle");
                    city.setCurrentWindSpeed(new Scanner(System.in).nextInt());
                    City.updateCity(city);
                    break;
                case 4:
                    System.out.println("Entrez id du ville que vous voullez supprimer");
                    city.setCityId(new Scanner(System.in).nextInt());
                    City.deleteCity(city);
                    break;
                case 5:
                    System.out.println("Entrez id du ville que vous voullez ajouter");
                    cityHistory.setCityId(new Scanner(System.in).nextInt());
                    System.out.println("Entrez la date de l'événement météorologique historique (AAAA-MM-JJ)");
                    String inputDate = new Scanner(System.in).next();
                    cityHistory.setEventDate(valueOf(LocalDate.parse(inputDate)).toLocalDate());
                    System.out.println("Entrez la Température historique");
                    cityHistory.setTemperature(new Scanner(System.in).nextInt());
                    CityHistory.addCityHistory(cityHistory);
                    break;
                case 6:
                    System.out.println("_________________Historique des villes_____________________ ");
                    for(CityHistory hCity:CityHistory.displayCityHistory()){
                        System.out.println("                                                 ");
                        System.out.println("Id Historique : "+hCity.getHistoricalDataId());
                        System.out.println("Id Ville : "+hCity.getCityId());
                        System.out.println("La date de l'événement météorologique historique : "+hCity.getEventDate());
                        System.out.println("La Température historique : "+hCity.getTemperature());
                    }
                    break;
                case 7:
                    System.out.println("Entrez id du ville que vous voullez ajouter");
                    cityHistory.setCityId(new Scanner(System.in).nextInt());
                    System.out.println("Entrez la date de l'événement météorologique historique (AAAA-MM-JJ)");
                    String inpuT = new Scanner(System.in).next();
                    cityHistory.setEventDate(LocalDate.parse(inpuT));
                    System.out.println("Entrez la Température historique");
                    cityHistory.setTemperature(new Scanner(System.in).nextInt());
                    CityHistory.updateCityHistory(cityHistory);
                    break;
                case 8:
                    System.out.println("Entrez id du historique ville que vous voullez supprimer");
                    cityHistory.setHistoricalDataId(new Scanner(System.in).nextInt());
                    CityHistory.deleteCityHistory(cityHistory);
                    break;


                default:
                    System.out.println("Choix invalide ");

            }
        }while(Choix!=0);
    }
}