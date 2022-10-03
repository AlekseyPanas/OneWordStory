package usecases.submit_name;

public class snInputData {
    private String displayName;

    public snInputData (String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
