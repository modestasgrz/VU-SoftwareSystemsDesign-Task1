package GivenUnitTests;

public class CountryRule {
    public CountryRule() {}

    public CountryRule(String country, int lengthOfPhoneNumber, String prefix) {
        this.country = country;
        this.lengthOfPhoneRule = lengthOfPhoneNumber;
        this.prefix = prefix;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getLengthOfPhoneRule() {
        return lengthOfPhoneRule;
    }

    public void setLengthOfPhoneRule(int lengthOfPhoneRule) {
        this.lengthOfPhoneRule = lengthOfPhoneRule;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    private String country;
    private int lengthOfPhoneRule;
    private String prefix;
}
