package Q2;

/**
 * [2번 문제]
 */
public class Coin {

    int sum;
    int[] coins;

    /**
     * tatalCases[index] = 주어진 동전으로 합이 index가 되는 조합
     */
    int[] totalCases;

    public Coin(int sum, int[] coins){
        this.sum = sum;
        this.coins = coins;
        totalCases = new int[sum+1];
        totalCases[0] = 1;
    }

    /**
     * 경우의 수 구하기
     *
     * totalCases의 index는 만들고자 하는 합산 금액이므로
     * j가 coins[i] 이상일 때, totalCases[j]의 값이 갱신됩니다.
     *
     * ex)
     * 입력: sum = 4, coins[] = {1,2,3},
     * 출력: 4
     *
     * totalCases[3], coins[2]
     * => 1 짜리 동전으로 합 3을 만들 수 있는 경우의 수
     * + (2로 합 3을 만들 수 있는 경우의 수 + 1과 2로 합 3을 만들 수 있는 경우의 수)
     *
     * @return
     */
    public int numberOfCases(){
        for(int i=0; i<coins.length; i++){
            for(int j=1; j<=sum; j++){
                if(j >= coins[i])
                    totalCases[j] += totalCases[j-coins[i]];
            }
        }

        return totalCases[totalCases.length-1];
    }
}
