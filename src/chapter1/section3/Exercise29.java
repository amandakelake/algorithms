package chapter1.section3;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.StringJoiner;

/**
 *
 * @param <Item>
 */
public class Exercise29<Item> implements Iterable<Item> {
    private class Node {
        Item item;
        Node next;
    }

    Node last;
    int size;

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (isEmpty()) {
            last = new Node();
            last.item = item;
            last.next = last; // 只有一个元素，指向自己
        } else {
            Node node = new Node();
            node.item = item;

            if (size == 1) {
                last.next = node;
                node.next = last;
            } else {
                node.next = last.next; // 将新插入的结点指向表头
                last.next = node; // 原先的last结点指向新的入队列的结点
            }
            last = node; // 答案中没有把node赋值给last, 还是要自己多跑跑测试  验证是否正确
        }
        size++;
    }

    public Item dequeue() {
        if (isEmpty()) {
            return null;
        }
        Item item;
        if (size == 1) {
            item = last.item;
            last = null;
        } else {
            item = last.next.item; // 删除表头结点
            last.next = last.next.next; // 将last结点指向表头第二个元素
        }
        size--;
        return item;
    }

    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<Item> {
        private Node current;
        int count = 0;

        public QueueIterator() {
            if (last != null && size > 1) {
                current = last.next;
            } else {
                current = last;
            }
        }

        public Item next() {
            count++;
            Item item = current.item;
            current = current.next;
            return item;
        }

        public boolean hasNext() {
            return count < size;
        }
    }

    public static void main(String[] args) {
        Exercise29<Integer> queue = new Exercise29<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);

        StringJoiner queueItems = new StringJoiner(" ");
        for(int item: queue) {
            queueItems.add(String.valueOf(item));
        }

        StdOut.println("Queue items: " + queueItems.toString());
    }
}
