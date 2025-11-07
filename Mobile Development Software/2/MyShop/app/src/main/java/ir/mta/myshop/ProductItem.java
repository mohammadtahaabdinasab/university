package ir.mta.myshop;

public class ProductItem {
    private final int imageResId;
    private final String title;
    private final String subtitle;
    private boolean active;

    public ProductItem(int imageResId, String title, String subtitle, boolean active) {
        this.imageResId = imageResId;
        this.title = title;
        this.subtitle = subtitle;
        this.active = active;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}

