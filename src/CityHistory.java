import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CityHistory {
    private int historicalDataId;
    private int cityId;
    private Date eventDate;
    private int temperature;
    public CityHistory(int historicalDataId, int cityId, Date eventDate, int temperature){
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

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }
    public List<CityHistory> getAllCityHistory() throws SQLException {
        List<CityHistory> citiesHistory = new ArrayList<>();
        String sql = "SELECT * FROM cityhistory";
        Connection connection = Menu.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int historicalDataId = resultSet.getInt("historicalDataId");
            int cityId = resultSet.getInt("cityId");
            Date eventDate = resultSet.getDate("eventDate");
            int temperature = resultSet.getInt("temperature");
            citiesHistory.add(new CityHistory(historicalDataId, cityId,eventDate,temperature ));
        }
        connection.close();
        statement.close();
        resultSet.close();
        return citiesHistory;
    }

    public void addCityHistory(CityHistory cityHistory) throws SQLException {
        String sql = "INSERT INTO City (historicalDataId, cityId, eventDate, temperature) VALUES (?, ?, ?, ?)";
        Connection connection = Menu.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, cityHistory.getHistoricalDataId());
        statement.setInt(2, cityHistory.getCityId());
        statement.setDate(3, cityHistory.getEventDate());
        statement.setInt(3, cityHistory.getTemperature());
        statement.executeUpdate();
        connection.close();
        statement.close();
        System.out.println("City updated successfully!");
    }

    public static void updateCityHistory(CityHistory cityHistory) throws SQLException {
        String sql = "UPDATE cityhistory SET historicalDataId = ?, cityId = ?, eventDate = ?, temperature = ?";
        Connection connection = Menu.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1,cityHistory.getHistoricalDataId());
        statement.setInt(2, cityHistory.getCityId());
        statement.setDate(3, cityHistory.getEventDate());
        statement.setInt(3, cityHistory.getTemperature());
        statement.executeUpdate();
        connection.close();
        statement.close();
        System.out.println("CityHistory updated successfully!");
    }

    public static void deleteCityHistory(int cityId) throws SQLException {
        String sql = "DELETE FROM cityhistory WHERE cityId = ?";
        Connection connection = Menu.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, cityId);
        statement.executeUpdate();
        connection.close();
        statement.close();
        System.out.println("City History deleted successfully!");
    }

}
