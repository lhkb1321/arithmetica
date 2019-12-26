package com.qd.stack;

import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.Stack;

/**
 * @author 30588
 * @description 用两个栈实现队列，支持队列的基本操作(poll、peek、add)
 * 栈的三个特征：压栈 弹栈 先进后出 体现一种栈结构的特点
 *
 * @refenrence https://blog.csdn.net/qmdweb/article/details/80537602
 */
public class ConstructingQueue {

    private static Logger logger = Logger.getLogger(GetMinStack.class);
    private static Stack<Integer> elementCache = new Stack<>();

    /**
     * 实现获取并删除栈底元素的pop方法，递归的过程就是出入栈的过程，后进先解，层层递进
     */
    int getStackBottom(Stack<Integer> stack) {

        //进入入栈递进段
        Integer element = stack.pop();

        //边界条件：入栈之前需要检查是否需要入栈
        if (stack.isEmpty()) {

            //开始出栈，材料准备好了

            return element;
        }

        //返回结果并进入出栈返回段,返回结果的同时需要恢复数组《卡住的地方》
        int last = getStackBottom(stack);

        //出栈 有出口值了 唤醒
        stack.push(element);

        return last;

    }

    /**
     * @description 实现一个逆序栈的方法, 我需要取得栈底，再推入栈顶，考虑入栈做什么出栈做什么就好
     * 可以用画图的方式实现，过程推演。
     */

    void reverseStackOder(Stack<Integer> stack) {

        //条件边界(出入栈标志)：在下一次入栈需要判断一下
        if (stack.isEmpty()) {

            //出栈的出口值

            return;

        }

        //入栈
        int stackBottom = getStackBottom(stack);

        //开始跳转《卡住的地方》
        reverseStackOder(stack);

        //出栈 有出口值了 唤醒
        stack.push(stackBottom);



    }

    boolean add(Integer num) {
        if (elementCache.size() > 10) {

            return false;

        }
        elementCache.push(num);
        return true;

    }

    Integer poll() {

        if (elementCache.isEmpty()) {

            return null;

        }
        //先翻转
        reverseStackOder(elementCache);
        //取数
        Integer stackBottom = elementCache.pop();

        //再翻回去,保障后续数据连续性
        reverseStackOder(elementCache);
        return stackBottom;

    }

    Integer peek(Integer num) {

        if (elementCache.isEmpty()) {

            return -1;

        }

        if (elementCache.search(num) != -1) {

            return elementCache.search(num);

        }

        return -1;
    }

    @Test
    public void getQueue() {

        ConstructingQueue constructingQueue = new ConstructingQueue();

        logger.info("--------- 添加 ---------" + constructingQueue.add(1));
        logger.info("--------- 添加 ---------" + constructingQueue.add(2));
        logger.info("--------- 添加 ---------" + constructingQueue.add(3));
        logger.info("--------- 删除 ---------" + constructingQueue.poll());
        logger.info("--------- 删除 ---------" + constructingQueue.poll());
        logger.info("--------- 删除 ---------" + constructingQueue.poll());
        logger.info("--------- 删除 ---------" + constructingQueue.poll());

    }

}
