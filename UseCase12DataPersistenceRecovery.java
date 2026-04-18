import java.io.*;
import java.util.HashMap;
import java.util.Map;

class RoomInventory {

    private Map<String, Integer> roomAvailability;

    public RoomInventory() {
        roomAvailability = new HashMap<>();

        roomAvailability.put("Single", 2);
        roomAvailability.put("Double", 2);
        roomAvailability.put("Suite", 1);
    }

    public Map<String, Integer> getRoomAvailability() {
        return roomAvailability;
    }

    public void updateAvailability(String roomType, int count) {
        roomAvailability.put(roomType, count);
    }
}

class FilePersistenceService {

    public void saveInventory(RoomInventory inventory, String filePath) {

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(filePath))) {

            Map<String, Integer> availability =
                    inventory.getRoomAvailability();

            for (Map.Entry<String, Integer> entry : availability.entrySet()) {
                writer.write(entry.getKey() + "=" + entry.getValue());
                writer.newLine();
            }

            System.out.println("Inventory saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving inventory: " + e.getMessage());
        }
    }

    public void loadInventory(RoomInventory inventory, String filePath) {

        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("No valid inventory data found. Starting fresh.");
            return;
        }

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(filePath))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split("=");

                if (parts.length == 2) {
                    String roomType = parts[0];
                    int count = Integer.parseInt(parts[1]);

                    inventory.updateAvailability(roomType, count);
                }
            }

            System.out.println("Inventory loaded successfully.");

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading inventory. Starting fresh.");
        }
    }
}

public class UseCase12DataPersistenceRecovery {

    public static void main(String[] args) {

        System.out.println("======================================");
        System.out.println("System Recovery");
        System.out.println("======================================");

        String filePath = "inventory.txt";

        RoomInventory inventory = new RoomInventory();
        FilePersistenceService persistenceService =
                new FilePersistenceService();

        // Load previous inventory data
        persistenceService.loadInventory(inventory, filePath);

        System.out.println("\nCurrent Inventory:");
        System.out.println("Single: " +
                inventory.getRoomAvailability().get("Single"));
        System.out.println("Double: " +
                inventory.getRoomAvailability().get("Double"));
        System.out.println("Suite: " +
                inventory.getRoomAvailability().get("Suite"));

        // Save inventory again
        persistenceService.saveInventory(inventory, filePath);

        System.out.println("\nUC12 Data Persistence completed...");
    }
}