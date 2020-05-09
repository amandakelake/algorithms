package chapter1.section3;


import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.StringJoiner;

/**
 * 随机背包
 * 迭代时 应该随机访问背包中的所有元素
 * 用数组保存所有元素 并在迭代器的构造函数中随机打乱元素的顺序
 * @param <Item>
 */
public class Exercise34_RandomBag<Item> implements Iterable<Item> {
    private Item[] array; // array用来计算当前背包的容量
    private int size;

    /**
     * 构造函数中初始化一个空背包  容量为1
     */
    public Exercise34_RandomBag() {
        // TODO what's this syntax?
        array = (Item[]) new Object[1]; // 初始容量只有1的数组
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }
    public int size() {
        return size;
    }
    public void add(Item item) {
        if (size() == array.length) {
            resize(array.length * 2);
        }
        array[size] = item;
        size++;
    }

    /**
     * 扩容 具体扩多少由使用者决定
     * @param capacity 数组容量
     */
    private void resize(int capacity) {
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < size(); i++) {
            temp[i] = array[i];
        }
        array = temp;
    }

    public Iterator<Item> iterator() {
        return new RandomBagIterator();
    }

    private class RandomBagIterator implements Iterator<Item> {
        int index;
        Item[] arrayCopy;

        /**
         * 每一轮迭代 构造一个打乱的数组arrayCopy 然后遍历改数组
         * 相对于两次不同的迭代  arrayCopy就是随机的
         */
        public RandomBagIterator() {
            index = 0;
            arrayCopy = (Item[]) new Object[size];
            for (int i = 0; i<size; i++) {
                arrayCopy[i] = array[i];
            }
            sortArrayCopy();
        }

        public boolean hasNext() {
            return index < size();
        }

        /**
         * 在一次迭代中  arrayCopy是固定的顺序  迭代arrayCopy即可
         * @return
         */
        public Item next() {
            Item item = arrayCopy[index];
            index++;
            return item;
        }

        /**
         * 从第一位元素开始 随机与某个位置的元素交换位置
         */
        private void sortArrayCopy() {
            for (int i=0; i< size; i++) {
                int randomIndex = StdRandom.uniform(0, size -1);
                Item temp = arrayCopy[i];
                arrayCopy[i] = arrayCopy[randomIndex];
                arrayCopy[randomIndex] = temp;
            }
        }
    }

    public static void main(String[] args) {
        Exercise34_RandomBag<Integer> randomBag = new Exercise34_RandomBag<Integer>();
        randomBag.add(1);
        randomBag.add(2);
        randomBag.add(3);
        randomBag.add(4);
        randomBag.add(5);
        randomBag.add(6);
        randomBag.add(7);
        randomBag.add(8);

        StdOut.print("Random bag items: ");
        StringJoiner randomBagItems = new StringJoiner(" ");
        for (int item: randomBag) {
            randomBagItems.add(String.valueOf(item));
        }
        StdOut.println(randomBagItems.toString());
    }
}
