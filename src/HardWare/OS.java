package HardWare;

public enum OS {
    Windows10HomeSL ("Windows 10 Home Single Language"),
    Windows10Home ("Windows 10 Home"),
    Windows10Pro ("Windows 10 Professional"),
    Windows11HomeSL("Windows 11 Home Single Language"),
    Windows11Pro("Windows 11 Professional"),
    Linux("Linux"),
    Ubuntu("Ubuntu"),
    OSX("HardWare.OS X"),
    DOS("DOS"),
    NoOS("No OS");

    private String osName;

    OS (String osName){
        this.osName = osName;
    }

    public String getOsName() {
        return osName;
    }
}
