class Instructor {
    private String instructorID;
    private String name;
    private boolean isExternal;
    private String personalSchedule;

    public Instructor(String instructorID, String name, boolean isExternal, String personalSchedule) {
        this.instructorID = instructorID;
        this.name = name;
        this.isExternal = isExternal;
        this.personalSchedule = personalSchedule;
    }

    public String getInstructorID() {
        return instructorID;
    }

    public String getName() {
        return name;
    }

    public boolean isExternal() {
        return isExternal;
    }

    public String getPersonalSchedule() {
        return personalSchedule;
    }
}
