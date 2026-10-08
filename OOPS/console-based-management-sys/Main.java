import java.util.HashMap;
import java.util.Scanner;

class Student{
    int s_id =0;
    String name;
    int roll ;
    boolean isPresent = false;
    String branch = "CSE";

    Student(String name , int roll , String branch){
        s_id++;
        this.name = name;
        this.branch = branch;
        this.roll = roll;
    }

    @Override
    public String toString() {
        return "Student{" +
                "s_id=" + s_id +
                ", name='" + name + '\'' +
                ", roll=" + roll +
                ", isPresent=" + isPresent +
                ", branch='" + branch + '\'' +
                '}';
    }
}

class Operations{
    //Create a Student DataBase
   static HashMap<Integer , Student> studentHashMap = new HashMap<>();

   static void  create_student(Scanner sc){

       System.out.println("Enter college Roll Number :");
       int roll = sc.nextInt();
       System.out.println("Enter your Full Name :");
       String name = sc.next();
       System.out.println("Enter college Branch :");
       String branch = sc.next();

       Student student = new Student(name,roll , branch);
       System.out.println("Student " + student.name + " Has Been created ..");
       System.out.println(student.toString());
       studentHashMap.put(roll , student);

    }

   static void  update_Student(int roll , Scanner sc){

       // get the student from map using roll(key);
       Student student = studentHashMap.get(roll);

       System.out.println("Type 1 to update name..");
       System.out.println("Type 2 to update roll");
       System.out.println("Type 3 to update branch");

       int input = sc.nextInt();

       switch (input){
           case 1:
               System.out.println("Enter Updated name ");
               String new_name = sc.nextLine();
               student.name = new_name;
               break;
           case 2:
               System.out.println("Enter updated roll..");
               int new_roll = sc.nextInt();
               student.roll = new_roll;
               roll = new_roll;
               break;
           case 3:
               System.out.println("Enter Updated branch");
               String new_branch = sc.next();
               student.branch = new_branch;
               break;

       }
       studentHashMap.put(roll , student);
       System.out.println(" updated Student data ...."+student.toString());


    }

   static void delete_student(Scanner sc ){
       System.out.println("Please enter the roll number of student");
       int roll = sc.nextInt();
       studentHashMap.remove(roll);
        System.out.println("Student deleted ....");

    }

  static void  mark_Present(Scanner sc ){
      System.out.println("Please enter the roll number of student");
      int roll = sc.nextInt();
       Student student = studentHashMap.get(roll);
       student.isPresent = true;

       System.out.println("Student mark present ....");
    }

  static void  get_all_Student(){
       studentHashMap.forEach((key , value)->{
           System.out.println(value.toString());
       });
    }
}


class Main{
    public  static  void main(String[] args){

        Scanner sc = new Scanner(System.in);
        boolean ter = true;
        while(ter){
            System.out.println("1 > Press 1 for Create Student :");
            System.out.println("2 > Press 2 for Update Student :");
            System.out.println("3 > Press 3 for Delete Student :");
            System.out.println("4 > Press 4 for Mark_Present Student :");
            System.out.println("5 > Press 5 to Get all Student :");
            System.out.println("6 > Press 6 Exit :");

            int input = sc.nextInt();

           switch (input){
               case 1:

                   Operations.create_student(sc);
                   break;


               case 2 :
                   //update student
                   System.out.println("Please enter the roll number of student");
                   int roll = sc.nextInt();
                  Operations.update_Student(roll , sc);
                   break;

               case 3 :
                   // delete student\

                   Operations.delete_student(sc);
                   break;
               case 4:
                   // Mark Present

                  Operations.mark_Present(sc);
                   break;

               case 5 :
                   // get all student
                   Operations.get_all_Student();
                   break;
               case 6 :
                   //Exit
                   ter = false;
                   break;
           }




        }


    }
}