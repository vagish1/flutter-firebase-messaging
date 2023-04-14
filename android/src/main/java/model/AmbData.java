package model;


public  class AmbData {
    AmbResult result;
    private int responseCode;


    // Getter Methods

    public AmbResult getResult() {
        return result;
    }

    public int getResponseCode() {
        return responseCode;
    }

    // Setter Methods

    public void setResult(AmbResult resultObject) {
        this.result = resultObject;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }
}