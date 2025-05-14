public class Astronauts {
    private String name;
    private int age;
    private String role;
    private String nationality;


    public Astronauts(String name, int age, String role, String nationality) {
        this.name = name;
        this.age = age;
        this.role = role;
        this.nationality = nationality;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getRole() {
        return role;
    }

    public String getNationality() {
        return nationality;
}
    
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    @Override
    public String toString() {
        return "Astronauts: " +
                "name='" + name + '\'' +
                ", age=" + age +
                ", role='" + role + '\'' +
                ", nationality='" + nationality;
    }
}