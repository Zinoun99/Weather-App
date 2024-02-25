import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CityHistory {
    private int historicalDataId;
    private int cityId;
    private LocalDate eventDate;
    private int temperature;
    public CityHistory(){}
    public CityHistory(int historicalDataId, int cityId, LocalDate eventDate, int temperature){
        this.cityId=cityId;
        this.historicalDataId=historicalDataId;
        this.eventDate=eventDate;
        this.temperature=temperature;
    }
    public int getHistoricalDataId() {
        return historicalDataId;
    }

    public void setHistoricalDataId(int historicalDataId) {
        this.historicalDataId = historicalDataId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }
    public static List<CityHistory> displayCityHistory() throws SQLException {
        List<CityHistory> citiesHistory = new ArrayList<>();
        String sql = "SELECT * FROM cityhistory";
        Connection connection = Menu.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int historicalDataId = resultSet.getInt("historicalDataId");
            int cityId = resultSet.getInt("cityId");
            LocalDate eventDate = resultSet.getDate("eventDate").toLocalDate();
            int temperature = resultSet.getInt("temperature");
            citiesHistory.add(new CityHistory(historicalDataId, cityId,eventDate,temperature ));
        }
        connection.close();
        statement.close();
        resultSet.close();
        return citiesHistory;
    }

    public static void addCityHistory(CityHistory cityHistory) throws SQLException {
        String sql = "INSERT INTO CityHistory (historicalDataId, cityId, eventDate, temperature) VALUES (?, ?, ?, ?)";
        Connection connection = Menu.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, cityHistory.getHistoricalDataId());
        statement.setInt(2, cityHistory.getCityId());
        statement.setDate(3, Date.valueOf(cityHistory.getEventDate()));
        statement.setInt(4, cityHistory.getTemperature());
        statement.executeUpdate();
        connection.close();
        statement.close();
        System.out.println("City history added successfully!");
    }


    public static void updateCityHistory(CityHistory cityHistory) throws SQLException {
        String sql = "UPDATE cityhistory SET historicalDataId = ?, cityId = ?, eventDate = ?, temperature = ?";
        Connection connection = Menu.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1,cityHistory.getHistoricalDataId());
        statement.setInt(2, cityHistory.getCityId());
        statement.setDate(3, Date.valueOf(cityHistory.getEventDate()));
        statement.setInt(4, cityHistory.getTemperature());
        statement.executeUpdate();
        connection.close();
        statement.close();
        System.out.println("CityHistory updated successfully!");
    }

    public static void deleteCityHistory(CityHistory cityHistory) throws SQLException {
        String sql = "DELETE FROM cityhistory WHERE cityId = ?";
        Connection connection = Menu.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, cityHistory.getHistoricalDataId());
        statement.executeUpdate();
        connection.close();
        statement.close();
        System.out.println("City History deleted successfully!");
    }
    public static void SearchCityHistory(int Id)throws SQLException{
        String sql = "SELECT cityhistory.historicalDataId, cityhistory.cityId, cityhistory.eventDate, cityhistory.temperature, city.cityName " +
                "FROM CityHistory cityhistory " +
                "JOIN City city ON cityhistory.cityId = city.cityId " +
                "WHERE cityhistory.cityId = ?";
        Connection connection=Menu.getConnection();
        PreparedStatement statement=connection.prepareStatement(sql);
        statement.setInt(1,Id);
        ResultSet resultSet=statement.executeQuery();
        while (resultSet.next()){
            String name=resultSet.getString("cityName");

            int historicalDataId = resultSet.getInt("historicalDataId");

            int cityId = resultSet.getInt("cityId");

            int temperature = resultSet.getInt("temperature");

            LocalDate eventDate = resultSet.getDate("eventDate").toLocalDate();


            System.out.println("Historical Data ID: " + historicalDataId + "\n "+
                    "\n, City Name: " + name + "\n, Event Date: " + eventDate + "\n, Temperature: " + temperature);

        }
    }

}
