package online.xuanwei.bbs.model;

public class UserKey {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.Host
     *
     * @mbg.generated Wed Apr 14 13:33:46 CST 2021
     */
    private String host;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.User
     *
     * @mbg.generated Wed Apr 14 13:33:46 CST 2021
     */
    private String user;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.Host
     *
     * @return the value of user.Host
     *
     * @mbg.generated Wed Apr 14 13:33:46 CST 2021
     */
    public String getHost() {
        return host;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.Host
     *
     * @param host the value for user.Host
     *
     * @mbg.generated Wed Apr 14 13:33:46 CST 2021
     */
    public void setHost(String host) {
        this.host = host == null ? null : host.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.User
     *
     * @return the value of user.User
     *
     * @mbg.generated Wed Apr 14 13:33:46 CST 2021
     */
    public String getUser() {
        return user;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.User
     *
     * @param user the value for user.User
     *
     * @mbg.generated Wed Apr 14 13:33:46 CST 2021
     */
    public void setUser(String user) {
        this.user = user == null ? null : user.trim();
    }
}