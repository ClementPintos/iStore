package Model;

public class Store {

    private final int storeId;
    private final String storeName;

    public Store(int storeId, String storeName) {
        this.storeId = storeId;
        this.storeName = storeName;
    }

    public int getStoreId() {
        return storeId;
    }
    public String getStoreName() {
        return storeName;
    }

}
