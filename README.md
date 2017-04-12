# lastfm-library
[![CircleCI](https://circleci.com/gh/4-Eyes/lastfm-lib/tree/master.svg?style=shield)](https://circleci.com/gh/4-Eyes/lastfm-lib/tree/master)

Gradle:
```groovy
compile 'com.github.ag:lastfm-library:1.0.2
```
## Usage
1. Add the internet permission in your `AndroidManifest.xml`

   ```xml
   <uses-permission android:name="android.permission.INTERNET" /> 
   ```

2. Add this to the resource file (example strings.xml).

   ```xml
   <string name="api_key">your_api_key</string>
   ```
   If you want to use methods that require authorization add your API secret to the resource file (example strings.xml).
   ```xml
   <string name="secret">your_api_secret</string>
   ```
   
3. Initialize library on startup using the following method. Call it inside `onCreate` method of your `Application` class.
   ```java
   public class TestApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //Initialize here.
        Lfm.initializeWithSecret(this);
        //You can initialize without secret,but remember that you cannot use methods that require authentication.
        //Lfm.initialize(this);

       }
   }
   ```
   
## API Requests

### New Methods

Several examples of how to use the API are detailed below

1. Getting Track Info

   ```java
   // Using artist and track name
   LfmRequest request = LfmApi.track().getInfo("Evermore", "It's Too Late");
   // Using MusicBrainz ID
   LfmRequest request = LfmApi.track().getInfo(UUID.fromString("1846f14e-99ec-4f12-b35f-ae4454df314a"));
   ```
2. Getting Artist Info

   ```java
   // Using artist name
   LfmRequest request = LfmApi.artist().getInfo("Florence + the Machine");
   // Using MusicBrainz ID
   LfmRequest request = LfmApi.artist().getInfo(UUID.fromString("5fee3020-513b-48c2-b1f7-4681b01db0c6"));
   ```
3. Scrobbling

   ```java
   // Single Scrobble
   LfmRequest request = LfmApi.track().scrobble("Florence + the Machine", "Queen of Peace",
                new Date(System.currentTimeMillis() / 1000));


   // Multiple Scrobble
   Collection<String> tracks = Arrays.asList("Farewell To The Fairground (Single Mix)",
                "Starlight", "The Cave");
   Collection<String> artists = Arrays.asList("White Lies", "Muse", "Mumford & Sons");
   Collection<Date> timestamps = Collections.nCopies(3, new Date(System.currentTimeMillis() / 1000));
   
   // Without optional parameters
   LfmRequest request = LfmApi.track().scrobble(artists, tracks, timestamps);
   
   // With optional parameters
   // albums are optional
   Collection<String> albums = Arrays.asList("Farewell To The Fairground",
                "Black Holes And Revelations", "Sigh No More");
   // The five nulls are for other optional parameters
   LfmRequest request = LfmApi.track().scrobble(artists, tracks, timestamps, albums, null, null, null, null, null);
   ```

### Old Methods

You can still use these methods, however, they are now deprecated as they weren't as inuative to use as the replacement methods. The newer methods have a signature, or multiple singatures (such as methods which can use MusicBrainz IDs or an artist/track combo), with all required fields and then one signature with all the required and optional fields. 

1. Plain request.

   ```java
   LfmRequest lfmRequest = LfmApi.user().getInfo();
    ```
2. Request with parameters.

   ```java
    LfmParameters params = new LfmParameters();
    params.put("artist","The artist name");
    params.put("track","The track name");
        
    LfmRequest request = LfmApi.track().getInfo(params);
   ```
3. Track scrobble.
   
   **Remember** : This method requires authentication.
   It means that you should use `API secret` when initializing library at the beginning.
   ```java
   ScrobbleParameters params = new ScrobbleParameters();
   params.put("track","track1","track2","track3");
   params.put("artist","artist1","artist2","artist3");
        
   String timestamp = String.valueOf(System.currentTimeMillis()/1000);
   params.put("timestamp",timestamp,timestamp,timestamp);
        
   LfmRequest trackScrobble = LfmApi.track().scrobble(params);
   ```
## Requests Sending
```java
 request.executeWithListener(new LfmRequest.LfmRequestListener() {
    @Override
    public void onComplete(JSONObject response) {
         //Called if there were no errors
    }

    @Override
    public void onError(LfmError error) {
        //Called immediately if there was API error, or  if there was an HTTP error
    }
});
```
## User Authorization
**Remember** : Use `Lfm.initializeWithSecret(Context context);` method when initializing library at the beginning if you want to use user authorization .
```java
Lfm.login("username", "password", new Lfm.LfmCallback<Session>() {
    @Override
    public void onResult(Session result) {
        // User passed Authorization      
    }

    @Override
    public void onError(LfmError error) {
        // User didn't pass Authorization
    }
});
```

## Future Work
- Return models instead of JSON objects
- Add the ability to pass models into api calls
- Better backend handling of requests
- Better error management for API errors
