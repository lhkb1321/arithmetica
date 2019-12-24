package com.qd.stack;

import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.Stack;

/**
 * @description 实现一个特殊的栈，在实现栈的基本的功能的基础上，返回栈中最小的元素。
 * @require 1.push pop getMin的时间复杂度都是O(1)
 * 2.可以使用现有的栈结构
 * 定义两个栈结构
 * stackCache:用于存放数据
 * stackMin：用于标记状态
 * @conclusion 在进行栈状态更新的时候同步更新维护的状态
 * 1.为何选择栈：要想时刻根据进入和删除的元素，更新min()状态，所以需要的更新是必须在栈顶，后进先感知变化的
 * 所以选用栈结构。
 * @attetion 添加和删除操作时，注意栈中为空的情况。出错的地方
 * @reference https://juejin.im/post/5b5131256fb9a04fcf59d0b4
 */
public class GetMinStack {

    private static Logger logger = Logger.getLogger(GetMinStack.class);

    private static Stack<Integer> stackCache = new Stack<>();
    private static Stack<Integer> stackMin = new Stack<>();

    /**
     * 压栈
     *
     * @param element 插入的元素 类型Integer
     */
    void push(Integer element) {

        if (stackCache.isEmpty()) {

            //刚开始入栈的数据,空栈
            stackMin.push(element);
            stackCache.push(element);
        } else {

            //判断此时的状态，更新或空置
            if (stackMin.peek() > element) {

                //更新
                stackMin.push(element);
                stackCache.push(element);

            } else {

                //空置
                stackMin.push(stackMin.peek());
                stackCache.push(element);

            }

        }

    }

    /**
     * 弹栈:弹出并删除
     */
    Integer pop() {

        //同步消除状态,注意空栈的现象
        if (stackMin.isEmpty()) {

            return null;

        }
        stackMin.pop();
        Integer popElement = stackCache.pop();

        return popElement;

    }

    /**
     * 提取状态
     *
     * @return 返回栈中的最小值
     */
    Integer getMin() {

        //提取栈顶元素
        if (stackMin.isEmpty()) {

            return null;

        }
        Integer minElemnt = stackMin.peek();

        return minElemnt;

    }

    @Test
    public void stackTest() {

        GetMinStack getMinStack = new GetMinStack();

        getMinStack.push(1);
        System.err.println("---- min ---- " + getMinStack.getMin());
        getMinStack.push(3);
        System.err.println("---- min ---- " + getMinStack.getMin());
        getMinStack.push(4);
        System.err.println("---- min ---- " + getMinStack.getMin());
        getMinStack.push(0);
        System.err.println("---- min ---- " + getMinStack.getMin());
        getMinStack.push(9);
        System.err.println("---- min ---- " + getMinStack.getMin());
        getMinStack.push(-1);
        System.err.println("---- min ---- " + getMinStack.getMin());

        System.err.println("---------------------------- 栈顶清除计划开始 ------------------------------");

        getMinStack.pop();
        System.err.println("---- min ---- " + getMinStack.getMin());
        getMinStack.pop();
        System.err.println("---- min ---- " + getMinStack.getMin());
        getMinStack.pop();
        System.err.println("---- min ---- " + getMinStack.getMin());
        getMinStack.pop();
        System.err.println("---- min ---- " + getMinStack.getMin());
        getMinStack.pop();
        System.err.println("---- min ---- " + getMinStack.getMin());
        getMinStack.pop();
        System.err.println("---- min ---- " + getMinStack.getMin());

        logger.info("------------------ 结束 -----------------");

    }

}
