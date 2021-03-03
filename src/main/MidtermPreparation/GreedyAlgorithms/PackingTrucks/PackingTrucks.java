package main.MidtermPreparation.GreedyAlgorithms.PackingTrucks;

/**
 * As the owner of a shipping company, you won a large contract requiring you to ship a certain amount of boxes
 * n to a certain destination. The contract gives the weight of the boxes in order of their arrival.
 * This is also the order in which the boxes must be shipped. To maximize profit, you want to minimize the number
 * of trucks used. Each truck can carry boxes up to a total of weight W. Every box i with 1≤i≤n has a weight wi.
 *
 * Implement an algorithm that determines the minimal amount of trucks needed to ship the boxes to the destination.
 *
 * If we can carry at most 48 weight units in one truck (W=48) then the following shipment of boxes should result
 * in an output of 3.
 */
public class PackingTrucks {
    public static /**
     * @param n the number of packages
     * @param weights the weights of all packages 1 through n. Note that weights[0] should be ignored!
     * @param maxWeight the maximum weight a truck can carry
     * @return the minimal number of trucks required to ship the packages _in the given order_.
     */
    int minAmountOfTrucks(int n, int[] weights, int maxWeight) {
        int weight = 0;
        int trucks = 0;

        for(int i = 1; i <= n; i++) {
            if(weight + weights[i] <= maxWeight) {
                weight += weights[i];
            }
            else {
                weight = weights[i];
                trucks++;
            }
        }

        if(weight == 0) return trucks;

        return trucks + 1;
    }

}
