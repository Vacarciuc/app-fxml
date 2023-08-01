package org.example;
    //I created an object to model an object
public class ModelForList extends Contract {
    private String firstName;
    private String lastName;
    private String address;
    private String speed;
    private String bandwidth;
    private String duration;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getBandwidth() {
        return bandwidth;
    }

    public void setBandwidth(String bandwidth) {
        this.bandwidth = bandwidth;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    //

    @Override
    public String toString() {
        return "ModelForList{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", speed='" + speed + '\'' +
                ", bandwidth='" + bandwidth + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}
