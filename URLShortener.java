import java.util.*;

public class URLShortener {
    private static final int LENGTH = 6;
    private static final String BASE_URL = "sho.rt/";
    private static Random random = new Random();
    private static String alphaNum = "abcdefghijklmnopqrstuvwxyz1234567890";
    private static StringBuilder sb = new StringBuilder();
    private static String url = "";
    private static String shortenUrl = "";
    private static boolean isDone = false;
    private static HashMap<String, String> hMap = new HashMap<String, String>();
    private static ArrayList<String> listURL = new ArrayList<String>();
    private static ArrayList<String> listShorten = new ArrayList<String>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        //Simulating input box for multiple entries
            menu();
    }

    public static void menu() {
        while (!isDone) {
            System.out.println("Select Option");
            System.out.println("1. Shorten URL");
            System.out.println("2. Enter shortened URL");
            System.out.println("3. Retrieve URL Database");
            System.out.println("4. Exit");

            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    shortenUrl();
                    shortenMoreUrls();
                    break;
                case "2":
                    clickUrl();
                    break;
                case "3":
                    retrieveURLMapping();
                    break;
                case "4":
                    System.out.println("Exiting Program");
                    isDone = true;
                    break;
                default:
                    System.out.println("Invalid Option");
            }
        }
    }

    public static void shortenUrl() {
        System.out.println("Enter URL to shorten");
        url = sc.nextLine();
        if (url.isEmpty()){
            System.out.println("Empty String. Try again");
            url = sc.nextLine();
        }
        //TODO - if url not empty
        shortenUrl = encodeURL(url);
        System.out.println("Shortened URL is " + shortenUrl);
        listURL.add(url);
        listShorten.add(shortenUrl);
        hMap = urlMapping(listURL, listShorten);
    }

    public static void shortenMoreUrls() {
        do {
            System.out.println("Want to shorten more URLS? Y/N");
            String ans = sc.nextLine();
            switch (ans) {
                case "Y":
                    shortenUrl();
                    isDone = false;
                    break;
                case "N":
                    menu();
                    isDone = true;
                    break;
                default:
                    System.out.println("Invalid Option");
                    isDone = false;
            }
        } while (!isDone);
    }

    public static void clickUrl (){
        //if url exist in hashmap then redirect else ask user retry url
        System.out.println("Enter Shortened URL");
        String input = sc.nextLine();
        boolean result = hMap.containsValue(input);
            if (result ==true){
                url = getKey(hMap,input);
                System.out.println("Valid URL");
                System.out.println("Redirecting to " + url);
            }else{
                System.out.println("Invalid URL");
            }
    }

    public static String getKey(Map<String, String> map, String value)
    {
        String key = null;
                for (Map.Entry<String,String> entry : map.entrySet()){
                    if (Objects.equals(entry.getValue(),value)){
                        key=entry.getKey();
                    }
                }

        return key;
    }

    public static String encodeURL(String longURL) {
              sb.setLength(0);
        for (int i = 0; i < LENGTH; i++) {
            int index = random.nextInt(alphaNum.length());
            char randomChar = alphaNum.charAt(index);
            sb.append(randomChar);
        }
        return BASE_URL + sb.toString();
    }

    public static HashMap<String, String> urlMapping(ArrayList<String> key, ArrayList<String> value) {
        HashMap<String, String> hMap = new HashMap<String, String>();
        for (int i = 0; i < key.size(); i++) {
            hMap.put(key.get(i), value.get(i));
        }
        return hMap;

    }

    public static void retrieveURLMapping() {
        Set set = hMap.entrySet();
        Iterator i = set.iterator();

        while (i.hasNext()) {
            Map.Entry entry = (Map.Entry) i.next();
            System.out.println("Key is " + entry.getKey());
            System.out.println("Value is " + entry.getValue());
        }
      //  System.out.println(hMap.toString());
    }

}
