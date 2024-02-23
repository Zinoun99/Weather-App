import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class City {
    private int cityId;
    private String cityName;
    private int currentTemperature;
    private int currentHumidity;
    private int currentWindSpeed;
    public City(int cityId, String cityName, int currentTemperature, int currentHumidity, int currentWindSpeed){
        this.
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
    public class City {
        private static final String URL = "jdbc:mysql://localhost:3300/weather_app";
        private static final String USERNAME = "root";
        private static final String PASSWORD = "1234";


        public static Connection getConnection() throws SQLException {
            return City.getConnection(cityId, cityName, PASSWORD);
        }

        public static List<Student> getAllStudents() throws SQLException {
            List<Student> students = new ArrayList<>();
            String sql = "SELECT * FROM Student";
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String city = resultSet.getString("city");
                students.add(new Student(id, name, city));
            }
            connection.close();
            statement.close();
            resultSet.close();
            return students;
        }

        public static void addStudent(Student student) throws SQLException {
            String sql = "INSERT INTO Student (id, name, city) VALUES (?, ?, ?)";
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, student.getId());
            statement.setString(2, student.getName());
            statement.setString(3, student.getCity());
            statement.executeUpdate();
            connection.close();
            statement.close();
            System.out.println("Student updated successfully!");
        }

        public static void updateStudent(Student student) throws SQLException {
            String sql = "UPDATE Student SET name = ?, city = ? WHERE id = ?";
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, student.getName());
            statement.setString(2, student.getCity());
            statement.setInt(3, student.getId());
            statement.executeUpdate();
            connection.close();
            statement.close();
            System.out.println("Student updated successfully!");
        }

        public static void deleteStudent(int id) throws SQLException {
            String sql = "DELETE FROM Student WHERE id = ?";
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
            connection.close();
            statement.close();
            System.out.println("Student deleted successfully!");
        }
    }




}
