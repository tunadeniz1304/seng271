class Classroom {
    private String classroomID;
    private String type; // Lab veya Regular
    private int capacity;
    private String availability; // "Available", "Occupied" vb.

    public Classroom(String classroomID, String type, int capacity, String availability) {
        this.classroomID = classroomID;
        this.type = type;
        this.capacity = capacity;
        this.availability = availability;
    }

    public String getClassroomID() {
        return classroomID;
    }

    public String getType() {
        return type;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getAvailability() {
        return availability;
    }
}
