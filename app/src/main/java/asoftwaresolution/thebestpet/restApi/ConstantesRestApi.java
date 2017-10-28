package asoftwaresolution.thebestpet.restApi;

/**
 * Created by AdderlyS on 25/10/2017.
 */

public class ConstantesRestApi {

    //https://api.instagram.com/v1/
    public static final String VERSION = "/v1/";
    public static final String ROOT_URL = "https://api.instagram.com" + VERSION;
    public static final String ACCESS_TOKEN = "6264282911.a8acc9b.8a6669382db24fdab99c7f429cd18322";
    public static final String KEY_ACCESS_TOKEN = "access_token=";
    public static final String KEY_GET_RECENT_MEDIA_USER = "users/self/media/recent/?";
    public static final String KEY_GET_USER_DATA = "users/search?q=";
    public static final String KEY_USERNAME = "_thebestpet&";
    public static final String URL_GET_RECENT_MEDIA_USER = ROOT_URL + KEY_GET_RECENT_MEDIA_USER + KEY_ACCESS_TOKEN + ACCESS_TOKEN;
    public static final String URL_GET_USER_DATA = ROOT_URL + KEY_GET_USER_DATA + KEY_USERNAME + KEY_ACCESS_TOKEN + ACCESS_TOKEN;

    //https://api.instagram.com/v1/users/self/media/recent/?access_token=ACCESS-TOKEN
    //https://api.instagram.com/v1/users/search?q=jack&access_token=ACCESS-TOKEN
}
