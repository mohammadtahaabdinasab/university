package ir.mta.openlinker;

public class SearchEngine {
    private String name;
    private String url;

    public SearchEngine(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() { return name; }
    public String getUrl() { return url; }

    public void setName(String name) { this.name = name; }
    public void setUrl(String url) { this.url = url; }
}
