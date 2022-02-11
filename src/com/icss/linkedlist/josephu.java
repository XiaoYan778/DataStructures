package com.icss.linkedlist;


/**
 * 约瑟夫问题
 * 单向环形链表
 */
class josephu {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.countBoy(1,2,5);
    }
}

/**
 * 创建一个环形的单向链表
 */
class CircleSingleLinkedList{
    //创建一个first 节点，当前没有编号
    private Boy first = null;

    /**
     * 直接增加几个小孩（节点）
     * @param nums 做一个数据校验 > 0
     */
    public void addBoy(int nums){
        if (nums < 1){
            System.out.println("nums的值不正确");
            return;
        }
        //辅助指针，帮助构建环形链表
        Boy curBoy = null ;

        for (int i = 1 ;i <= nums ; i++){
            //根据编号，创建小孩节点
            Boy boy = new Boy(i);
            if (i == 1){
                first = boy;
                //构成环
                first.setNext(first);
                //让 curBoy 指向第一个小孩
                curBoy = first;
            }else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    /**
     * 遍历当前环形链表
     */
    public void showBoy(){
       //判断链表是否为空
        if (first == null){
            System.out.println("没有任何小孩~~");
            return;
        }

        //辅助指针
        Boy curBoy = first;
        while (true){
            System.out.printf("小孩的编号 %d \n",curBoy.getNo());

            //判断链表是否遍历完毕
            if (curBoy.getNext() == first){
                break;
            }

            //curBoy 后移
            curBoy = curBoy.getNext();
        }
    }

    /**
     * 根据用户输入，生成小孩出圈的顺序
     * @param statNo 表示从第几个小孩开始数数
     * @param countNum 表示数几下
     * @param nums 表示最初有多少小孩在圈中
     */
    public void countBoy(int statNo , int countNum ,int nums){
        //先对数据进行校验
        if (first == null || statNo < 1 || statNo > nums){
            System.out.println("参数输入有误，请重新输入");
            return;
        }

        //创建辅助指针，帮助完成小孩出圈
        //helper指向队伍末尾这个小孩
        Boy helper = first;
        while (true){
            if (helper.getNext() == first){
                break;
            }
            helper = helper.getNext();
        }

        //小孩报数前根据需要移动到statNo指定位置开始报数
        for (int j = 0 ; j < statNo - 1 ; j++){
            first = first.getNext();
            helper = helper.getNext();
        }

        //当小孩报数时，让fist 和 helper 指针同时移动 m - 1 次 ，然后出圈
        while (true){
            //说明圈中只有一个节点
            if (helper == first){
                break;
            }

            for (int j = 0 ; j < countNum -1 ; j++){
                first = first.getNext();
                helper = helper.getNext();
            }

            //这时first 指向的节点，就是要出圈的小孩节点
            System.out.printf("小孩 %d 出圈 \n",first.getNo());


            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的小孩编号 %d \n",first.getNo());
    }

}

/**
 * 创建一个Boy类，表示一个节点
 */
class Boy{
    //编号
    private int no;
    //指向下一个节点，默认为 null
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
