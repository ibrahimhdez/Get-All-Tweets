import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import twitter4j.conf.ConfigurationBuilder;
import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;

public final class GetTweets{
	private final static int PAGE_SIZE = 200;
	private int numTweets;
	private String stringUser;
	private ResponseList<Status> myStatues;
	private Paging page;
	private ConfigurationBuilder cb;
	private Twitter twitter;
	private User user;
	
	public GetTweets(){
		this.setCb(new ConfigurationBuilder());
		this.setPage(new Paging(1, PAGE_SIZE));
	}
	
	void configurateBuilder(){
	     this.getCb().setDebugEnabled(true)
	        .setOAuthConsumerKey(CONSUMER_KEY) //Change 'CONSUMER_KEY' for you consumer key.
	        .setOAuthConsumerSecret(CONSUMER_SECRET) //Change 'CONSUMER_SECRET' for you consumer secret.
	        .setOAuthAccessToken(ACCESS_TOKEN) //Change 'ACCESS_TOKEN' for you access_token.
	        .setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET); //Change 'ACCESS_TOKEN_SECRET' for you access token secret.
	}
	
	void initializeTwitter(){
		TwitterFactory tf = new TwitterFactory(this.getCb().build());
		this.setTwitter(tf.getInstance());
	}
	
	void initializeUser(String userAccount) throws TwitterException{
		 this.setUser(this.getTwitter().showUser(userAccount));
         this.setNumTweets(this.getUser().getStatusesCount());
         this.setNumTweets(this.getUser().getStatusesCount());
         this.setStringUser(userAccount);
	}
	
	void initializeTweets() throws TwitterException{
		this.setMyStatues(this.getTwitter().getUserTimeline(this.getStringUser(), this.getPage()));
	}
	
	void updatePage(int i){
		this.setPage(new Paging(i + 2, PAGE_SIZE));
	}

	void output(String file) throws IOException, TwitterException{
		int repetitions = this.getNumTweets() / PAGE_SIZE;
		
		FileWriter fw = new FileWriter(file);
        PrintWriter pw = new PrintWriter(fw);
        
        for(int i = 0; i < repetitions; i++){
        	for (Status status : this.getMyStatues())
        		pw.println(status.getText());
   
        	updatePage(i);
        	initializeTweets();
        }
        pw.close();
	}

	/**
	 * @param args --> args[0]: Twitter Account.
	 * 			   --> args[1]: Output textfile name.
	 * @throws TwitterException
	 * @throws IOException
	 */
	public static void main(String[] args) throws TwitterException, IOException {
    		GetTweets gt = new GetTweets();
    		gt.configurateBuilder();
    		gt.initializeTwitter();
       		gt.initializeUser(args[0]);
        	gt.initializeTweets();
        	gt.output(args[1]);
    	}
	
	public Paging getPage() {
		return page;
	}

	public void setPage(Paging page) {
		this.page = page;
	}

	public ConfigurationBuilder getCb() {
		return cb;
	}

	public void setCb(ConfigurationBuilder cb) {
		this.cb = cb;
	}
	
	public Twitter getTwitter() {
		return twitter;
	}

	public void setTwitter(Twitter twitter) {
		this.twitter = twitter;
	}

	public int getNumTweets() {
		return numTweets;
	}

	public void setNumTweets(int numTweets) {
		this.numTweets = numTweets;
	}

	public ResponseList<Status> getMyStatues() {
		return myStatues;
	}

	public void setMyStatues(ResponseList<Status> myStatues) {
		this.myStatues = myStatues;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getStringUser() {
		return stringUser;
	}

	public void setStringUser(String stringUser) {
		this.stringUser = stringUser;
	}
}
