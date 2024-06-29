package Leetcode;

/**
 * @Description
 * @Author xiamoxin
 * @Date 2020/9/2 11:52
 **/
class MyHashSet {

    /** Initialize your data structure here. */
    boolean[] map = new boolean[1000005];
    public MyHashSet() {

    }

    public void add(int key) {
        map[key] = true;
    }

    public void remove(int key) {
        map[key] = false;
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        return map[key];
    }

    public static void main(String[] args) {
        MyHashSet myHashSet = new MyHashSet();
        System.out.println(myHashSet.contains(2));
    }
    //他们早就支撑不住了 星罗帝国 日月帝国 天魂帝国 斗灵帝国
    //大军看来 这就是他们最后的挣扎 孔德明 -- 宣子文 -- 霍雨浩
    //太好看了！顶级大前的对决！静态天赋第一对动态天赋第一！邓肯真稳，而且好快啊，肉眼可见的比后期动作速率
    //快多了！加内特这跑跳能力和防守范围太恐怖了！运动能力太吓人了！
    //还看见大卫罗宾逊了，作为之前的顶级运动天赋代表，巅峰的大卫罗宾逊和这时候的加内特谁运动能力更强？
    //邓肯静态是真的变态，裸足209的身高，站立摸高居然288；姚明226，裸足站立摸高才290，抹平了17公分的身高差距
    //足足成末了一刻钟孔老 麻烦你 日月皇导师团 密法 星罗帝国两倍以上 硬特 许家伟 徐天然
    //十级魂导师 都没有发挥啥子作用 就g了 还有唐三居然是神界战力最强的人
}