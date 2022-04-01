package ust.tad.modelsservice.shared;

import java.net.URL;
import java.util.Objects;

public class Location {
    private URL url;
    private int startLineNumber;
    private int endLineNumber;    

    public Location() {
    }

    public Location(URL url, int startLineNumber, int endLineNumber) {
        this.url = url;
        this.startLineNumber = startLineNumber;
        this.endLineNumber = endLineNumber;
    }

    public URL getUrl() {
        return this.url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public int getStartLineNumber() {
        return this.startLineNumber;
    }

    public void setStartLineNumber(int startLineNumber) {
        this.startLineNumber = startLineNumber;
    }

    public int getEndLineNumber() {
        return this.endLineNumber;
    }

    public void setEndLineNumber(int endLineNumber) {
        this.endLineNumber = endLineNumber;
    }

    public Location url(URL url) {
        setUrl(url);
        return this;
    }

    public Location startLineNumber(int startLineNumber) {
        setStartLineNumber(startLineNumber);
        return this;
    }

    public Location endLineNumber(int endLineNumber) {
        setEndLineNumber(endLineNumber);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Location)) {
            return false;
        }
        Location location = (Location) o;
        return Objects.equals(url, location.url) && startLineNumber == location.startLineNumber && endLineNumber == location.endLineNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, startLineNumber, endLineNumber);
    }

    @Override
    public String toString() {
        return "{" +
            " url='" + getUrl() + "'" +
            ", startLineNumber='" + getStartLineNumber() + "'" +
            ", endLineNumber='" + getEndLineNumber() + "'" +
            "}";
    }
}
