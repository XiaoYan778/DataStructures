package com.icss.linkedlist;

/**
 *双向链表
 */
public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2,"卢俊义","玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3,"吴用","智多星");
        HeroNode2 hero4 = new HeroNode2(4,"林冲","豹子头");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);

        System.out.println("添加后的双向链表");
        doubleLinkedList.list();

        HeroNode2 newHeroNode = new HeroNode2(4,"公孙胜","人云龙");
        doubleLinkedList.update(newHeroNode);
        System.out.println("修改后的双向链表");
        doubleLinkedList.list();

        doubleLinkedList.del(3);
        System.out.println("删除后的双向链表");
        doubleLinkedList.list();

        HeroNode2 hero5 = new HeroNode2(3,"李白","傻子");
        doubleLinkedList.addByOrder(hero5);
        System.out.println("增加双向链表");
        doubleLinkedList.list();
    }
}

/**
 * 创建一个双向链表的类
 */
class DoubleLinkedList {
    /**
     * 先初始化一个头结点，头结点不要动，不存放具体的数据
     */
    private HeroNode2 head = new HeroNode2(0,"","");

    /**
     * 返回头结点
     */
    public HeroNode2 getHead() {
        return head;
    }

    /**
     * 添加一个节点到双向链表的最后
     * @param heroNode
     */
    public void add(HeroNode2 heroNode){

        //因为head 节点不能动，因此我们需要一个辅助遍历 temp
        HeroNode2 temp = head;
        //遍历链表，找到最后
        while (true){
            //找到链表的最后
            if (temp.next == null){
                break;
            }
            //如果没有找到最后，将temp后移
            temp = temp.next;
        }
        //当退出这个while循环时，temp就指向了链表的最后
        temp.next = heroNode;
        heroNode.pre = temp;

    }

    /**
     * 修改双向链表内容，可以看到双向链表的节点内容修改和单向链表一样
     */
    public void update(HeroNode2 newHeroNode){
        //判断是否空
        if(head.next == null){
            System.out.println("链表为空~~");
            return;
        }
        //找到需要修改的节点，根据 no编号
        //定义一个辅助变量
        HeroNode2 temp = head.next;
        boolean flag = false;//表示是否找到该节点
        while (true){
            if (temp == null){ //已经遍历完链表
                break;
            }
            if (temp.no == newHeroNode.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag，判断是否找到要修改的节点
        if (flag){
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        }else {//没有找到
            System.out.printf("没有找到 编号 %d 的节点，不能修改\n",newHeroNode.no);
        }

    }

    /**
     * 从双向链表中删除一个节点，
     * 说明
     * 1 对于双向链表，我们可以直接找到要删除的这个节点
     * 2 找到后，自我删除即可
     */
    public void del(int no){

        //判断当前链表是否为空
        if (head.next == null){
            System.out.println("链表为空，无法删除");
            return;
        }

        //辅助指针变量
        HeroNode2 temp = head.next ;
        boolean flag = false;
        while(true){
            //到链表的最后
            if(temp == null){
                break;
            }
            if (temp.no == no){
                flag = true;
                break;
            }

            //temp 后移，遍历
            temp = temp.next;
        }

        if (flag){
            temp.pre.next = temp.next;
            //如果是最后一个节点，就不需要执行下面这句话，否则出现空指针

            if(temp.next != null){
                temp.next.pre = temp.pre;
            }
        }else { System.out.printf("没有找到 编号 %d 的节点，不能修改\n",no);
        }
    }
    //显示链表【遍历】
    public void list(){
        //判断链表是否为空
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        //因为头节点，不能动，所以需要一个辅助变量来遍历
        HeroNode2 temp = head.next;
        while (true){
            //判断链表是否为空
            if (temp == null){
                break;
            }
            //输出节点的信息
            System.out.println(temp);
            //将temp后移，一定小心
            temp = temp.next;
        }
    }

    //第二种方式在添加英雄时，根据排名将英雄插入到指定位置
    //(如果有这个排名，则添加失败，并给出提示)
    public void addByOrder(HeroNode2 heroNode){
        //因为头节点不能动，因此我们仍然通过一个辅助指针（变量）来帮助找到添加的位置
        //因为单链表，因为我们找的temp 时位于添加位置的前一个节点，否则插入不了
        HeroNode2 temp = head ;
        boolean flag = false;
        while (true){
            if (temp.next == null){//说明temp已经在链表的最后
                break;
            }
            if (temp.next.no > heroNode.no){
                break;
            }else if (temp.next.no == heroNode.no){//说明希望添加的hero编号已经存在
                flag = true;
                break;
            }
            temp = temp.next;//后移，遍历当前单链表
        }
        //判断flag的值
        if (flag){//不能添加，说明编号存在
            System.out.printf("准备插入的英雄的编号 %d 已经存在了，不能加入\n",heroNode.no);
        }else {
            if(temp.next != null){
                heroNode.next = temp.next;
                temp.next.pre = heroNode;
            }

            temp.next = heroNode;
            heroNode.pre = temp ;

            }
        }
    }


/**
 * 定义 HeroNode2，每个 HeroNode 对象就是一个节点
 */
class HeroNode2{
    public int no;
    public String name;
    public String nickname;

    /**
     * 指向下一个节点，默认为 null
     */
    public HeroNode2 next;

    /**
     * 指向前一个节点，默认为 null
     */
    public HeroNode2 pre;

    public HeroNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
