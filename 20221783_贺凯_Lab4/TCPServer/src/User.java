public class User {
    private int userId;
    private String username;
    private int sum;
    public User(int userId, String username, int sum) {
        this.userId = userId;
        this.username = username;
        this.sum = sum;
    }

    public int getUserId() {
        return userId;
    }
    public String getUsername() {
        return username;
    }
    public int getSum(){
        return sum;
    }
    public void setSum(int sum){
        this.sum=sum;
    }
}
