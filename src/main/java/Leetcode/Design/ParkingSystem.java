package Leetcode.Design;

/**
 * @author maoXin
 * @Description:
 * @Date 2020/12/25 15:20
 */
public class ParkingSystem {

    int big;
    int medium;
    int small;

    /**
     * @Description
     * @ 请你给一个停车场设计一个停车系统。停车场总共有三种不同大小的车位：大，中和小，每种尺寸分别有固定数目的车位。
     *
     * 请你实现 ParkingSystem 类：
     *
     * ParkingSystem(int big, int medium, int small) 初始化 ParkingSystem 类，三个参数分别对应每种停车位的数目。
     * bool addCar(int carType) 检查是否有 carType 对应的停车位。 carType 有三种类型：大，中，小，分别用数字 1， 2 和 3 表示。一辆车只能停在  carType 对应尺寸的停车位中。如果没有空车位，请返回 false ，否则将该车停入车位并返回 true 。
     *  
     *solution: 1. 構造函數初始化 big medium small 數量  2. addCar 对应的if (type--) > 0 return true else return false
     **/
    public ParkingSystem(int big, int medium, int small) {
        this.big = big;
        this.medium = medium;
        this.small = small;
    }

    public boolean addCar(int carType) {
        switch (carType) {
            case 1:
                if (big-- < 0) {
                    return false;
                }
                return true;
            case 2:
                if (medium-- < 0) {
                    return false;
                }
                return true;
            case 3:
                if (small-- < 0) {
                    return false;
                }
                return true;
            default:
                break;
        }
        return false;
    }

    public static void main(String[] args) {
        ParkingSystem parkingSystem = new ParkingSystem(1, 1, 0);
        parkingSystem.addCar(1); // 返回 true ，因为有 1 个空的大车位
        parkingSystem.addCar(2); // 返回 true ，因为有 1 个空的中车位
        parkingSystem.addCar(3); // 返回 false ，因为没有空的小车位
        parkingSystem.addCar(1); // 返回 false ，因为没有空的大车位，唯一一个大车位已经被占据了
    }
}
