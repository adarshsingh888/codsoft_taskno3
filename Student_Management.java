import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
class Student implements Serializable{
    private String name;
    private int rollNumber;
    private String grade;
    public Student(String name,int rollNumber,String grade){
        this.name=name;
        this.rollNumber=rollNumber;
        this.grade=grade;
    }
    public int getRollNumber(){
        return rollNumber;
    }
    public String getName(){
        return name;
    }
    public String getGrade(){
        return grade;
    }
    @Override
    public String toString() {
        return "Name: " + name + ", Roll Number: " + rollNumber + ", Grade: " + grade;
    }


}
class StudentManagementSystem{
    private List<Student> students;
    private String dataFilePath;
    
    public StudentManagementSystem(String dataFilePath){
        this.dataFilePath=dataFilePath;
        students=new ArrayList<>();
        loadDataFromFile();


    }
    public void addStudent(Student student){
        students.add(student);
        saveDataToFile();
    }
    public void removeStudent(int rollNumber){
        students.removeIf(student -> student.getRollNumber()==rollNumber);

    }
    public Student searchStudent(int rollNumber){
        for(Student student : students){
            if(student.getRollNumber()==rollNumber){
                return student;
            }
        }
        return null;
    }
    public List<Student>getAllStudents(){
        return new ArrayList<>(students);
    }
    private void loadDataFromFile(){
        try(ObjectInputStream ois=new ObjectInputStream(new FileInputStream(dataFilePath))){
            students = (List<Student>) ois.readObject();
        }catch (IOException |ClassNotFoundException e){
            //Handle exceptions
        }

    }
    private void saveDataToFile(){
        try(ObjectOutputStream oos =new ObjectOutputStream(new FileOutputStream(dataFilePath))){
            oos.writeObject(students);
        }catch (IOException e){
            //Handle exeception
        }
    }


}
public class Student_Management {
    public static void main(String[] args){
        String dataFilePath ="student.text";
        StudentManagementSystem sms = new StudentManagementSystem(dataFilePath);
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println();
            System.out.println("***************************************************************");
            System.out.println("***************************************************************");
            System.out.println();
            System.out.println("STUDENT MANAGEMENT SYSTEM");
            System.out.println();
            System.out.println();
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Exit"); 
            System.out.println();
            System.out.println();
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();//Consume newline
            switch (choice){
                case 1:
                    System.out.println();
                    System.out.println();
                    System.out.print("Enter student name : ");
                    String name=scanner.nextLine();
                    System.out.print("Enter student roll number: ");
                    int rollNumber=scanner.nextInt();
                    scanner.nextLine(); //Consume newline
                    System.out.print("Enter student grade : ");
                    String grade=scanner.nextLine();
                    sms.addStudent(new Student(name,rollNumber,grade));
                    System.out.println();
                    System.out.println("Student added successfully!");
                    break;
                case 2: 
                    System.out.println();
                    System.out.println();
                    System.out.print("Enter student roll number to remove: ");
                    int rollToRemove = scanner.nextInt();
                    sms.removeStudent(rollToRemove);
                    System.out.println();
                    System.out.println("Student removed successfully!");
                    break;
                case 3: 
                    System.out.println();
                    System.out.println();
                    System.out.print("Enter student roll number to search: ");
                    int rollToSearch = scanner.nextInt();
                    System.out.println();
                    Student searchedStudent = sms.searchStudent(rollToSearch);
                    if (searchedStudent != null) {
                    System.out.println("Student Found: " + 
                    searchedStudent);
                    } else {
                    System.out.println("Student not found.");
                    }
                    break;
                case 4:
                   System.out.println();
                   System.out.println();
                   List<Student> allStudents = sms.getAllStudents();
                   if(allStudents.isEmpty()){
                      System.out.println("No student found.");

                   }
                   else{
                      System.out.println("All Student: ");
                      System.out.println();
                      for(Student student : allStudents){
                         System.out.println(student);
                      }
                   }
                   break;
                 case 5:
                    System.out.println();
                    System.out.println();
                    System.out.println("Exiting the application.");
                    scanner.close();
                    System.exit(0);
                    break;
                default :
                    System.out.println();
                    System.out.println();
                    System.out.println("Invalid choice.Please select again."); 

                        
                     

            }

        }
    }
    
    
}
