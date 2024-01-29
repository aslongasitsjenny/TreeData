
public class Tree {
    private String location;
    private String name;
    private String age;
    double height;
    private String borough;

    public Tree(String location, String name, String age, double height) {
        this.location = location;
        this.name = name;
        this.age = age;
        this.height = height;
    }

    public Tree(String objectid, String borough, String treeName, String ageGroup, int age) {
    }

    public Tree(int objectId, String borough, String treeName, String ageGroup, double heightM) {
        this.borough = borough;
        this.name = treeName;
        this.age = ageGroup;
        this.height = heightM;
    }


    public String getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public int getHeight() {
        return (int) height;
    }

    public String getBorough() {
        return borough;
    }
}