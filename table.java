/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dsaprojectone;

/**
 *
 * @author Administrator
 */
public class table {

    node origin = new node();
    node rowtail;
    node coltail;
    Boolean[] courses = new Boolean[21];
    Boolean[] students = new Boolean[201];
    int sumCourses=0,sumStudents=0;
    table(){
        for (int i = 0; i < 20; i++) {
            courses[i] = false;
            students[i]=false;
        }
        for( int i = 20;i<200;i++){
            students[i]=false;
        }
    
    }
    public String deleteCourse(int course){
        if(course>20 || course<1 || !courses[course]){
            return("course doesn't exist");
        }else{
        for(node i=origin;i!=null;i=i.down){
            for(node j=i;(j!=null && j.course<=course);j=j.right){
                if(j.right!=null && j.right.course==course){
                    if(j.right.right==null){
                        j.right=null;
                        if(i==origin){
                            rowtail=j;
                        }else{
                            i.end=j;
                        }
                        
                    }else{
                        j.right=j.right.right;
                    }
                    break;
                }
            }
        }
        courses[course]=false;
        sumCourses--;
        return("Course deleted");
        }
    }
    public String deleteStudent(int student){
        if(student>200 || student<0 || !students[student]){
            return("student doesn't exist");
        }else{
        for(node i=origin;i!=null;i=i.right){
            for(node j=i;(j!=null && j.student<=student);j=j.down){
                if(j.down!=null && j.down.student==student){
                    if(j.down.down==null){
                        j.down=null;
                        if(i==origin){
                            coltail=j;
                        }else{
                            i.end=j;
                        }
                    }else{
                        j.down=j.down.down;
                    }
                    break;
                }
            }
        }
        students[student]=false;
        sumStudents--;
        return("Student deleted");
        }
    }
    public String addCourse(int course) {

        if (course > 20 || course < 1) {
            return"invalid ID (1~20)";
        } else if (courses[course] == true) {
            return"course already exists in system";
        } else if (origin.right == null) {
            node newnode = new node();
            origin.right = newnode;
            newnode.course = course;
            rowtail = newnode; 
            courses[course] = true;
            sumCourses++;
            return"course " + course +" added";
        } else if (rowtail.course < course) {
            node newnode = new node();
            rowtail.right = newnode;
            newnode.course = course;
            rowtail = newnode;
            courses[course] = true;
            sumCourses++;
            return"course " + course +" added";
        } else {
            for (node i = origin; i.right != null; i = i.right) {
                if (i.right.course > course) {
                    node newnode = new node();
                    newnode.course = course;
                    newnode.right = i.right;
                    i.right = newnode;
                    courses[course] = true;
                    sumCourses++;
                    return"course " + course +" added";
                }
            }
        }
      return"";  
    }
    public String addStudent(int student) {
        if (student > 200 || student < 1) {
            return "invalid ID (1~200)";
        } else if (students[student] == true) {
            return"student already is in system";
        } else if (origin.down == null) {
            node newnode = new node();
            origin.down = newnode;
            newnode.student = student;
            coltail = newnode;
            sumStudents++;
            students[student] = true;
            return"student " + student +" added";
        } else if (coltail.student < student) {
            node newnode = new node();
            coltail.down = newnode;
            newnode.student = student;
            coltail = newnode;
            sumStudents++;
            students[student] = true;
            return"student " + student +" added";
        } else {
            for (node i = origin; i.down != null; i = i.down) {
                if (i.down.student > student) {
                    node newnode = new node();
                    newnode.student = student;
                    newnode.down = i.down;
                    i.down = newnode;
                    students[student] = true;
                    sumStudents++;
                    return"student " + student +" added";
                }
            }
        }
        return"student enrolled";

    }
    public void listCourses() {
        for (node i = origin.right; i != null; i = i.right) {
            System.out.println("course ID : " + i.course);
        }
    }
    public void listStudents() {
        for (node i = origin.down; i != null; i = i.down) {
            System.out.println("student ID : " + i.student);
        }
    }
    public String add(int student, int course) {
        if (course >20 || course<0 || student>200 || student<0 || (!courses[course]) ||(!students[student])) {
            return"course or student (or both) doesn't exist";
        } else {
            for (node i = origin; i != null; i = i.right) {
                if (i.course == course) {
                    for (node j = origin; j != null; j = j.down) {
                        if (j.student == student) {
                            if (i.down == null) {
                                node newnode = new node(student, course);
                                i.down = newnode;
                                i.end = newnode;
                                addFromStudent(j,newnode);
                            } else if (i.end.student < student) {
                                node newnode = new node(student, course);
                                i.end.down = newnode;
                                i.end = newnode;
                                addFromStudent(j,newnode);
                            }else if(i.end.student==student){
                                return"student is already in this course";
                            }else{
                                node newnode = new node(student, course);
                                for(node k=i;i.down!=null;i=i.down){
                                    if(k.down.student==student){
                                        return("student is already in this course");
                                    }else if(k.down.student>student){
                                        newnode.down=k.down;
                                        k.down=newnode;
                                        addFromStudent(j,newnode);
                                        return("student enrolled");
                                    }
                                }
                            }
                            break;
                        }
                    }
                    break;
                }
            }
        }
        return"student enrolled";
    }
    public void addFromStudent(node j,node newnode){
         if (j.right == null) {
             j.right = newnode;
             j.end = newnode;
       } else if (j.end.course < newnode.course) {
             j.end.right=newnode;
             j.end = newnode;
       }else{
             for(node k=j;k.right!=null;k=k.right){
                 if(k.right.course>newnode.course){
                      newnode.right=k.right;
                      k.right=newnode;
                      break;
                    }
               }
         }
    }
    public int sumCoursesforStudent(int student){
        if(student >200 || student <0 || !students[student]){return 0;}
        else{
            int sum =0;
            for(node i=origin;i!=null;i=i.down){
                if(i.student==student){
                    for(node j=i;j!=null;j=j.right){
                     sum++;   
                    }
                }
            }
            return(sum-1);
        }
    }
    public void listCoursesOfStudent(int student){
        if(student >200 || student <0 || !students[student]){System.out.println("student doesn't exist");}
        else{
            System.out.println("courses student "+student+" is enroled in are :");
            for(node i=origin;i!=null;i=i.down){
                if(i.student==student){
                    for(node j=i;j!=null;j=j.right){
                        if(j.course==0)
                            continue;
                        System.out.println("course : " +j.course);
                    }
                }
            }
        }
    }
    public int sumStudentsforCourse(int course){
        if(course >20 || course <0 || !courses[course]){return 0;}
        else{
            int sum =0;
            for(node i=origin;i!=null;i=i.right){
                if(i.course==course){
                    for(node j=i;j!=null;j=j.down){
                     sum++;   
                    }
                }
            }
            return(sum-1);
        }
    }
    public void listStudentsInCourse(int course){
        if(course >20 || course <0 || !courses[course]){System.out.println("course doesn't exist");}
        else{
            System.out.println("students enrolled in course "+course+" are :");
            for(node i=origin;i!=null;i=i.right){
                if(i.course==course){
                    for(node j=i;j!=null;j=j.down){
                        if(j.student==0)
                            continue;
                        System.out.println("student "+j.student);   
                    }
                }
            }
        }
    }
    public void printTable(){
        node i,j;
        for(j=origin;j!=null;j=j.down){
            int k=1;
            for(i=j;i!=null;i=i.right){
                
                if(i.student==0 && i.course==0){
                    System.out.print("            ");
                }else if(i.student==0 && i.course<10){
                    System.out.print("course 0"+i.course+" ");
                }else if(i.student==0 && i.course>9){
                    System.out.print("course "+i.course+" ");
                }else if(i.course==0 && i.student<10){
                    System.out.print("student 00"+j.student+" ");
                }else if(i.course==0 && i.student<100){
                    System.out.print("student 0"+j.student+" ");
                }else if(i.course==0 && i.student>=100){
                    System.out.print("student "+j.student+" ");
                }else{
                    while(k<21){
                        if(courses[k]==true && k!=i.course){
                            System.out.print("          ");
                            k++;
                        }else if(courses[k]==true && k==i.course){
                            System.out.print("    xx    ");
                            k++;
                            break;
                        }else{
                            k++;
                        }
                        
                    }
                }
            }
            System.out.println();
        }
    }
    public String delete(int student,int course){
        boolean done=false;
        if (course >20 || course<0 || student>200 || student<0 || (!courses[course]) ||(!students[student])) {
            return"course or student (or both) doesn't exist";
        }else{
            for(node i=origin;i!=null;i=i.right){
                if(i.course==course){
                    for(node j=i;j!=null;j=j.down){
                        if(j.down!=null && j.down.student==student){
                            if(j.down.down==null){
                                j.down=null;
                                i.end=j;
                                done=true;
                            }else{
                                j.down=j.down.down;
                                done=true;
                            }
                           break;
                        }
                    }
                    break;
                }
            }
            for(node i=origin;i!=null;i=i.down){
                if(i.student==student){
                    for(node j=i;j!=null;j=j.right){
                        if(j.right!=null && j.right.course==course){
                            if(j.right.right==null){
                                j.right=null;
                                i.end=j;
                            }else{
                                j.right=j.right.right;
                            }
                           break;
                        }
                    }
                    
                }
            }
            if(done)
                return("student removed from course");
            return("student isn't in this course");
        }
    }
}
