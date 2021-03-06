/*
 *  Created by IntelliJ IDEA.
 *  User: Vaibhav
 *  Date: 23-Mar-20
 *  Time: 7:15 PM
 */
package problem3.myqueue;

import problem3.node.Node;
import problem5.student.Student;

import java.util.NoSuchElementException;

public class MyPriorityQueue {
    private Node rear;
    private Node front;
    private int size;

    public MyPriorityQueue() {
        front = null;
        rear = null;
        size = 0;
    }

    public boolean offer(Student student) {
        int index = getIndex(student);
        if (index == 0) {
            if (front == null) {
                front = new Node(student);
                rear = front;
                size++;
            } else {
                Node newNextNode = front;
                front = new Node(student, newNextNode);
                size++;
            }

        } else if (index > 0 && index < size) {
            Node newNextNode = getNode(index);
            getNode(index - 1).setNext(new Node(student, newNextNode));
            size++;
        } else if (index == size) {
            Node newPreviousNode = getNode(index - 1);
            Node newNode = new Node(student);
            newPreviousNode.setNext(new Node(student));
            rear = newNode;
            size++;
        }
        return true;
    }

    private Node getNode(int index) {
        Node response = this.front;
        for (int i = 0; i < index; i++) {
            response = response.getNext();
        }
        return response;
    }

    private int getIndex(Student student) {
        if (this.size == 0) {
            return 0;
        } else {
            for (int i = 0; i < this.size; i++) {
                Student item = getNode(i).getData();
                int compare = item.compareTo(student);
                if (compare >= 0) {
                    return i;
                }

            }
        }
        return size;

    }

    public Student peek() {
        if (front == null)
            return null;
        else {
            return front.getData();
        }
    }

    public Student poll() {
        if (front == null) {
            return null;
        } else {
            Student temp = front.getData();
            front = front.getNext();
            size--;
            return temp;
        }
    }

    public Student element() {
        if (front == null)
            throw new NoSuchElementException();
        else {
            return front.getData();
        }
    }


    public Student remove() {
        if (front == null) {
            throw new NoSuchElementException();
        } else {
            Student item = peek();
            front = front.getNext();
            size--;
            return item;
        }
    }


    public void display() {
        Node temp = this.front;
        for (int i = 0; i < this.size; i++) {
            if (i != this.size - 1) {
                System.out.print("Roll NO :  " + temp.getData().getRollNo() + "   Name : " + temp.getData().getName());
                System.out.println();
                temp = temp.getNext();
            } else {
                System.out.println("Roll NO :  " + temp.getData().getRollNo() + "   Name : " + temp.getData().getName());
            }
        }
    }
}