public class Astronaut {
    private String name;
    private String role;
    private int age;
    private String nationality;

    public Astronaut(String name, String role, int age, String nationality) {
        this.name = name;
        this.role = role;
        this.age = age;
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
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }

    public void setAge(int age) {
        if (age < 0 || age > 100) {
            throw new IllegalArgumentException("Age must be a valid integer between 0 and 100");
        }
        this.age = age;
    }

    public void setRole(String role) {
        if (role== null || role.isEmpty()) {
            throw new IllegalArgumentException("Role cannot be null or empty");
        }
        this.role = role;
    }

    public void setNationality(String nationality) {
        if (nationality == null || nationality.isEmpty()) {
            throw new IllegalArgumentException("Nationality cannot be null or empty");
        }
        this.nationality = nationality;
    }
    @Override
    public String toString() {
        return "Name: " + name + "\n" +
            "Age: " + age + "\n" +
            "Role: " + role + "\n" +
            "Nationality: " + nationality;
    }

}