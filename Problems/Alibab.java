/*
3.30 阿里巴巴两道面试算法题
 */
public class Alibaba  {
/**
 * 问题一：
 * 小强有n个养鸡场，弟i个养鸡场初始有a[i]只小鸡。与其他养鸡场不同的是，他的养鸡场每天增加k只小鸡，
 * 小强每天结束都会在数量最多的养鸡场里卖掉一半的小鸡，假如一个养鸡场有x只鸡，则卖出后只剩下x/2(向下取整)只鸡。问m天后小强的n个养鸡场一共多少只小鸡？
 * 输入 第一行输入三个int类型n,m,k（1 <= n,m,k <= 10^6） 第二行输入n个正整数，表示n个养鸡场初始鸡的个数
 * 输出 输出一个整数表示鸡的总数
 *
 * 例：
 * 输入：
 * 3 3 100
 * 100 200 400
 * 输出：
 * 925
 *
 * 方法：
 * 优先队列
 */
    public int solve1(int[] nums, int m, int k) {
        PriorityQueue<Integer> q = new PriorityQueue<>();
        int sum = 0;
        for(int num: nums) {
            q.offer(nums);
            sum += num;
        }
        //base为底，来模拟第n天增加的鸡的数量，不需要每次实际对对内数据进行增加
        int base = 0;
        for(int i = 0; i < m; i++) {
            int max = q.poll();
            base += k;
            max += base;
            int reduction = (max + 1) / 2;
            q.offer(max - reduction - base);
            sum -= reduction;
        }
        return sum + nums.length * base;
    }

/**
 * 问题二：
 * 小强得到了长度为n的序列，但他只对非常大的数字感兴趣，因此随机选择这个序列的一个连续子序列，并求这个序列的最大值，请告诉他这个最大值的期望是多少?
 * 输入 第一行n表示序列长度接下来一行n个数描述这个序列，n大于等于1小于等于1000000，数字保证是正整数且不超过100000 第二行n个数字表示序列的值
 *
 * 例：
 * 输入：1 2 3
 * 输出：2.333333
 *
 * 方法：
 * 单调栈
 */

    public double solve2(int[] nums) {
        if(nums.length == 1) {
            return nums[0]*1.0;
        }
        LinkedList<Integer> s = new LinkedList<>();
        s.push(-1);
        int count = nums.length * (nums.length + 1) / 2;
        double res = 0.0;
        for(int i = 0; i <= nums.length; i++) {
            while(s.size() > 1 && (i == nums.length || nums[s.peek()] < nums[i] )) {
                int index = s.pop();
                int max = nums[index];
                int l = s.peek();
                int r = i;
                res += max * (index - l) * (r - index);
            }
            s.push(i);
        }
        return res / count;
    }
}