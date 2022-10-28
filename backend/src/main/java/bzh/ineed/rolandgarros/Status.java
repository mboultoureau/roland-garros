package bzh.ineed.rolandgarros;

public class Status {
    private final long id;
    private final String version;
    private final String status;

    public Status(long id, String version, String status) {
        this.id = id;
        this.version = version;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public String getVersion() {
        return version;
    }

    public String getStatus() {
        return status;
    }
}
