package appDomain;

/**
 * Holds the settings parsed from the command line: the data file to load,
 * how the products should be compared, and which sorting algorithm to run.
 * <p>
 * This is a simple data holder. It performs no validation of its own; that is
 * done by {@link CommandLineParser} before the values are stored here.
 * </p>
 */

public class AppConfig {
    private String fileName;
    private char compareType;
    private char sortAlgorithm;

    public String getFileName() {
    	return fileName;
    }
    public void setFileName(String fileName) {
		this.fileName = fileName;
	}

    public char getCompareType() {
		return compareType;
	}
    
    public void setCompareType(char compareType) {
		this.compareType = compareType;
	}

    public char getSortAlgorithm() {
		return sortAlgorithm;
	}
    
    public void setSortAlgorithm(char sortAlgorithm) {
		this.sortAlgorithm = sortAlgorithm;
	}
}