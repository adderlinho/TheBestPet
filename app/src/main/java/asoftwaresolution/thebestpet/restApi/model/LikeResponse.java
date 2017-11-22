package asoftwaresolution.thebestpet.restApi.model;

/**
 * Created by AdderlyS on 21/11/2017.
 */

public class LikeResponse {
    private String data;
    private String code;

    public LikeResponse(String data, String code) {
        this.data = data;
        this.code = code;
    }

    public LikeResponse() {
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
