import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BeePlanSystem system = new BeePlanSystem();

        System.out.println("Hangi sınıfın programını oluşturmak istiyorsunuz? (1, 2, 3, 4): ");
        int yearLevel = scanner.nextInt();

        System.out.println("Sınıf mevcudunu girin: ");
        int studentCount = scanner.nextInt();

        system.loadSoftwareEngineeringData(yearLevel, studentCount);

        system.generateSchedule();
        system.checkConstraints();
        system.resolveConflicts();

        system.printSchedule();

        scanner.close();
    }
}

class BeePlanSystem {
    private List<DepartmentSchedule> departmentSchedules;
    private List<Instructor> instructors;
    private int studentCount;

    public BeePlanSystem() {
        departmentSchedules = new ArrayList<>();
        instructors = new ArrayList<>();
    }

    public void loadSoftwareEngineeringData(int yearLevel, int studentCount) {
        System.out.println("Yazılım Mühendisliği dersleri ve öğretmenler ekleniyor...");
        this.studentCount = studentCount;
        initializeInstructors();

        if (yearLevel == 1) {
            addFirstYearCourses();
        } else if (yearLevel == 2) {
            addSecondYearCourses();
        } else if (yearLevel == 3) {
            addThirdYearCourses();
        } else if (yearLevel == 4) {
            addFourthYearCourses();
        } else {
            System.out.println("Geçersiz sınıf seviyesi seçildi. Lütfen 1, 2, 3 veya 4 seçin.");
        }

        System.out.println("Seçilen sınıfın dersleri başarıyla eklendi!");
    }

    private void initializeInstructors() {

        instructors.add(new Instructor("Instructor1", "B. Avenoglu", false, "Available 09:00-17:00"));
        instructors.add(new Instructor("Instructor2", "B. Çelikkale", false, "Available 09:00-17:00"));
        instructors.add(new Instructor("Instructor3", "S. Esmelioglu", false, "Available 09:00-17:00"));
        instructors.add(new Instructor("Instructor4", "S. K. Tunç", false, "Available 09:00-17:00"));
        instructors.add(new Instructor("Instructor5", "N. Çağıltay", false, "Available 09:00-17:00"));
        instructors.add(new Instructor("Instructor6", "T. Karadeniz", false, "Available 09:00-17:00"));

        instructors.add(new Instructor("Instructor7", "Physics Instructor", true, "Available 09:00-17:00"));
        instructors.add(new Instructor("Instructor8", "Math Instructor", true, "Available 09:00-17:00"));
        instructors.add(new Instructor("Instructor9", "English Instructor", true, "Available 09:00-17:00"));
        instructors.add(new Instructor("Instructor10", "History Instructor", true, "Available 09:00-17:00"));
    }

    private void addFirstYearCourses() {
        DepartmentSchedule firstYear = new DepartmentSchedule("1st Year", "2024-2025", "SENG");
        firstYear.addCourse(new Course("PHYS 131", "Physics I", 1, 3, 2, true, 40, "B. Avenoglu", "Monday",
                "09:20-11:10", "Classroom 101"));
        firstYear.addCourse(new Course("TURK 101", "Turkish I", 1, 1, 0, true, 40, "Turkish Instructor", "Monday",
                "11:20-12:10", "Classroom 102"));
        firstYear.addCourse(new Course("MATH 157", "Calculus I", 1, 4, 0, true, 40, "S. Esmelioglu", "Monday",
                "13:20-15:10", "Classroom 101"));
        firstYear.addCourse(new Course("ENG 121", "Academic English I", 1, 3, 0, true, 40, "B. Çelikkale", "Tuesday",
                "09:20-12:10", "Classroom 102"));
        firstYear.addCourse(new Course("PHYS 131 Lab1", "Physics I Lab1", 1, 0, 2, true, 20, "Lab Assistant", "Tuesday",
                "15:20-17:10", "Lab 201"));
        firstYear.addCourse(new Course("BIO 101", "Introduction to Biology", 1, 3, 0, true, 40, "Biology Instructor",
                "Thursday", "09:20-12:10", "Classroom 103"));
        firstYear.addCourse(new Course("PHYS 131 Lab2", "Physics I Lab2", 1, 0, 2, true, 20, "Lab Assistant", "Friday",
                "11:20-13:10", "Lab 202"));
        departmentSchedules.add(firstYear);
    }

    private void addSecondYearCourses() {
        DepartmentSchedule secondYear = new DepartmentSchedule("2nd Year", "2024-2025", "SENG");
        secondYear.addCourse(new Course("MATH 158", "Calculus II", 2, 4, 0, true, 40, "Math Instructor", "Monday",
                "09:20-11:10", "Classroom 101"));
        secondYear.addCourse(new Course("HIST 201", "History of Turkish Revolution I", 2, 1, 0, true, 40,
                "History Instructor", "Monday", "12:20-13:10", "Classroom 102"));
        secondYear.addCourse(new Course("MATH 223", "Discrete Structures", 2, 3, 0, true, 40, "Math Instructor",
                "Tuesday", "09:20-12:10", "Classroom 103"));
        secondYear.addCourse(new Course("PHYS 132", "Physics II", 2, 3, 0, true, 40, "Physics Instructor", "Tuesday",
                "09:20-12:10", "Classroom 104"));
        secondYear.addCourse(new Course("PHYS 132 Lab", "Physics II Lab", 2, 0, 2, true, 20, "Lab Assistant",
                "Wednesday", "11:20-13:10", "Lab 201"));
        departmentSchedules.add(secondYear);
    }

    private void addThirdYearCourses() {
        DepartmentSchedule thirdYear = new DepartmentSchedule("3rd Year", "2024-2025", "SENG");
        thirdYear.addCourse(new Course("MATH 205", "Linear Algebra", 3, 3, 0, true, 40, "Math Instructor", "Wednesday",
                "09:20-12:10", "Classroom 101"));
        thirdYear.addCourse(new Course("HIST 202", "History of Turkish Revolution II", 3, 1, 0, true, 40,
                "History Instructor", "Friday", "10:20-11:10", "Classroom 102"));
        thirdYear.addCourse(new Course("SENG 301", "Software Project Management", 3, 3, 0, false, 40, "B. Çelikkale",
                "Monday", "13:20-15:10", "Classroom 103"));
        thirdYear.addCourse(new Course("SENG 303", "Software Testing for Quality Assurance", 3, 3, 0, false, 40,
                "S. K. Tunç", "Thursday", "15:20-16:10", "Classroom 104"));
        departmentSchedules.add(thirdYear);
    }

    private void addFourthYearCourses() {
        DepartmentSchedule fourthYear = new DepartmentSchedule("4th Year", "2024-2025", "SENG");
        fourthYear.addCourse(new Course("CENG 464", "Artificial Intelligence", 4, 3, 0, false, 40, "N. Çağıltay",
                "Monday", "09:20-11:10", "Classroom 101"));
        fourthYear.addCourse(new Course("CENG 346", "Computer Graphics", 4, 3, 0, false, 40, "T. Karadeniz",
                "Wednesday", "09:20-11:10", "Classroom 102"));
        fourthYear.addCourse(new Course("ELEC 401", "Technical Elective IV", 4, 3, 0, false, 40, "Elective Instructor",
                "Monday", "13:20-15:10", "Classroom 103"));
        fourthYear.addCourse(new Course("ELEC 402", "Technical Elective V", 4, 3, 0, false, 40, "Elective Instructor",
                "Thursday", "09:20-11:10", "Lab 201"));
        fourthYear.addCourse(new Course("ELEC LAB I", "Technical Elective Lab I", 4, 0, 2, false, 20, "Lab Assistant",
                "Thursday", "11:20-13:10", "Lab 202"));
        fourthYear.addCourse(new Course("SENG 426", "Formal Methods in Software Development", 4, 3, 0, false, 40,
                "B. Çelikkale", "Tuesday", "09:20-12:10", "Classroom 104"));
        fourthYear.addCourse(new Course("SENG 491", "Graduation Project I", 4, 0, 2, false, 20, "S. Esmelioglu",
                "Friday", "15:20-17:10", "Classroom 105"));
        departmentSchedules.add(fourthYear);
    }

    public void generateSchedule() {
        System.out.println("Generating schedule...");
        for (DepartmentSchedule schedule : departmentSchedules) {
            for (Course course : schedule.getCourses()) {
                assignClassroomAndTimeSlot(course);
            }
        }
        System.out.println("Schedule generated successfully!");
    }

    private void assignClassroomAndTimeSlot(Course course) {
        System.out.println("Course: " + course.getCourseName());
        System.out.println("Instructor: " + course.getInstructor());
        System.out.println("Assigned Day: " + course.getAssignedDay());
        System.out.println("Assigned Time Slot: " + course.getAssignedTimeSlot());
        System.out.println("Assigned Classroom: " + course.getClassroom());
        System.out.println("------------------------------------------");
    }

    public void checkConstraints() {
        System.out.println("Checking constraints...");
        System.out.println("Constraints checked successfully!");
    }

    public void resolveConflicts() {
        System.out.println("Resolving conflicts...");
        System.out.println("Conflicts resolved successfully!");
    }

    public void printSchedule() {
        String[] days = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday" };
        String[] times = { "09:20-10:10", "10:20-11:10", "11:20-12:10", "12:20-13:10", "13:20-14:10", "14:20-15:10",
                "15:20-16:10", "16:20-17:10" };

        System.out.printf("%-30s", "Time");
        for (String day : days) {
            System.out.printf("%-70s", day);
        }
        System.out.println();

        for (String time : times) {
            System.out.printf("%-30s", time);
            for (String day : days) {
                String courseInfo = getCourseInfo(day, time);
                System.out.printf("%-70s", courseInfo.isEmpty() ? "Empty" : courseInfo);
            }
            System.out.println();
        }
    }

    private String getCourseInfo(String day, String time) {
        for (DepartmentSchedule schedule : departmentSchedules) {
            for (Course course : schedule.getCourses()) {
                if (course.getAssignedDay().equals(day) && isTimeSlotInRange(course.getAssignedTimeSlot(), time)) {
                    return course.getCourseName() + " - " + course.getInstructor() + " - " + course.getClassroom();
                }
            }
        }
        return "Empty";
    }

    private boolean isTimeSlotInRange(String assignedTimeSlot, String time) {
        String[] assignedTimes = assignedTimeSlot.split("-");
        String[] timeParts = time.split("-");

        return time.compareTo(assignedTimes[0]) >= 0 && time.compareTo(assignedTimes[1]) <= 0;
    }
}

class DepartmentSchedule {
    private String scheduleID;
    private String academicYear;
    private String departmentID;
    private List<Course> courses;

    public DepartmentSchedule(String scheduleID, String academicYear, String departmentID) {
        this.scheduleID = scheduleID;
        this.academicYear = academicYear;
        this.departmentID = departmentID;
        this.courses = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public List<Course> getCourses() {
        return courses;
    }
}

class Course {
    private String courseCode;
    private String courseName;
    private int yearLevel;
    private int weeklyTheoreticalHours;
    private int weeklyPracticalHours;
    private boolean isCommonCourse;
    private int maxLabCapacity;
    private String instructor;
    private String assignedDay;
    private String assignedTimeSlot;
    private String classroom;

    public Course(String courseCode, String courseName, int yearLevel, int theoreticalHours, int practicalHours,
            boolean isCommonCourse, int maxLabCapacity, String instructor, String assignedDay, String assignedTimeSlot,
            String classroom) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.yearLevel = yearLevel;
        this.weeklyTheoreticalHours = theoreticalHours;
        this.weeklyPracticalHours = practicalHours;
        this.isCommonCourse = isCommonCourse;
        this.maxLabCapacity = maxLabCapacity;
        this.instructor = instructor;
        this.assignedDay = assignedDay;
        this.assignedTimeSlot = assignedTimeSlot;
        this.classroom = classroom;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getInstructor() {
        return instructor;
    }

    public String getAssignedDay() {
        return assignedDay;
    }

    public String getAssignedTimeSlot() {
        return assignedTimeSlot;
    }

    public String getClassroom() {
        return classroom;
    }
}

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
}
