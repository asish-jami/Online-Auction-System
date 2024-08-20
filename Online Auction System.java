import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
class Bid {
    private String bidderName;
    private double amount;
    public Bid(String bidderName, double amount) {
        this.bidderName = bidderName;
        this.amount = amount;
    }
    public String getBidderName() {
        return bidderName;
    }
    public double getAmount() {
        return amount;
    }
}
class AuctionItem {
    private String itemName;
    private double startingPrice;
    private List<Bid> bids;
    public AuctionItem(String itemName, double startingPrice) {
        this.itemName = itemName;
        this.startingPrice = startingPrice;
        this.bids = new ArrayList<>();
    }
    public void placeBid(String bidderName, double bidAmount) {
        if (bidAmount > getHighestBid()) {
            bids.add(new Bid(bidderName, bidAmount));
            System.out.println("Bid placed successfully by " + bidderName + " for $" + bidAmount);
        } else {
            System.out.println("Bid amount is lower than the current highest bid.");
        }
    }
    public double getHighestBid() {
        if (bids.isEmpty()) {
            return startingPrice;
        } else {
            return bids.get(bids.size() - 1).getAmount();
        }
    }
    public String getHighestBidder() {
        if (bids.isEmpty()) {
            return "No bids yet";
        } else {
            return bids.get(bids.size() - 1).getBidderName();
        }
    }
    public void displayBids() {
        if (bids.isEmpty()) {
            System.out.println("No bids placed yet.");
        } else {
            System.out.println("Bids placed on " + itemName + ":");
            for (Bid bid : bids) {
                System.out.println(bid.getBidderName() + " bid $" + bid.getAmount());
            }
        }
    }
}
public class AuctionSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Online Auction System!");
        System.out.print("Enter the item name for auction: ");
        String itemName = scanner.nextLine();
        System.out.print("Enter the starting price of the item: ");
        double startingPrice = scanner.nextDouble();
        scanner.nextLine();  // Consume newline left-over
        AuctionItem item = new AuctionItem(itemName, startingPrice);
        boolean auctionOpen = true;
        while (auctionOpen) {
            System.out.println("\nOptions: ");
            System.out.println("1. Place a bid");
            System.out.println("2. View current highest bid");
            System.out.println("3. View all bids");
            System.out.println("4. End auction");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline left-over
            switch (choice) {
                case 1:
                    System.out.print("Enter your name: ");
                    String bidderName = scanner.nextLine();
                    System.out.print("Enter your bid amount: ");
                    double bidAmount = scanner.nextDouble();
                    item.placeBid(bidderName, bidAmount);
                    break;
                case 2:
                    System.out.println("Current highest bid: $" + item.getHighestBid() + " by " + item.getHighestBidder());
                    break;
                case 3:
                    item.displayBids();
                    break;
                case 4:
                    auctionOpen = false;
                    System.out.println("Auction ended! The highest bid was $" + item.getHighestBid() + " by " + item.getHighestBidder());
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
}
