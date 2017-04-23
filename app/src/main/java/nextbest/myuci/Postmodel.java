package nextbest.myuci;

public  class Postmodel {

    public String postTitle, postDescription,  userName;

    public Postmodel() {
        this("", "", "");
    }
    public Postmodel(String postTitle, String postDescription, String userName) {
        this.postTitle = postTitle;
        this.postDescription = postDescription;
        this.userName = userName;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostDescription() {
        return postDescription;
    }

    public void setPostDescription(String postDescription) {
        this.postDescription = postDescription;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
