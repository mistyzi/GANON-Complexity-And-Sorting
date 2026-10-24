package appDomain;

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