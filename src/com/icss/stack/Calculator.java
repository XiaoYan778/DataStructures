package com.icss.stack;

/**
 * 用栈实现综合计算器
 */
public class Calculator {
    public static void main(String[] args) {
        //根据前面老师的思路，完成表达式的运算
        String expression = "5*28/7+70";

        //创建两个栈，数字栈，符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack2 = new ArrayStack2(10);

        int num2 = 0;
        int oper = 0;
        int res = 0;
        //用于扫描
        int index = 0;

        int num1 = 0;
        //将每次扫描得到char 保存到ch
        char ch = ' ';
        //用于多位数字符串拼接
        String keepNum = "";

        //开始while循环的扫描 expression
        while (true){
            //依次得到expression 的每一个字符
            ch = expression.substring(index,index+1).charAt(0);

            //判断ch是什么，然后做相应的处理
            if (operStack2.isOper(ch)){
                //如果当前的符号栈为空
                if (!operStack2.isEmpty()){

                    if (operStack2.priority(ch) <= operStack2.priority(operStack2.peek())){
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack2.pop();
                        res = operStack2.cal(num1, num2, oper);

                        numStack.push(res);

                        operStack2.push(ch);
                    }else {
                        operStack2.push(ch);
                    }
                }else {
                    operStack2.push(ch);
                }
            }else {
                //numStack.push(ch - 48);
                keepNum += ch;

                if (index == expression.length() - 1){
                    numStack.push(Integer.parseInt(keepNum));
                }else {
                    //判断下一个字符是不是数字，如果是数字就加到字符串中，如果是符号就运算符入栈
                    if (operStack2.isOper(expression.substring(index+1,index+2).charAt(0))){
                        numStack.push(Integer.parseInt(keepNum));

                        keepNum = "";
                    }
                }


            }
            index++;
            if(index >= expression.length()){
                break;
            }
        }
        while (true){
            if (operStack2.isEmpty()){
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack2.pop();
            res = operStack2.cal(num1, num2, oper);
            numStack.push(res);
        }
        System.out.printf("表达式 %s = %d",expression,numStack.pop());

    }

}

/**
 * 定义一个 ArrayStack 表示栈结构
 */
class ArrayStack2{
    /**
     * 栈的大小
     */
    private int maxSize;

    /**
     * 数组，数组模拟栈，数据就放在该数组中
     */
    private int[] stack;

    /**
     * top 表示栈顶，初始化为 -1
     */
    private int top = -1;

    /**
     * 构造器
     */
    public ArrayStack2(int maxSize){
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    /**
     * 栈满
     */
    public boolean isFull(){
        return top == maxSize -1;
    }

    /**
     * 栈空
     */
    public boolean isEmpty(){
        return top == -1;
    }

    /**
     * 入栈 - push
     */
    public void push(int value){
        //先判断栈是否满
        if (isFull()){
            System.out.println("栈满");
            return;
        }

        top++ ;
        stack[top] = value ;
    }

    /**
     * 出栈 - pop，将栈顶的数据返回
     */
    public int pop(){
        //先判断栈是否为空
        if (isEmpty()){
            throw new RuntimeException("栈空，没有数据");
        }

        int value = stack[top];
        top--;
        return value;
    }

    /**
     * 显示栈的情况，遍历栈，遍历时需要从栈顶开始显示数据
     */
    public void list(){
        //先判断栈是否为空
        if (isEmpty()){
            System.out.println("栈空，没有数据~~");
            return;
        }

        for (int i = top ; i >= 0 ; i--){
            System.out.printf("stack[%d] =  %d \n",i,stack[i]);
        }
    }

    /**
     * 增加一个方法，可以返回当前栈顶的值，但不是真正的pop
     */
    public int peek(){
        return stack[top];
    }

    /**
     * 返回运算符的优先级，优先级是程序员来确定，优先级使用数字表示
     * 数字越大，则优先级就越高
     */
    public int priority(int oper){
        if (oper == '*' || oper == '/'){
            return 1;
        }else  if (oper == '+' || oper == '-'){
            return 0;
        }else {
            return -1;
        }
    }

    /**
     * 判断是不是一个运算符
     */
    public boolean isOper(char val){
        return val == '+' || val == '-' || val == '*' || val == '/' ;
    }

    /**
     * 计算方法
     */
    public int cal(int num1,int num2,int oper){
        /**
         * res 用于存放计算的结果
         */
        int res = 0;
        switch (oper){
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
        }
        return res;
    }
}

