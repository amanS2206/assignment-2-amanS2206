/*
 *  Created by IntelliJ IDEA.
 *  User: Vaibhav
 *  Date: 23-Mar-20
 *  Time: 7:15 PM
 */
package problem3.myqueue;
import problem3.node.Node;
import problem5.student.Student;

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
        int index = getRightIndex(student);
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

    private int getRightIndex(Student student) {
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


}
