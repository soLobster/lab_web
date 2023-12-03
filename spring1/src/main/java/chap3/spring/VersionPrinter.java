package chap3.spring;

public class VersionPrinter {

    private int majorVersion;
    private int minorVersion;

    public void print() {
        System.out.printf("this program Version >> %d,%d \n\n", majorVersion, minorVersion);
    }

    public void setMajorVersion(int majorVersion) {
        this.majorVersion = majorVersion;
    }
    
    public void setMinorVersion(int minorVersion) {
        this.minorVersion = minorVersion;
    }
    
}