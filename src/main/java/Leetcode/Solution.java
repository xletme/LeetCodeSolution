package Leetcode;



import java.util.*;

/**
 * @Description TODO
 * @Author xiamoxin
 * @Date 2020/8/7 15:04
 **/
public class Solution {

    private static Solution solution = new Solution();
    private HashMap<Node,Node> visited = new HashMap<>();

    public boolean isHappy(int n) {
        //思路 1.拆分n为个位数  2.每个数平方相加的和 和 0 对比 ,返回true or 继续拆分+求平方和 递归
        HashSet<Integer> set = new HashSet<>();
        while(n!= 1 && !set.contains(n)){
            set.add(n);
            n = getNext(n);
        }

        return n == 1;
    }

    public int getNext(int n){
        int sum = 0;
        while(n > 0){
           int k =  n % 10;
           sum += k * k;
           n = n /10;
        }
        return sum;
    }

    //所在位置的 元素值 + 下标  不超过   元素为0的下标
    //找出元素为0的元素依次遍历
    //官方解答
    /*解题思路：
    如果某一个作为 起跳点 的格子可以跳跃的距离是 3，那么表示后面 3 个格子都可以作为 起跳点。
    可以对每一个能作为 起跳点 的格子都尝试跳一次，把 能跳到最远的距离 不断更新。
    如果可以一直跳到最后，就成功了。*/
    public boolean canJump(int[] nums) {
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if(i > k ){
                return false;
            }
            k = Math.max(k, nums[i] + i);
        }
        return true;
    }

    //nums1 nums2 0元素去除，nums2合并到nums1
    public void merge(int[] nums1, int m, int[] nums2, int n) {
       System.arraycopy(nums2,0,nums1,m,n);
       Arrays.sort(nums1);
    }

    /**
     关键是搞清楚格雷编码的生成过程, G(i) = i ^ (i/2);
     如 n = 3:
     G(0) = 000,
     G(1) = 1 ^ 0 = 001 ^ 000 = 001   1
     G(2) = 2 ^ 1 = 010 ^ 001 = 011   3
     G(3) = 3 ^ 1 = 011 ^ 001 = 010   2
     G(4) = 4 ^ 2 = 100 ^ 010 = 110   6
     G(5) = 5 ^ 2 = 101 ^ 010 = 111   7
     G(6) = 6 ^ 3 = 110 ^ 011 = 101   5
     G(7) = 7 ^ 3 = 111 ^ 011 = 100   4
     **/
    public List<Integer> grayCode(int n) {
        List<Integer> grayList = new ArrayList<>();
        for(int i=0;i< 1<<n;++i){
            grayList.add(i ^ i>>1);
        }
        return grayList;
    }

    /***
     *@描述 X X O O矩阵
     * 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的
     * 1.找到所有边界的O，一次循环遍历，找与他们相邻的O
     * （边界 ：
     * arr[0][0]---arr[0]arr[m - 1]
     * arr[n-1][0]---arr[n-1][m-1]
     * arr[1][0]----arr[n-2][0]
     * arr[1][m-1]----arr[n-2][m-1]） 长 宽 m,n 适用于 大于 2*2的矩阵；2*2不变
     * 找相邻的
     * a.i = 0 j = 0 [0][1] [1][0] /
     * b.i = 0 j = m [0][m-1] [1][m]/
     * c.i = n j = 0 [n-1]0[] [n][1]/
     * d.i = n j = m [n][m-1] [n-1][m]/
     * e.i = 0 0<j<m [0][j-1] [0][j+1] [1][j]/
     * f.0<i<n  j=0 [i-1][0] [i+1][0] [i][1]/
     * g.i = n 0<j<m [n][j-1] [n][j+1] [n-1][j]/
     * h.0<i<n j=m [i-1][j] [i+1][j] [i][j-1]/
     * i.0<i<n && 0<j<m [i][j-1] [i][j+1] [i-1][j] [i+1][j]
     * 2.再次遍历新增的O 找与之相邻的O
     * 3.递归重复第二步骤
     * 4.记住所有O的下标，其他的都为X
     *@参数 [board]
     *@返回值 void
     *@创建人 xiamaoxin
     *@创建时间 2020/8/11
     */
    public void solve(char[][] board) {
        int n = board.length;//row
        if (n == 0) {
            return;
        }
        int m = board[0].length;//column
        for (int i = 0; i < n; i++) {
            dfs(board, i, 0,m,n);
            dfs(board, i, m - 1,m,n);
        }
        for (int i = 1; i < m - 1; i++) {
            dfs(board, 0, i,m,n);
            dfs(board, n - 1, i,m,n);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'A') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++) {
                System.out.print(board[i][j]);
                System.out.print("\t");
            }
            System.out.println();
        }
    }

    public void findBorder(Map<Integer, Integer> position,char[][] board,int m,int n){
            for(Map.Entry<Integer, Integer> entry :position.entrySet()){
                int i = entry.getKey();
                int j = entry.getValue();
                if(i==0 && j>0 && j<m-1){
                    if(board[0][j-1] == 'O'){
                        position.put(0,j-1);
                    }
                    if(board[0][j-1] == 'O'){
                        position.put(0,j+1);
                    }
                    if(board[1][j] == 'O'){
                        position.put(1,j);
                    }
                    continue;
                }

                if(i > 0&& i< n-1 && j==0){
                    //[i-1][0] [i+1][0] [i][1]
                    if(board[i-1][0] == 'O'){
                        position.put(i-1,0);
                    }
                    if(board[i+1][0] == 'O'){
                        position.put(i+1,0);
                    }
                    if(board[i][1] == 'O'){
                        position.put(i,1);
                    }
                    continue;
                }

                if(i == n-1 && j > 0 && j<m-1){
                    //[n][j-1] [n][j+1] [n-1][j]
                    if(board[n-1][j-1] == 'O'){
                        position.put(n-1,j-1);
                    }
                    if(board[n-1][j+1] == 'O'){
                        position.put(n-1,j+1);
                    }
                    if(board[n-1][j] == 'O'){
                        position.put(n-1,j);
                    }
                    continue;
                }

                if(i>0 && i<n-1 && j==m-1){
                    //[i-1][j] [i+1][j] [i][j-1]
                    if(board[i-1][j] == 'O'){
                        position.put(i-1,j);
                    }
                    if(board[i+1][j] == 'O'){
                        position.put(i+1,j);
                    }
                    if(board[i][j-1] == 'O'){
                        position.put(i,j-1);
                    }
                    continue;
                }

                if(i>0 && i<n-1 && j>0 && j<m-1 ){
                    //[i][j-1] [i][j+1] [i-1][j] [i+1][j]
                    if(board[i][j-1] == 'O'){
                        position.put(i,j-1);
                    }
                    if(board[i][j+1] == 'O'){
                        position.put(i,j+1);
                    }
                    if(board[i-1][j] == 'O'){
                        position.put(i-1,j);
                    }
                    if(board [i+1][j] == 'O'){
                        position.put(i+1,j);
                    }
                    continue;
                }

            }
    }

    private void dfs(char[][] board,int x,int y,int m,int n){
        if(x < 0 || x>n-1 || y<0||y>m-1||board[x][y] != 'O'){
            return;
        }
        board[x][y] = 'A';
        dfs(board,x+1,y,m,n);
        dfs(board,x-1,y,m,n);
        dfs(board,x,y+1,m,n);
        dfs(board,x,y-1,m,n);
    }

    public int[] sort(int[] sourceArray) throws Exception {
             // 对 arr 进行拷贝，不改变参数内容
              int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

               // 从下标为1的元素开始选择合适的位置插入，因为下标为0的只有一个元素，默认是有序的
                for (int i = 1; i < arr.length; i++) {

                        // 记录要插入的数据
                       int tmp = arr[i];

                        // 从已经排序的序列最右边的开始比较，找到比其小的数
                        int j = i;
                      while (j > 0 && tmp < arr[j - 1]) {
                                arr[j] = arr[j - 1];
                               j--;
                           }

                        // 存在比其小的数，插入
                       if (j != i) {
                                arr[j] = tmp;
                           }

                   }
              return arr;
           }

    int countPrimes(int n) {
        boolean[] isPrim = new boolean[n];
        Arrays.fill(isPrim, true);
        for (int i = 2; i * i < n; i++)
            if (isPrim[i])
                for (int j = i * i; j < n; j += i)
                    isPrim[j] = false;

        int count = 0;
        for (int i = 2; i < n; i++)
            if (isPrim[i]) count++;

        return count;
    }

    /*public int removeElement(int[] nums, int val) {
        List<Integer> collect = Arrays.stream(nums).filter(k -> k != val).boxed().collect(Collectors.toList());
        int[] arr=new int[collect.size()];
        for(int i=0;i<collect.size();i++){
            arr[i] = collect.get(i);
        }

        return nums.length;
    }*/
    public int removeElement(int[] nums, int val) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }

    public int strStr(String haystack, String needle) {
        int i = 0;
        if(!haystack.contains(needle)){
            i = -1;
        }else {
            i = haystack.indexOf(needle);
        }
        return i;
    }



    public boolean isPrime(int n){
        if(n == 1){
            return false;
        }
        if(n == 2){
            return true;
        }
        for (int i = 2; i < n-1; i++) {
            if(n % i == 0){
                return false;
            }
        }
        return true;
    }

    //除数为负一 特殊处理  左移右移操作
    public int divide(int dividend, int divisor) {
        if (dividend == 0) {
            return 0;
        }
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        boolean negative;
        negative = (dividend ^ divisor) <0;//用异或来计算是否符号相异
        long t = Math.abs((long) dividend);
        long d= Math.abs((long) divisor);
        int result = 0;
        for (int i=31; i>=0;i--) {
            if ((t>>i)>=d) {//找出足够大的数2^n*divisor
                result+=1<<i;//将结果加上2^n
                t-=d<<i;//将被除数减去2^n*divisor
            }
        }
        return negative ? -result : result;//符号相异取反
    }

    //节点数不超过 100 。
    //每个节点值 Node.val 都是唯一的，1 <= Node.val <= 100。
    //无向图是一个简单图，这意味着图中没有重复的边，也没有自环。
    //由于图是无向的，如果节点 p 是节点 q 的邻居，那么节点 q 也必须是节点 p 的邻居。
    //图是连通图，你可以从给定节点访问到所有节点。
    public Node cloneGraph(Node node) {
        if(node == null){
            return node;
        }

        if(visited.containsKey(node)){
            return visited.get(node);
        }

        Node cloneNode = new Node(node.val,new ArrayList<>());

        visited.put(node,cloneNode);

        for(Node neighor : node.neighbors){
            cloneNode.neighbors.add(cloneGraph(neighor));
        }

        return cloneNode;
    }

    public String defangIPaddr(String address) {
        return address.replace(".","[.]");
    }

    //输入：bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5
    //输出：[10,55,45,25,25]
    //请你返回一个长度为 n 的数组 answer，按航班编号顺序返回每个航班上预订的座位数。
    /***
     *@描述 1.定义返回数组 arr[]，初始化 size为 n
     *  2.依次遍历bookings bookings[i] 根据 i j取 k的值放入 arr
     * 3.条件判断 n ==0 1 <= bookings.length <= 20000
     * 1 <= bookings[i][0] <= bookings[i][1] <= n <= 20000
     * 1 <= bookings[i][2] <= 10000
     *
     * 来源：力扣（Leetcode）
     * 链接：https://leetcode-cn.com/problems/corporate-flight-bookings
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *@参数 [bookings, n]
     *@返回值 int[]
     *@创建人 xiamaoxin
     *@创建时间 2020/8/20
     */
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] arr =new int[n];
        int length = bookings.length;
        for(int i=0;i<length;i++){
            int i1 = bookings[i][0];
            int i2 = bookings[i][1];
            int num = bookings[i][2];
            for(int j=i1-1;j<=i2-1;j++){
                arr[j]+=num;
            }
        }
        return arr;
    }

    /***
     *@描述 给定一个Excel表格中的列名称，返回其相应的列序号。
     * TODO 规则：一位数  X-A +1  两位数  (X1-A +1)*26+(X2-A+1) 重点在于寻找规律
     *@参数 [s]
     *@返回值 int
     *@创建人 xiamaoxin
     *@创建时间 2020/8/21
     */
    public int titleToNumber(String s) {
        int num = 0;
        for(int i=0;i<s.length();i++){
            int temp = s.charAt(i) -'A' +1;
            num = num * 26 +temp;
        }
        return num;
    }

    /***
     *@描述 返回n！尾数0的数量
     * 思路：先计算n! ，在计算0的数量 TODO 定义flag，对10取余，成功 flag++,且除以10 递归直到对10取余不为0
     *@参数 [n]
     *@返回值 int
     *@创建人 xiamaoxin
     *@创建时间 2020/8/21
     */
    public int trailingZeroes(int n) {
        long num = recursion(n);
        int flag = 0;
        return calculateFlag(flag,num);
    }

    public static int calculateFlag(int flag,long num){
        if (num % 10 == 0) {
            flag++;
            num /= 10;
            flag = calculateFlag(flag, num);
        }
        return flag;
    }

    public static long recursion(long num){//利用递归计算阶乘
        if(num == 1){
            return 1;//根据条件,跳出循环
        }else{
            return num * recursion(num-1);//运用递归计算
        }
    }

    public boolean divisorGame(int N) {
            return (N & 1) == 0;
    }

    public int[] sortedSquares(int[] A) {
        return Arrays.stream(A).map(Math::abs).map(item -> item* item).sorted().toArray();
    }

    //求全排列   情况nums!  数组个数，每次循环 (nums-1)! 共循环nums 次
    public List<List<Integer>> permute(int[] nums) {
       int len = nums.length;
       List<List<Integer>> res = new ArrayList<>();
       if(len == 0){
           return res;
       }

       boolean[] used = new boolean[len];
       Deque<Integer> path = new ArrayDeque<>();
       dfs(nums,len,0,path,used,res);
       return res;
    }

    private void dfs(int[] nums,int len,int depth,Deque<Integer> path,boolean[] used,List<List<Integer>> res){
        if(depth == len){
            res.add(new ArrayList<>(path));
            return;
        }

        for(int i=0;i<len;i++){
            if(!used[i]){
                path.addLast(nums[i]);
                used[i] = true;

                System.out.println("<<<递归前："+path);
                dfs(nums,len,depth+1,path,used,res);

                used[i] = false;
                path.removeLast();
                System.out.println("<<<递归之后:"+path);
            }
        }
    }

    //找出他们共同喜爱的餐厅。 如果答案不止一个，则输出所有答案并且不考虑顺序 至少存在一个答案
    //官方思路：建立一个 hashMap存储数据,key为索引和 值为索引和对应的字符串 遍历list1 list2
    public String[] findRestaurant(String[] list1, String[] list2) {
        Map<Integer,List<String>> commonMap = new HashMap<>();
        for (int i = 0; i < list1.length; i++) {
            for (int i1 = 0; i1 < list2.length; i1++) {
               if(list1[i].equals(list2[i1])){
                   if(commonMap.containsKey(i+i1)){
                       commonMap.get(i+i1).add(list1[i]);
                   }else {
                       ArrayList<String> index = new ArrayList<>();
                       index.add(list1[i]);
                       commonMap.put(i+i1,index);
                   }
               }
            }
        }
        int min = Integer.MAX_VALUE;
        for(Map.Entry<Integer,List<String>> entry : commonMap.entrySet()){
            min = Math.min(min,entry.getKey());
        }
        String[] result = new String[commonMap.get(min).size()];
        return commonMap.get(min).toArray(result);
    }

    //思路：先求出每个元素的个数，放入map  map1 map2 key->元素值 value->个数
    // 遍历map1 map2,如果map2 中的 value > map1中的value 则就是子集,放入到list 最后转为数组
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer,Integer> map1 = new HashMap<>();
        Map<Integer,Integer> map2 = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums1.length; i++) {
            if(map1.containsKey(nums1[i])){
                map1.put(nums1[i], map1.get(nums1[i])+1);
            }else {
                map1.put(nums1[i],1);
            }
        }
        for (int i = 0; i < nums2.length; i++) {
            if(map2.containsKey(nums2[i])){
                map2.put(nums2[i], map2.get(nums2[i])+1);
            }else {
                map2.put(nums2[i],1);
            }
        }

        for( Map.Entry<Integer, Integer> entry1 : map1.entrySet()){
            for(Map.Entry<Integer, Integer> entry2 : map2.entrySet()){
                if(entry2.getKey().equals(entry1.getKey()) ){
                    int min = Math.min(entry1.getValue(),entry2.getValue());
                    for(int i=0;i<min;i++) {
                        result.add(entry1.getKey());
                    }
                }
            }
        }
        return result.stream().mapToInt(x->x).toArray();
    }

    //找到缺少的正整数
    //思路：找到arr 缺失的元素，把位置和元素存入 map map.get(k)
    //arr[i]-i-1=0  k+i
    public int findKthPositive(int[] arr, int k) {
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]-i-1 >= k){
                return k+i;
            }
        }
        return k+arr.length;
    }

    //判断回文字符串
    //字符串中字母个数为 奇数 的大于1个，false 否则为true   存储hashMap 奇数存false，偶数存true 最后判断map中false的个数是否大于1
    public boolean canPermutePalindrome(String s) {
        char[] chars = s.toCharArray();
        Map<Boolean,Integer> map = new HashMap<>();
        List<Character> car = new ArrayList<>();
        for (int i = 0; i < chars.length; i++) {
            if(car.contains(chars[i])){
                continue;
            }
            int count = count(chars[i], chars);
            if(count % 2 != 0){
                if(!map.containsKey(false)){
                    map.put(false,1);
                }else {
                    map.put(false,map.get(false)+1);
                }
            }
            car.add(chars[i]);
        }
        if(map.size() == 0){
            return true;
        }
        return map.get(false) <= 1;
    }

    private int count(char a,char[] chars){
        int count=0;
        for (int i = 0; i < chars.length; i++) {
            if(chars[i] == a){
                count++;
            }
        }
        return count;
    }

    //最长回文字符串  先去重字符串，获取对应每个字符串的个数，偶数加起来 再加上最大的奇数
    public int longestPalindrome(String s) {
/*        int sum =0;
        int reduce = 0;
        String tmpStr = ARFA3(s);
        char[] chars = s.toCharArray();
        char[] tmpChars = tmpStr.toCharArray();
        for (int i = 0; i < tmpChars.length; i++) {
            int count = count(tmpChars[i], chars);
            if(count % 2 == 0){
                sum+=count;
            }else {
                reduce++;
                sum+=count;
            }
        }
        return reduce != 0 ?sum-reduce+1 : sum;*/
        int[] count = new int[128];
        for (char c: s.toCharArray())
            count[c]++;

        int ans = 0;
        for (int v: count) {
            // v/2 np   v %2==1 && ans%2==0 np
            ans += v / 2 * 2;
            if (v % 2 == 1 && ans % 2 == 0)
                ans++;
        }
        return ans;
    }

    private  String ARFA3(String str) {
        StringBuffer sb = new StringBuffer(str);
        String rs = sb.reverse().toString().replaceAll("(.)(?=.*\\1)", "");
        StringBuffer out = new StringBuffer(rs);
        return out.reverse().toString();
    }

    public String longestWord(String[] words) {
        String ans = "";
        Set<String> wordset = new HashSet();
        for (String word: words) wordset.add(word);
        for (String word: words) {
            if (word.length() > ans.length() ||
                    word.length() == ans.length() && word.compareTo(ans) < 0) {
                boolean good = true;
                for (int k = 1; k < word.length(); ++k) {
                    if (!wordset.contains(word.substring(0, k))) {
                        good = false;
                        break;
                    }
                }
                if (good) ans = word;
            }
        }
        return ans;
    }

    public  String Decimal2Binary(int de){
        String numstr = "";
        while (de>0){
            int res = de%2; //除2 取余数作为二进制数
            numstr = res + numstr;
            de = de/2;
        }
        return  numstr;
    }


    public static void main(String[] args) {
        System.out.println(solution.Decimal2Binary(10));
    }


}
