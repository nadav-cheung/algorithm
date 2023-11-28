package cn.com.nadav.structure.util;

public interface Stack<E> {

    /**
     * 将一个项目推入该堆栈的顶部
     */
    E push(E e);

    /**
     * 将栈顶元素弹出
     */
    E pop();

    /**
     * 查看栈顶元素，不删除
     */
    E peek();


    /**
     * 栈是否为空
     */
    boolean empty();

    int size();
}
