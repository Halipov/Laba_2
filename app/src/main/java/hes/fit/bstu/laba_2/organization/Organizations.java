package hes.fit.bstu.laba_2.organization;

public enum Organizations {
itechart("ITechArt");
    private String title;
    Organizations(String company) {this.title = company;

    }

    public String getTitle() {
        return title;
    }
}
