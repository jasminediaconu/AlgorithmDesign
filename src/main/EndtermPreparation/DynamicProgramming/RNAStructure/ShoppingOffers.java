package main.EndtermPreparation.DynamicProgramming.RNAStructure;

import java.util.ArrayList;
import java.util.List;

/**
 * In LeetCode Store, there are some kinds of items to sell. Each item has a price.
 *
 * However, there are some special offers, and a special offer consists of one or more different kinds of
 * items with a sale price.
 *
 * You are given the each item's price, a set of special offers, and the number we need to buy for each item.
 * The job is to output the lowest price you have to pay for exactly certain items as given,
 * where you could make optimal use of the special offers.
 *
 * Each special offer is represented in the form of an array, the last number represents
 * the price you need to pay for this special offer, other numbers represents how many specific items
 * you could get if you buy this offer.
 *
 * You could use any of special offers as many times as you want.
 */
public class ShoppingOffers {
    int minPrice = 0;

    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        int n = price.size();
        List<Item> items = new ArrayList<>();

        // Initialize price without offers
        for(int i = 0; i < n; i++) items.add(new Item(price.get(i), needs.get(i)));

        minPrice = buyItems(items);

        findMinimum(items, special, 0, 0);

        return minPrice;
    }

    public int buyItems(List<Item> items) {
        int n = items.size();
        int total = 0;
        for(int i = 0; i < n; i++) {
            total += items.get(i).price * items.get(i).quantity;
        }

        return total;
    }

    public void findMinimum(List<Item> items, List<List<Integer>> offers, int totalPrice, int index) {
        // Base case: the offer doesn't give a better price
        if(totalPrice >= minPrice) return;

        // If there are no more offers to use, buy left items without discount
        if(index == offers.size()) {
            totalPrice += buyItems(items);
            minPrice = totalPrice < minPrice ? totalPrice : minPrice;

            return;
        }

        // Recursive step: get the current offer
        List<Integer> offer = offers.get(index);

        // For each item check if the quantity is <= than the quantity we need to buy
        boolean canBeUsed = true;

        for(int i = 0; i < items.size(); i++) {
            if(offer.get(i) > items.get(i).quantity) {
                canBeUsed = false;
                break;
            }
        }

        if(canBeUsed) {
            for(int i = 0; i < items.size(); i++) items.get(i).setQuantity(offer.get(i));

            totalPrice += offer.get(offer.size()-1);

            findMinimum(items, offers, totalPrice, index);
        }
        else findMinimum(items, offers, totalPrice, index+1);

    }

}

class Item {

    int price;
    int quantity;

    public Item(int price, int quantity) {
        this.price = price;
        this.quantity = quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity -= quantity;
    }
}