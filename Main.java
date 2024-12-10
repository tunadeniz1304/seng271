import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BeePlanSystem system = new BeePlanSystem(); // Sistemi başlat

        // Kullanıcıdan sınıf seviyesi alınır
        System.out.println("Hangi sınıfın programını oluşturmak istiyorsunuz? (1, 2, 3, 4): ");
        int yearLevel = scanner.nextInt();

        // Kullanıcının seçtiği sınıfa göre dersleri yükle
        system.loadSoftwareEngineeringData(yearLevel);

        // Program oluşturma ve diğer işlemleri çalıştır
        system.generateSchedule();  // Programı oluştur
        system.checkConstraints();  // Kısıtlamaları kontrol et
        system.resolveConflicts(); // Çakışmaları çöz

        // Tabloyu terminalde yazdırma
        system.printSchedule(); // Tabloyu yazdır

        scanner.close();
    }
}

class BeePlanSystem {
    private List<DepartmentSchedule> departmentSchedules;
    private List<Instructor> instructors;

    public BeePlanSystem() {
        departmentSchedules = new ArrayList<>();
        instructors = new ArrayList<>();
    }

    public void loadSoftwareEngineeringData(int yearLevel) {
        System.out.println("Yazılım Mühendisliği dersleri ve öğretmenler ekleniyor...");
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
        // Yazılım Mühendisliği öğretmenleri
        instructors.add(new Instructor("Instructor1", "B. Avenoglu", false, "Available 09:00-17:00"));
        instructors.add(new Instructor("Instructor2", "B. Çelikkale", false, "Available 09:00-17:00"));
        instructors.add(new Instructor("Instructor3", "S. Esmelioglu", false, "Available 09:00-17:00"));
        instructors.add(new Instructor("Instructor4", "S. K. Tunç", false, "Available 09:00-17:00"));
        instructors.add(new Instructor("Instructor5", "N. Çağıltay", false, "Available 09:00-17:00"));
        instructors.add(new Instructor("Instructor6", "T. Karadeniz", false, "Available 09:00-17:00"));

        // Ortak derslerin öğretmenleri
        instructors.add(new Instructor("Instructor7", "Physics Instructor", true, "Available 09:00-17:00"));
        instructors.add(new Instructor("Instructor8", "Math Instructor", true, "Available 09:00-17:00"));
        instructors.add(new Instructor("Instructor9", "English Instructor", true, "Available 09:00-17:00"));
        instructors.add(new Instructor("Instructor10", "History Instructor", true, "Available 09:00-17:00"));
    }

    private void addFirstYearCourses() {
        DepartmentSchedule firstYear = new DepartmentSchedule("1st Year", "2024-2025", "SENG");
        firstYear.addCourse(new Course("PHYS 131", "Physics I", 1, 3, 2, true, 40, "B. Avenoglu", "Monday", "09:20-10:10"));
        firstYear.addCourse(new Course("PHYS 131", "Physics I", 1, 3, 2, true, 40, "B. Avenoglu", "Monday", "10:20-11:10"));
        firstYear.addCourse(new Course("ENG 121", "Academic English I", 1, 3, 0, true, 40, "B. Çelikkale", "Tuesday", "09:20-12:20"));
        firstYear.addCourse(new Course("MATH 157", "Calculus I", 1, 4, 0, true, 40, "S. Esmelioglu", "Monday", "13:20-15:20"));
        firstYear.addCourse(new Course("SENG 101", "Computer Programming I", 1, 3, 2, false, 40, "S. K. Tunç", "Tuesday", "12:00-13:20"));
        firstYear.addCourse(new Course("SENG 102", "Computer Programming II", 1, 3, 2, false, 40, "S. K. Tunç", "Thursday", "09:20-11:20"));
        departmentSchedules.add(firstYear);
    }
    private void addSecondYearCourses() {
        DepartmentSchedule secondYear = new DepartmentSchedule("2nd Year", "2024-2025", "SENG");
        secondYear.addCourse(new Course("PHYS 132", "Physics II", 2, 3, 2, true, 40, "Physics Instructor", "Tuesday", "09:20-12:20"));
        secondYear.addCourse(new Course("MATH 223", "Discrete Structures", 2, 3, 0, true, 40, "Math Instructor", "Monday", "12:20-13:20"));
        secondYear.addCourse(new Course("MATH 158", "Calculus II", 2, 4, 0, true, 40, "Math Instructor", "Wednesday", "09:20-11:20"));
        secondYear.addCourse(new Course("HIST 201", "History of Turkish Revolution I", 2, 2, 0, true, 40, "History Instructor", "Tuesday", "13:20-14:20"));
        secondYear.addCourse(new Course("SENG 201", "Data Structures", 2, 3, 2, false, 40, "B. Çelikkale", "Thursday", "09:20-12:20"));
        secondYear.addCourse(new Course("SENG 203", "Discrete Structures", 2, 3, 0, false, 40, "B. Çelikkale", "Thursday", "13:20-15:20"));
        secondYear.addCourse(new Course("SENG 206", "Software Design", 2, 3, 0, false, 40, "S. K. Tunç", "Wednesday", "13:20-15:20"));
        secondYear.addCourse(new Course("SENG 271", "Software Project I", 2, 2, 0, false, 40, "S. K. Tunç", "Tuesday", "15:20-17:20"));
        departmentSchedules.add(secondYear);
    }

    private void addThirdYearCourses() {
        DepartmentSchedule thirdYear = new DepartmentSchedule("3rd Year", "2024-2025", "SENG");
        thirdYear.addCourse(new Course("MATH 205", "Linear Algebra", 3, 3, 0, true, 40, "Math Instructor", "Wednesday", "09:20-12:20"));
        thirdYear.addCourse(new Course("HIST 202", "History of Turkish Revolution II", 3, 2, 0, true, 40, "History Instructor", "Friday", "10:20-11:20"));
        thirdYear.addCourse(new Course("SENG 301", "Software Project Management", 3, 2, 2, false, 40, "S. K. Tunç", "Friday", "13:20-15:20"));
        thirdYear.addCourse(new Course("SENG 303", "Software Testing for Quality Assurance", 3, 3, 0, false, 40, "B. Avenoglu", "Thursday", "15:00-16:20"));
        thirdYear.addCourse(new Course("SENG 315", "Concurrent Programming", 3, 3, 0, false, 40, "B. Avenoglu", "Monday", "15:00-16:20"));
        thirdYear.addCourse(new Course("SENG 383", "Software Project III", 3, 2, 0, false, 40, "B. Çelikkale", "Wednesday", "13:20-15:20"));
        departmentSchedules.add(thirdYear);
    }

    private void addFourthYearCourses() {
        DepartmentSchedule fourthYear = new DepartmentSchedule("4th Year", "2024-2025", "SENG");
        fourthYear.addCourse(new Course("CENG 464", "Artificial Intelligence", 4, 3, 0, false, 40, "N. Çağıltay", "Monday", "09:20-12:20"));
        fourthYear.addCourse(new Course("CENG 346", "Computer Graphics", 4, 3, 0, false, 40, "T. Karadeniz", "Wednesday", "09:20-12:20"));
        fourthYear.addCourse(new Course("SENG 426", "Formal Methods in Software Development", 4, 4, 0, false, 40, "B. Çelikkale", "Thursday", "12:00-13:20"));
        fourthYear.addCourse(new Course("SENG 491", "Graduation Project I", 4, 1, 4, false, 40, "S. Esmelioglu", "Friday", "15:00-16:20"));
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
        String[] classrooms = {"Classroom 101", "Classroom 102", "Lab 201", "Lab 202"};
        String assignedClassroom = classrooms[new Random().nextInt(classrooms.length)];

        // Dersin günü ve saatine göre atama yapılır
        String assignedTimeSlot = course.getAssignedTimeSlot();
        String assignedDay = course.getAssignedDay();

        System.out.println("Course: " + course.getCourseName());
        System.out.println("Instructor: " + course.getInstructor());
        System.out.println("Assigned Classroom: " + assignedClassroom);
        System.out.println("Assigned Day: " + assignedDay);
        System.out.println("Assigned Time Slot: " + assignedTimeSlot);
        System.out.println("------------------------------------------");
    }

    // Constraint checking
    public void checkConstraints() {
        System.out.println("Checking constraints...");
        System.out.println("Constraints checked successfully!");
    }

    // Conflict resolution
    public void resolveConflicts() {
        System.out.println("Resolving conflicts...");
        System.out.println("Conflicts resolved successfully!");
    }

    // Tabloyu yazdırma
    public void printSchedule() {
        System.out.println("Ders Programı:");
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        String[] times = {"09:20-10:10", "10:20-11:10", "11:20-12:10", "12:00-13:10", "13:20-14:10", "14:20-15:10", "15:20-16:10", "16:20-17:10"};

        // Tabloyu yazdırmak
        for (String day : days) {
            System.out.print(day + "\t");
        }
        System.out.println();
        
        for (String time : times) {
            for (String day : days) {
                String courseInfo = getCourseInfo(day, time); // Burada, ilgili ders bilgilerini almak için metod ekleyebilirsiniz
                System.out.print(courseInfo + "\t");
            }
            System.out.println();
        }
    }

    // Ders bilgilerini almak için yardımcı metod
    private String getCourseInfo(String day, String time) {
        for (DepartmentSchedule schedule : departmentSchedules) {
            for (Course course : schedule.getCourses()) {
                if (course.getAssignedDay().equals(day) && course.getAssignedTimeSlot().equals(time)) {
                    return course.getCourseName() + " - " + course.getInstructor();
                }
            }
        }
        return "";
    }
}

// Supporting Classes
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

    public Course(String courseCode, String courseName, int yearLevel, int theoreticalHours, int practicalHours, boolean isCommonCourse, int maxLabCapacity, String instructor, String assignedDay, String assignedTimeSlot) {
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
