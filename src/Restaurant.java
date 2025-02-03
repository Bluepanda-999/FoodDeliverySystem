public class Restaurant {
    private int id;
    private String name;
    private String location;

    public Restaurant(int id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "Restaurant ID: " + id + ", Name: " + name + ", Location: " + location;
    }
}