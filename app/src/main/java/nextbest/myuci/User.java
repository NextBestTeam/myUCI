package nextbest.myuci;

public class User {

    String name, major, level, phone;


    public User(){

    }

    public User(String name, String major, String level, String phone){
        this.name  = name;
        this.major = major;
        this.level = level;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDescription() {
        return phone;
    }

    public void setDescription(String phone) {
        this.phone= phone;
    }


}
