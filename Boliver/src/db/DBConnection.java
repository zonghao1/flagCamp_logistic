package db;

public interface DBConnection {
	/**
	 * Close the connection.
	 */
	public void close();

	/**
	 * Insert the favorite items for a user.
	 * 
	 * @param userId
	 * @param itemIds
	 */
	
	public boolean verifyLogin(String userId, String password);
	
	/**
	 * 
	 * @param userId
	 * @param password
	 * @param firstname
	 * @param lastname
	 * @return
	 */
	public boolean registerUser(String userId, String password, String firstname, String lastname);
}
