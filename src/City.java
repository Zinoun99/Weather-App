import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class City {
    private int cityId;
    private String cityName;
    private int currentTemperature;
    private int currentHumidity;
    private int currentWindSpeed;
    public City(){}
    
    public City(int cityId, String cityName, int currentTemperature, int currentHumidity, int currentWindSpeed){
        this.cityId=cityId;
        this.cityName=cityName;
        this.currentTemperature=currentTemperature;
        this.currentHumidity=currentHumidity;
        this.currentWindSpeed=currentWindSpeed;
    }
    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(int currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public int getCurrentHumidity() {
        return currentHumidity;
    }

    public void setCurrentHumidity(int currentHumidity) {
        this.currentHumidity = currentHumidity;
    }

    public int getCurrentWindSpeed() {
        return currentWindSpeed;
    }

    public void setCurrentWindSpeed(int currentWindSpeed) {
        this.currentWindSpeed = currentWindSpeed;
    }

    public static List<City> displayCity() throws SQLException {
        List<City> cities = new ArrayList<>();
        String sql = "SELECT * FROM city";
        Connection connection = Menu.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int cityId = resultSet.getInt("cityId");
            String cityName = resultSet.getString("cityName");
            int currentTemperature = resultSet.getInt("currentTemperature");
            int currentHumidity = resultSet.getInt("currentHumidity");
            int currentWindSpeed = resultSet.getInt("currentWindSpeed");
            cities.add(new City(cityId, cityName, currentTemperature,currentHumidity,currentWindSpeed ));
        }
        connection.close();
        statement.close();
        resultSet.close();
        return cities;
    }

    public static void addCity(City city) throws SQLException {
        String sql = "INSERT INTO city (cityId, cityName, currentTemperature, currentHumidity, currentWindSpeed) VALUES (?, ?, ?, ?, ?)";
        Connection connection = Menu.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, city.getCityId());
        statement.setString(2, city.getCityName());
        statement.setInt(3, city.getCurrentTemperature());
        statement.setInt(4, city.getCurrentHumidity());
        statement.setInt(5, city.getCurrentWindSpeed());
        statement.executeUpdate();
        connection.close();
        statement.close();
        System.out.println("City updated successfully!");
    }

    public static void updateCity(City city) throws SQLException {
        String sql = "UPDATE City SET cityName = ?, currentTemperature = ?, currentHumidity = ?, currentWindSpeed = ? WHERE cityId = ?";
        Connection connection = Menu.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, city.getCityName());
        statement.setInt(2, city.getCurrentTemperature());
        statement.setInt(3, city.getCurrentHumidity());
        statement.setInt(4, city.getCurrentWindSpeed());
        statement.setInt(5, city.getCityId());
        statement.executeUpdate();
        connection.close();
        statement.close();
        System.out.println("City updated successfully!");
    }


    public static void deleteCity(City city) throws SQLException {
        String sql = "DELETE FROM city WHERE cityId = ?";
        Connection connection = Menu.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, city.cityId);
        statement.executeUpdate();
        connection.close();
        statement.close();
        System.out.println("City deleted successfully!");
    }
    public static void SearchCity(String name)throws SQLException{
        ArrayList<City> search = new ArrayList<>();
        String sql="SELECT * FROM city WHERE cityName = ?";
        Connection connection=Menu.getConnection();
        PreparedStatement statement=connection.prepareStatement(sql);
        statement.setString(1,name);
        ResultSet resultSet= statement.executeQuery();
        while (resultSet.next()){
            int cityId= resultSet.getInt("cityId");
            String cityName=resultSet.getString("cityName");
            int currentTemperature=resultSet.getInt("currentTemperature");
            int currentHumidity=resultSet.getInt("currentHumidity");
            int currentWindSpeed=resultSet.getInt("currentWindSpeed");
            search.add(new City(cityId,cityName,currentTemperature,currentHumidity,currentWindSpeed));
            System.out.println("cityId: "+cityId+"\n city Name: "+cityName+"\n currentTemperature: "+currentTemperature+"\n currentHumidity: "+currentWindSpeed);
        }

        connection.close();
        statement.close();
    }
}
