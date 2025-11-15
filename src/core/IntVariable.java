package core;

public class IntVariable {
    private Domain domain;
    private String name;

    public IntVariable(Domain domain, String name) {
        this.domain = domain;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Domain getDomain() {
        return domain;
    }
}
