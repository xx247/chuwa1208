package StudentManagement;

import java.util.*;

//- Create `StudentManager` class with:
//  - `private List<Student> students` - use ArrayList
//  - `private Map<String, Student> studentMap` - use HashMap
//  - `public void addStudent(Student student)` - add to both list and map
//  - `public List<Student> getTopStudents(int n)` - return top n students by score (use sorting)
//  - `public Set<String> getUniqueNames()` - return unique names using Set
//  - `public Map<Integer, List<Student>> groupByScore()` - group students by score ranges (0-59, 60-79, 80-100)
//  - `public void removeStudentById(String id)` - remove from both collections
public class StudentManager {
    private List<Student> students = new ArrayList<>();
    private Map<String, Student> studentMap = new HashMap<>();

    public void addStudent(Student student){
        students.add(student);
        studentMap.put(student.getId(),student);
    }

    public List<Student> getTopStudents(int n){
        List<Student> sorted = new ArrayList<>(students);
        sorted.sort((a,b)->b.getScore()-a.getScore());
        int end = Math.min(n, sorted.size());
        return sorted.subList(0, end);
    }



    public Set<String> getUniqueNames(){
        Set<String> unique = new HashSet<>();
        for(Student s :students){
            unique.add(s.getName());
        }
        return unique;
    }
    public Map<Integer, List<Student>> groupByScore(){
        Map<Integer, List<Student>> groups = new HashMap<>();

        groups.put(0,new ArrayList<>());
        groups.put(60,new ArrayList<>());
        groups.put(80,new ArrayList<>());

        for(Student s : students){
            if(s.getScore()<60){
                groups.get(0).add(s);
            }
            else if(s.getScore()<80){
                groups.get(60).add(s);
            }else{
                groups.get(80).add(s);
            }
        }
        return groups;
    }

    public void removeStudentById(String id){
        Student removed = studentMap.remove(id);

        if (removed != null) {
            students.remove(removed);  // uses equals() to remove
        }
    }
}
